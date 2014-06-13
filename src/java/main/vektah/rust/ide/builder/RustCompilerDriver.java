package vektah.rust.ide.builder;

import com.intellij.compiler.CompilerConfiguration;
import com.intellij.compiler.ProblemsView;
import com.intellij.compiler.impl.*;
import com.intellij.compiler.progress.CompilerTask;
import com.intellij.compiler.server.BuildManager;
import com.intellij.compiler.server.CustomBuilderMessageHandler;
import com.intellij.compiler.server.DefaultMessageHandler;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.compiler.*;
import com.intellij.openapi.compiler.ex.CompilerPathsEx;
import com.intellij.openapi.deployment.DeploymentUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.packaging.artifacts.Artifact;
import com.intellij.packaging.impl.compiler.ArtifactCompilerUtil;
import com.intellij.packaging.impl.compiler.ArtifactsCompiler;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.util.ThrowableRunnable;
import com.intellij.util.containers.*;
import com.intellij.util.messages.MessageBus;
import gnu.trove.THashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.api.CmdlineProtoUtil;
import org.jetbrains.jps.api.CmdlineRemoteProto;
import org.jetbrains.jps.api.RequestFuture;

import javax.swing.*;
import java.io.File;
import java.util.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class RustCompilerDriver {
	private static final Logger LOG = Logger.getInstance(RustCompilerDriver.class);

	private static final Key<ExitStatus> COMPILE_SERVER_BUILD_STATUS = Key.create("RUST_COMPILE_SERVER_BUILD_STATUS");
	private static final long ONE_MINUTE_MS = 60L /*sec*/ * 1000L /*millisec*/;

	private final Project myProject;

	public RustCompilerDriver(Project myProject) {
		this.myProject = myProject;
	}

	public void make(CompileScope scope, CompileStatusNotification callback) {
		startup(scope, false, false, callback, null, true);
	}

	private void startup(final CompileScope scope,
	                     final boolean isRebuild,
	                     final boolean forceCompile,
	                     final CompileStatusNotification callback,
	                     final CompilerMessage message,
	                     final boolean checkCachesVersion) {
		ApplicationManager.getApplication().assertIsDispatchThread();

		final String contentName =
				forceCompile ? CompilerBundle.message("compiler.content.name.compile") : CompilerBundle.message("compiler.content.name.make");
		final boolean isUnitTestMode = ApplicationManager.getApplication().isUnitTestMode();
		final CompilerTask compileTask = new CompilerTask(myProject, contentName, isUnitTestMode, true, true, isCompilationStartedAutomatically(scope));

		StatusBar.Info.set("", myProject, "Compiler");

		saveAndCommit(isUnitTestMode);

		final CompileContextImpl compileContext = new CompileContextImpl(myProject, compileTask, scope, null, !isRebuild && !forceCompile, isRebuild);

		executeCompileTask(scope, isRebuild, forceCompile, callback, message, checkCachesVersion, compileTask, compileContext);
	}

	private void executeCompileTask(final CompileScope scope, final boolean isRebuild, final boolean forceCompile, final CompileStatusNotification callback, final CompilerMessage message, final boolean checkCachesVersion, CompilerTask compileTask, final CompileContextImpl compileContext) {
		final Runnable compileWork = new Runnable() {
			public void run() {
				final ProgressIndicator indicator = compileContext.getProgressIndicator();
				if (indicator.isCanceled() || myProject.isDisposed()) {
					if (callback != null) {
						callback.finished(true, 0, 0, compileContext);
					}
					return;
				}
				try {
					LOG.info("RUST COMPILATION STARTED (BUILD PROCESS)");
					if (message != null) {
						compileContext.addMessage(message);
					}
					if (isRebuild) {
						CompilerUtil.runInContext(compileContext, "Clearing build system data...", new ThrowableRunnable<Throwable>() {
							@Override
							public void run() throws Throwable {
								CompilerCacheManager.getInstance(myProject).clearCaches(compileContext);
							}
						});
					}
					final boolean beforeTasksOk = executeCompileTasks(compileContext, true);

					final int errorCount = compileContext.getMessageCount(CompilerMessageCategory.ERROR);
					if (!beforeTasksOk || errorCount > 0) {
						COMPILE_SERVER_BUILD_STATUS.set(compileContext, errorCount > 0? ExitStatus.ERRORS : ExitStatus.CANCELLED);
						return;
					}

					final RequestFuture future = compileInExternalProcess(compileContext, false);
					if (future != null) {
						while (!future.waitFor(200L , TimeUnit.MILLISECONDS)) {
							if (indicator.isCanceled()) {
								future.cancel(false);
							}
						}
						if (!executeCompileTasks(compileContext, false)) {
							COMPILE_SERVER_BUILD_STATUS.set(compileContext, ExitStatus.CANCELLED);
						}
						if (compileContext.getMessageCount(CompilerMessageCategory.ERROR) > 0) {
							COMPILE_SERVER_BUILD_STATUS.set(compileContext, ExitStatus.ERRORS);
						}
					}
				}
				catch (Throwable e) {
					LOG.error(e); // todo
				}
				finally {
					CompilerCacheManager.getInstance(myProject).flushCaches();

					final long duration = notifyCompilationCompleted(compileContext, callback, COMPILE_SERVER_BUILD_STATUS.get(compileContext), true);
					CompilerUtil.logDuration(
							"\tCOMPILATION FINISHED (BUILD PROCESS); Errors: " +
									compileContext.getMessageCount(CompilerMessageCategory.ERROR) +
									"; warnings: " +
									compileContext.getMessageCount(CompilerMessageCategory.WARNING),
							duration
					);
				}
			}
		};

		compileTask.start(compileWork, new Runnable() {
			public void run() {
				if (isRebuild) {
					final int rv = Messages.showOkCancelDialog(
							myProject, "You are about to rebuild the whole project.\nRun 'Make Project' instead?", "Confirm Project Rebuild",
							"Make", "Rebuild", Messages.getQuestionIcon()
					);
					if (rv == Messages.OK /*yes, please, do run make*/) {
						startup(scope, false, false, callback, null, checkCachesVersion);
						return;
					}
				}
				startup(scope, isRebuild, forceCompile, callback, message, checkCachesVersion);
			}
		});
	}

	private boolean executeCompileTasks(final CompileContext context, final boolean beforeTasks) {
		if (myProject.isDisposed()) {
			return false;
		}
		final CompilerManager manager = CompilerManager.getInstance(myProject);
		final ProgressIndicator progressIndicator = context.getProgressIndicator();
		progressIndicator.pushState();
		try {
			CompileTask[] tasks = beforeTasks ? manager.getBeforeTasks() : manager.getAfterTasks();
			if (tasks.length > 0) {
				progressIndicator.setText(beforeTasks
						? CompilerBundle.message("progress.executing.precompile.tasks")
						: CompilerBundle.message("progress.executing.postcompile.tasks"));
				for (CompileTask task : tasks) {
					if (!task.execute(context)) {
						return false;
					}
				}
			}
		}
		finally {
			progressIndicator.popState();
			WindowManager.getInstance().getStatusBar(myProject).setInfo("");
			if (progressIndicator instanceof CompilerTask) {
				ApplicationManager.getApplication().invokeLater(new Runnable() {
					public void run() {
						((CompilerTask)progressIndicator).showCompilerContent();
					}
				});
			}
		}
		return true;
	}


	@Nullable
	private RequestFuture compileInExternalProcess(final @NotNull CompileContextImpl compileContext, final boolean onlyCheckUpToDate)
			throws Exception {
		final CompileScope scope = compileContext.getCompileScope();
		final Collection<String> paths = CompileScopeUtil.fetchFiles(compileContext);
		List<CmdlineRemoteProto.Message.ControllerMessage.ParametersMessage.TargetTypeBuildScope> scopes = new ArrayList<CmdlineRemoteProto.Message.ControllerMessage.ParametersMessage.TargetTypeBuildScope>();
		final boolean forceBuild = !compileContext.isMake();
		List<CmdlineRemoteProto.Message.ControllerMessage.ParametersMessage.TargetTypeBuildScope> explicitScopes = CompileScopeUtil.getBaseScopeForExternalBuild(scope);
		if (explicitScopes != null) {
			scopes.addAll(explicitScopes);
		}
		else if (!compileContext.isRebuild() && !CompileScopeUtil.allProjectModulesAffected(compileContext)) {
			CompileScopeUtil.addScopesForModules(Arrays.asList(scope.getAffectedModules()), scopes, forceBuild);
		}
		else {
			scopes.addAll(CmdlineProtoUtil.createAllModulesScopes(forceBuild));
		}
		if (paths.isEmpty()) {
			for (BuildTargetScopeProvider provider : BuildTargetScopeProvider.EP_NAME.getExtensions()) {
				scopes = CompileScopeUtil.mergeScopes(scopes, provider.getBuildTargetScopes(scope, CompilerFilter.ALL, myProject, forceBuild));
			}
		}

		// need to pass scope's user data to server
		final Map<String, String> builderParams;
		if (onlyCheckUpToDate) {
			builderParams = Collections.emptyMap();
		}
		else {
			final Map<Key, Object> exported = scope.exportUserData();
			if (!exported.isEmpty()) {
				builderParams = new com.intellij.util.containers.HashMap<String, String>();
				for (Map.Entry<Key, Object> entry : exported.entrySet()) {
					final String _key = entry.getKey().toString();
					final String _value = entry.getValue().toString();
					builderParams.put(_key, _value);
				}
			}
			else {
				builderParams = Collections.emptyMap();
			}
		}

		final MessageBus messageBus = myProject.getMessageBus();
		final MultiMap<String, Artifact> outputToArtifact = ArtifactCompilerUtil.containsArtifacts(scopes) ? ArtifactCompilerUtil.createOutputToArtifactMap(myProject) : null;
		final RustBuildManager buildManager = RustBuildManager.getInstance();
		buildManager.cancelAutoMakeTasks(myProject);
		return buildManager.scheduleBuild(myProject, compileContext.isRebuild(), compileContext.isMake(), onlyCheckUpToDate, scopes, paths, builderParams, new DefaultMessageHandler(myProject) {
			@Override
			public void buildStarted(UUID sessionId) {
			}

			@Override
			public void sessionTerminated(final UUID sessionId) {
				if (compileContext.shouldUpdateProblemsView()) {
					final ProblemsView view = ProblemsViewImpl.SERVICE.getInstance(myProject);
					view.clearProgress();
					view.clearOldMessages(compileContext.getCompileScope(), compileContext.getSessionId());
				}
			}

			@Override
			public void handleFailure(UUID sessionId, CmdlineRemoteProto.Message.Failure failure) {
				parseErrors(failure.getDescription());
				compileContext.putUserData(COMPILE_SERVER_BUILD_STATUS, ExitStatus.ERRORS);
			}

			private void parseErrors(String description) {
				Map<String, VirtualFile> files = new HashMap<String, VirtualFile>();
				String[] lines = description.split("\n");
				for (String line : lines) {
					if (Character.isWhitespace(line.charAt(0))) {
						continue;
					}
					int idx = line.indexOf(':');
					if (idx <= 0) {
						continue;
					}
					String fileName = line.substring(0, idx);
					VirtualFile file = files.get(fileName);
					if (file == null) {
						file = myProject.getBaseDir().findFileByRelativePath(fileName);
						if (file != null) {
							files.put(fileName, file);
						}
					}
					if (file == null) {
						continue;
					}
					try {
						int idx2 = line.indexOf(':', idx + 1);
						int lineFromRow = Integer.valueOf(line.substring(idx + 1, idx2));
						idx = idx2;
						idx2 = line.indexOf(':', idx + 1);
						int lineFromCol = Integer.valueOf(line.substring(idx + 1, idx2));

						idx = idx2;
						idx2 = line.indexOf(':', idx + 1);
						if (!Character.isWhitespace(line.charAt(idx + 1))) {
							continue;
						}
						int lineToRow = Integer.valueOf(line.substring(idx + 2, idx2));
						idx = idx2;
						idx2 = line.indexOf(' ', idx + 1);
						int lineToCol = Integer.valueOf(line.substring(idx + 1, idx2));
						String message = line.substring(idx2 + 1);

						final CompilerMessageCategory errorType;
						if (message.startsWith("error: ")) {
							errorType = CompilerMessageCategory.ERROR;
							message = message.substring("error: ".length());
						} else if (message.startsWith("warning: ")) {
							errorType = CompilerMessageCategory.WARNING;
							message = message.substring("error: ".length());
						} else {
							errorType = CompilerMessageCategory.INFORMATION;
						}
						compileContext.addMessage(errorType, message, file.getUrl(), lineFromRow, lineFromCol);
					} catch (Exception ex) {
					}
				}
			}

			@Override
			protected void handleCompileMessage(UUID sessionId, CmdlineRemoteProto.Message.BuilderMessage.CompileMessage message) {
				final CmdlineRemoteProto.Message.BuilderMessage.CompileMessage.Kind kind = message.getKind();
				//System.out.println(compilerMessage.getText());
				final String messageText = message.getText();
				if (kind == CmdlineRemoteProto.Message.BuilderMessage.CompileMessage.Kind.PROGRESS) {
					final ProgressIndicator indicator = compileContext.getProgressIndicator();
					indicator.setText(messageText);
					if (message.hasDone()) {
						indicator.setFraction(message.getDone());
					}
				}
				else {
					final CompilerMessageCategory category = kind == CmdlineRemoteProto.Message.BuilderMessage.CompileMessage.Kind.ERROR ? CompilerMessageCategory.ERROR
							: kind == CmdlineRemoteProto.Message.BuilderMessage.CompileMessage.Kind.WARNING ? CompilerMessageCategory.WARNING : CompilerMessageCategory.INFORMATION;

					String sourceFilePath = message.hasSourceFilePath() ? message.getSourceFilePath() : null;
					if (sourceFilePath != null) {
						sourceFilePath = FileUtil.toSystemIndependentName(sourceFilePath);
					}
					final long line = message.hasLine() ? message.getLine() : -1;
					final long column = message.hasColumn() ? message.getColumn() : -1;
					final String srcUrl = sourceFilePath != null ? VirtualFileManager.constructUrl(LocalFileSystem.PROTOCOL, sourceFilePath) : null;
					compileContext.addMessage(category, messageText, srcUrl, (int)line, (int)column);
				}
			}

			@Override
			protected void handleBuildEvent(UUID sessionId, CmdlineRemoteProto.Message.BuilderMessage.BuildEvent event) {
				final CmdlineRemoteProto.Message.BuilderMessage.BuildEvent.Type eventType = event.getEventType();
				switch (eventType) {
					case FILES_GENERATED:
						final List<CmdlineRemoteProto.Message.BuilderMessage.BuildEvent.GeneratedFile> generated = event.getGeneratedFilesList();
						final CompilationStatusListener publisher = messageBus.syncPublisher(CompilerTopics.COMPILATION_STATUS);
						Set<String> writtenArtifactOutputPaths = outputToArtifact != null ? new THashSet<String>(FileUtil.PATH_HASHING_STRATEGY) : null;
						for (CmdlineRemoteProto.Message.BuilderMessage.BuildEvent.GeneratedFile generatedFile : generated) {
							final String root = FileUtil.toSystemIndependentName(generatedFile.getOutputRoot());
							final String relativePath = FileUtil.toSystemIndependentName(generatedFile.getRelativePath());
							publisher.fileGenerated(root, relativePath);
							if (outputToArtifact != null) {
								Collection<Artifact> artifacts = outputToArtifact.get(root);
								if (!artifacts.isEmpty()) {
									for (Artifact artifact : artifacts) {
										ArtifactsCompiler.addChangedArtifact(compileContext, artifact);
									}
									writtenArtifactOutputPaths.add(FileUtil.toSystemDependentName(DeploymentUtil.appendToPath(root, relativePath)));
								}
							}
						}
						if (writtenArtifactOutputPaths != null && !writtenArtifactOutputPaths.isEmpty()) {
							ArtifactsCompiler.addWrittenPaths(compileContext, writtenArtifactOutputPaths);
						}
						break;
					case BUILD_COMPLETED:
						ExitStatus status = ExitStatus.SUCCESS;
						if (event.hasCompletionStatus()) {
							final CmdlineRemoteProto.Message.BuilderMessage.BuildEvent.Status completionStatus = event.getCompletionStatus();
							switch (completionStatus) {
								case CANCELED:
									status = ExitStatus.CANCELLED;
									break;
								case ERRORS:
									status = ExitStatus.ERRORS;
									break;
								case SUCCESS:
									status = ExitStatus.SUCCESS;
									break;
								case UP_TO_DATE:
									status = ExitStatus.UP_TO_DATE;
									break;
							}
						}
						compileContext.putUserDataIfAbsent(COMPILE_SERVER_BUILD_STATUS, status);
						break;
					case CUSTOM_BUILDER_MESSAGE:
						if (event.hasCustomBuilderMessage()) {
							CmdlineRemoteProto.Message.BuilderMessage.BuildEvent.CustomBuilderMessage message = event.getCustomBuilderMessage();
							messageBus.syncPublisher(CustomBuilderMessageHandler.TOPIC).messageReceived(message.getBuilderId(), message.getMessageType(),
									message.getMessageText());
						}
						break;
				}
			}
		});
	}

	private void saveAndCommit(boolean isUnitTestMode) {
		// ensure the project model seen by build process is up-to-date
		myProject.save();
		if (!isUnitTestMode) {
			ApplicationManager.getApplication().saveSettings();
		}

		PsiDocumentManager.getInstance(myProject).commitAllDocuments();
		FileDocumentManager.getInstance().saveAllDocuments();
	}

	private static boolean isCompilationStartedAutomatically(CompileScope scope) {
		// FIXME
		return false;
	}

	private long notifyCompilationCompleted(final CompileContextImpl compileContext,
	                                        final CompileStatusNotification callback,
	                                        final ExitStatus _status,
	                                        final boolean refreshOutputRoots) {
		final long duration = System.currentTimeMillis() - compileContext.getStartCompilationStamp();
		if (refreshOutputRoots && !myProject.isDisposed()) {
			// refresh on output roots is required in order for the order enumerator to see all roots via VFS
			final Set<File> outputs = new HashSet<File>();
			final Module[] affectedModules = compileContext.getCompileScope().getAffectedModules();
			for (final String path : CompilerPathsEx.getOutputPaths(affectedModules)) {
				outputs.add(new File(path));
			}
			final LocalFileSystem lfs = LocalFileSystem.getInstance();
			if (!outputs.isEmpty()) {
				final ProgressIndicator indicator = compileContext.getProgressIndicator();
				indicator.setText("Synchronizing output directories...");
				CompilerUtil.refreshOutputDirectories(outputs, _status == ExitStatus.CANCELLED);
				indicator.setText("");
			}
			if (compileContext.isAnnotationProcessorsEnabled() && !myProject.isDisposed()) {
				final Set<File> genSourceRoots = new THashSet<File>(FileUtil.FILE_HASHING_STRATEGY);
				final CompilerConfiguration config = CompilerConfiguration.getInstance(myProject);
				for (Module module : affectedModules) {
					if (config.getAnnotationProcessingConfiguration(module).isEnabled()) {
						final String path = CompilerPaths.getAnnotationProcessorsGenerationPath(module);
						if (path != null) {
							genSourceRoots.add(new File(path));
						}
					}
				}
				if (!genSourceRoots.isEmpty()) {
					// refresh generates source roots asynchronously; needed for error highlighting update
					lfs.refreshIoFiles(genSourceRoots, true, true, null);
				}
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int errorCount = 0;
				int warningCount = 0;
				try {
					errorCount = compileContext.getMessageCount(CompilerMessageCategory.ERROR);
					warningCount = compileContext.getMessageCount(CompilerMessageCategory.WARNING);
					if (!myProject.isDisposed()) {
						final String statusMessage = createStatusMessage(_status, warningCount, errorCount, duration);
						final MessageType messageType = errorCount > 0 ? MessageType.ERROR : warningCount > 0 ? MessageType.WARNING : MessageType.INFO;
						if (duration > ONE_MINUTE_MS) {
							ToolWindowManager.getInstance(myProject).notifyByBalloon(ToolWindowId.MESSAGES_WINDOW, messageType, statusMessage);
						}
						CompilerManager.NOTIFICATION_GROUP.createNotification(statusMessage, messageType).notify(myProject);
						if (_status != ExitStatus.UP_TO_DATE && compileContext.getMessageCount(null) > 0) {
							compileContext.addMessage(CompilerMessageCategory.INFORMATION, statusMessage, null, -1, -1);
						}
					}
				} finally {
					if (callback != null) {
						callback.finished(_status == ExitStatus.CANCELLED, errorCount, warningCount, compileContext);
					}
				}
			}
		});
		return duration;
	}

	private static String createStatusMessage(final ExitStatus status, final int warningCount, final int errorCount, long duration) {
		String message;
		if (status == ExitStatus.CANCELLED) {
			message = CompilerBundle.message("status.compilation.aborted");
		}
		else if (status == ExitStatus.UP_TO_DATE) {
			message = CompilerBundle.message("status.all.up.to.date");
		}
		else  {
			if (status == ExitStatus.SUCCESS) {
				message = warningCount > 0
						? CompilerBundle.message("status.compilation.completed.successfully.with.warnings", warningCount)
						: CompilerBundle.message("status.compilation.completed.successfully");
			}
			else {
				message = CompilerBundle.message("status.compilation.completed.successfully.with.warnings.and.errors", errorCount, warningCount);
			}
			message = message + " in " + StringUtil.formatDuration(duration);
		}
		return message;
	}
}
