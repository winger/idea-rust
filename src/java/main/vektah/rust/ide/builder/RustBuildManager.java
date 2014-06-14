package vektah.rust.ide.builder;

import com.intellij.compiler.options.CompileStepBeforeRun;
import com.intellij.compiler.server.BuildManagerListener;
import com.intellij.compiler.server.BuilderMessageHandler;
import com.intellij.compiler.server.DefaultMessageHandler;
import com.intellij.compiler.server.impl.BuildProcessClasspathManager;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.ide.DataManager;
import com.intellij.ide.PowerSaveMode;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectCoreUtil;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.ShutDownTracker;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.util.SmartList;
import com.intellij.util.concurrency.SequentialTaskExecutor;
import com.intellij.util.messages.MessageBusConnection;
import gnu.trove.THashSet;
import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.ide.PooledThreadExecutor;
import org.jetbrains.io.ChannelRegistrar;
import org.jetbrains.jps.api.CmdlineProtoUtil;
import org.jetbrains.jps.api.CmdlineRemoteProto;
import org.jetbrains.jps.api.RequestFuture;
import vektah.rust.ide.runner.RustConfiguration;
import vektah.rust.ide.sdk.RustSdkData;
import vektah.rust.ide.sdk.RustSdkType;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;

public class RustBuildManager implements com.intellij.openapi.components.ApplicationComponent {
	private static final Logger LOG = Logger.getInstance(RustBuildManager.class);

	private final ProjectManager myProjectManager;
	private final boolean IS_UNIT_TEST_MODE;
	private final Charset mySystemCharset;
	private final File mySystemDirectory;

	private final ChannelRegistrar myChannelRegistrar = new ChannelRegistrar();
	private final Map<RequestFuture, Project> myAutomakeFutures = Collections.synchronizedMap(new HashMap<RequestFuture, Project>());
	private final Map<String, RequestFuture> myBuildsInProgress = Collections.synchronizedMap(new HashMap<String, RequestFuture>());
	private final BuildProcessClasspathManager myClasspathManager = new BuildProcessClasspathManager();
	private final SequentialTaskExecutor myRequestsProcessor = new SequentialTaskExecutor(PooledThreadExecutor.INSTANCE);
	private final Map<String, ProjectData> myProjectDataMap = Collections.synchronizedMap(new HashMap<String, ProjectData>());
	private final RustBuildMessageDispatcher myMessageDispatcher = new RustBuildMessageDispatcher();

	public RustBuildManager(final ProjectManager projectManager) {
		final Application application = ApplicationManager.getApplication();
		IS_UNIT_TEST_MODE = application.isUnitTestMode();
		myProjectManager = projectManager;
		mySystemCharset = CharsetToolkit.getDefaultSystemCharset();
		final String systemPath = PathManager.getSystemPath();
		File system = new File(systemPath);
		try {
			system = system.getCanonicalFile();
		}
		catch (IOException e) {
			LOG.info(e);
		}
		mySystemDirectory = system;

//		projectManager.addProjectManagerListener(new ProjectWatcher());

		final MessageBusConnection conn = application.getMessageBus().connect();
		conn.subscribe(VirtualFileManager.VFS_CHANGES, new BulkFileListener.Adapter() {
			@Override
			public void after(@NotNull List<? extends VFileEvent> events) {
				if (shouldTriggerMake(events)) {
//					scheduleAutoMake();
				}
			}

			private boolean shouldTriggerMake(List<? extends VFileEvent> events) {
				if (PowerSaveMode.isEnabled()) {
					return false;
				}

				Project project = null;
				ProjectFileIndex fileIndex = null;

				for (VFileEvent event : events) {
					final VirtualFile eventFile = event.getFile();
					if (eventFile == null || ProjectCoreUtil.isProjectOrWorkspaceFile(eventFile)) {
						continue;
					}

					if (project == null) {
						// lazy init
						project = getCurrentContextProject();
						if (project == null) {
							return false;
						}
						fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
					}

					if (fileIndex.isInContent(eventFile)) {
						return true;
					}
				}
				return false;
			}

		});

		EditorFactory.getInstance().getEventMulticaster().addDocumentListener(new DocumentAdapter() {
			@Override
			public void documentChanged(DocumentEvent e) {
//				scheduleProjectSave();
			}
		});

		ShutDownTracker.getInstance().registerShutdownTask(new Runnable() {
			@Override
			public void run() {
				stopListening();
			}
		});
	}

	@Nullable
	private Project getCurrentContextProject() {
		return getContextProject(null);
	}

	@Nullable
	private Project getContextProject(@Nullable Window window) {
		final List<Project> openProjects = getOpenProjects();
		if (openProjects.isEmpty()) {
			return null;
		}
		if (openProjects.size() == 1) {
			return openProjects.get(0);
		}

		if (window == null) {
			window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
			if (window == null) {
				return null;
			}
		}

		Component comp = window;
		while (true) {
			final Container _parent = comp.getParent();
			if (_parent == null) {
				break;
			}
			comp = _parent;
		}

		Project project = null;
		if (comp instanceof IdeFrame) {
			project = ((IdeFrame)comp).getProject();
		}
		if (project == null) {
			project = CommonDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext(comp));
		}

		return isValidProject(project)? project : null;
	}

	private List<Project> getOpenProjects() {
		final Project[] projects = myProjectManager.getOpenProjects();
		if (projects.length == 0) {
			return Collections.emptyList();
		}
		final List<Project> projectList = new SmartList<Project>();
		for (Project project : projects) {
			if (isValidProject(project)) {
				projectList.add(project);
			}
		}
		return projectList;
	}

	private static boolean isValidProject(@Nullable Project project) {
		return project != null && !project.isDisposed() && !project.isDefault() && project.isInitialized();
	}

	private void stopListening() {
		myChannelRegistrar.close();
	}

	public static RustBuildManager getInstance() {
		return ApplicationManager.getApplication().getComponent(RustBuildManager.class);
	}

	public Collection<RequestFuture> cancelAutoMakeTasks(Project project) {
		final Collection<RequestFuture> futures = new SmartList<RequestFuture>();
		synchronized (myAutomakeFutures) {
			for (Map.Entry<RequestFuture, Project> entry : myAutomakeFutures.entrySet()) {
				if (entry.getValue().equals(project)) {
					final RequestFuture future = entry.getKey();
					future.cancel(false);
					futures.add(future);
				}
			}
		}
		return futures;
	}

	@Nullable
	private static String getProjectPath(final Project project) {
		final String url = project.getPresentableUrl();
		if (url == null) {
			return null;
		}
		return VirtualFileManager.extractPath(url);
	}

	@Nullable
	public RequestFuture scheduleBuild(
			final Project project, final boolean isRebuild, final boolean isMake,
			final boolean onlyCheckUpToDate, final CompileScope scope,
			final Collection<String> paths,
			final Map<String, String> userData, final DefaultMessageHandler messageHandler) {

		final String projectPath = getProjectPath(project);
		final String runConfigurationName = userData.get(CompileStepBeforeRun.RUN_CONFIGURATION.toString());
		final UUID sessionId = UUID.randomUUID();
//		final boolean isAutomake = messageHandler instanceof AutoMakeMessageHandler;
		final boolean isAutomake = false;   // FIXME
		final BuilderMessageHandler handler = new MessageHandlerWrapper(messageHandler) {
			@Override
			public void buildStarted(UUID sessionId) {
				super.buildStarted(sessionId);
				try {
					ApplicationManager.getApplication().getMessageBus().syncPublisher(BuildManagerListener.TOPIC).buildStarted(project, sessionId, isAutomake);
				}
				catch (Throwable e) {
					LOG.error(e);
				}
			}

			@Override
			public void sessionTerminated(UUID sessionId) {
				try {
					super.sessionTerminated(sessionId);
				}
				finally {
					try {
						ApplicationManager.getApplication().getMessageBus().syncPublisher(BuildManagerListener.TOPIC).buildFinished(project, sessionId, isAutomake);
					}
					catch (Throwable e) {
						LOG.error(e);
					}
				}
			}
		};

		try {
			final RequestFuture<BuilderMessageHandler> future = new RequestFuture<BuilderMessageHandler>(handler, sessionId, new RequestFuture.CancelAction<BuilderMessageHandler>() {
				@Override
				public void cancel(RequestFuture<BuilderMessageHandler> future) throws Exception {
					myMessageDispatcher.cancelSession(future.getRequestID());
				}
			});
			// by using the same queue that processes events we ensure that
			// the build will be aware of all events that have happened before this request
			runCommand(new Runnable() {
				@Override
				public void run() {
					if (future.isCancelled() || project.isDisposed()) {
						handler.sessionTerminated(sessionId);
						future.setDone();
						return;
					}

					final CmdlineRemoteProto.Message.ControllerMessage.GlobalSettings globals =
							CmdlineRemoteProto.Message.ControllerMessage.GlobalSettings.newBuilder()
									.setGlobalOptionsPath(PathManager.getOptionsPath())
									.build();
					CmdlineRemoteProto.Message.ControllerMessage.FSEvent currentFSChanges;
					final SequentialTaskExecutor projectTaskQueue;
					synchronized (myProjectDataMap) {
						ProjectData data = myProjectDataMap.get(projectPath);
						if (data == null) {
							data = new ProjectData(new SequentialTaskExecutor(PooledThreadExecutor.INSTANCE));
							myProjectDataMap.put(projectPath, data);
						}
						if (isRebuild) {
							data.dropChanges();
						}
						// FIXME: This doesn't do anything at the moment
						currentFSChanges = data.getAndResetRescanFlag() ? null : data.createNextEvent();
						projectTaskQueue = data.taskQueue;
					}

					myMessageDispatcher.registerBuildMessageHandler(sessionId, new MessageHandlerWrapper(handler) {
						@Override
						public void sessionTerminated(UUID sessionId) {
							try {
								super.sessionTerminated(sessionId);
							}
							finally {
								future.setDone();
							}
						}
					});

					try {
						projectTaskQueue.submit(new Runnable() {
							@Override
							public void run() {
								Throwable execFailure = null;
								try {
									if (project.isDisposed()) {
										return;
									}
									myBuildsInProgress.put(projectPath, future);
									final OSProcessHandler processHandler = launchBuildProcess(project, sessionId, scope);
									final StringBuilder stdErrOutput = new StringBuilder();
									processHandler.addProcessListener(new ProcessAdapter() {
										@Override
										public void onTextAvailable(ProcessEvent event, Key outputType) {
											// re-translate builder's output to idea.log
											final String text = event.getText();
											if (!StringUtil.isEmptyOrSpaces(text)) {
												LOG.info("RUST_BUILDER_PROCESS [" + outputType.toString() + "]: " + text.trim());
												if (ProcessOutputTypes.STDERR.equals(outputType)) {
													stdErrOutput.append(text);
												}
											}
										}
									});
									processHandler.startNotify();
									final boolean terminated = processHandler.waitFor();
									if (terminated) {
										final int exitValue = processHandler.getProcess().exitValue();
										if (exitValue != 0) {
											final String msg;
											if (stdErrOutput.length() > 0) {
												msg = stdErrOutput.toString();
											}
											else {
												msg = "Abnormal build process termination: unknown error";
											}
											handler.handleFailure(sessionId, CmdlineProtoUtil.createFailure(msg, null));
										}
									}
									else {
										handler.handleFailure(sessionId, CmdlineProtoUtil.createFailure("Disconnected from build process", null));
									}
								}
								catch (Throwable e) {
									execFailure = e;
								}
								finally {
									myBuildsInProgress.remove(projectPath);
									if (myMessageDispatcher.getAssociatedChannel(sessionId) == null) {
										// either the connection has never been established (process not started or execution failed), or no messages were sent from the launched process.
										// in this case the session cannot be unregistered by the message dispatcher
										final BuilderMessageHandler unregistered = myMessageDispatcher.unregisterBuildMessageHandler(sessionId);
										if (unregistered != null) {
											if (execFailure != null) {
												unregistered.handleFailure(sessionId, CmdlineProtoUtil.createFailure(execFailure.getMessage(), execFailure));
											}
											unregistered.sessionTerminated(sessionId);
										}
									}
								}
							}
						});
					}
					catch (Throwable e) {
						final BuilderMessageHandler unregistered = myMessageDispatcher.unregisterBuildMessageHandler(sessionId);
						if (unregistered != null) {
							unregistered.handleFailure(sessionId, CmdlineProtoUtil.createFailure(e.getMessage(), e));
							unregistered.sessionTerminated(sessionId);
						}
					}
				}
			});

			return future;
		}
		catch (Throwable e) {
			handler.handleFailure(sessionId, CmdlineProtoUtil.createFailure(e.getMessage(), e));
			handler.sessionTerminated(sessionId);
		}

		return null;
	}

	public void runCommand(Runnable command) {
		myRequestsProcessor.submit(command);
	}

	private OSProcessHandler launchBuildProcess(Project project, UUID sessionId, CompileScope scope) throws ExecutionException {
		final Sdk defaultSdk = ProjectRootManager.getInstance(project).getProjectSdk();
		if (defaultSdk == null) {
			throw new ExecutionException("No SDK configured for this project.");
		}
		if (!(defaultSdk.getSdkType() instanceof RustSdkType)) {
			throw new ExecutionException("This project doesn't have a Rust SDK configured.");
		}
		final RustSdkData rustSdkData = (RustSdkData) defaultSdk.getSdkAdditionalData();
		final GeneralCommandLine cmdLine = new GeneralCommandLine();

		final RunConfiguration runConfig = scope.getUserData(CompileStepBeforeRun.RUN_CONFIGURATION);
		if (runConfig == null) {
			throw new ExecutionException("'Run Configuration' not found. If you're trying to compile without running, that's not yet supported");
		}
		final RustConfiguration rustConfiguration = (RustConfiguration) runConfig;
		final CompilerModuleExtension compilerModuleExtension = CompilerModuleExtension.getInstance(rustConfiguration.getModules()[0]);
		if (compilerModuleExtension == null) {
			throw new ExecutionException("Cannot find compiler module extension from module");
		}
		final String outputPathUrl = compilerModuleExtension.getCompilerOutputUrl();
		File outputPathFile = new File(outputPathUrl);
		if (!outputPathFile.exists()) {
			if (!outputPathFile.mkdirs()) {
				throw new ExecutionException("Cannot create output path '" + outputPathUrl + "'");
			}
		}

		cmdLine.setWorkDirectory(new File(project.getBasePath()));
		cmdLine.setExePath(rustSdkData.pathRustc);
		cmdLine.addParameter(rustConfiguration.mainFile);
		cmdLine.addParameters("-o", outputPathUrl);

		final Process process = cmdLine.createProcess();

		return new OSProcessHandler(process, null, mySystemCharset) {
			@Override
			protected boolean shouldDestroyProcessRecursively() {
				return true;
			}
		};
	}

	@Override
	public void initComponent() {

	}

	@Override
	public void disposeComponent() {
		stopListening();
	}

	@NotNull
	@Override
	public String getComponentName() {
		return RustBuildManager.class.getName();
	}

	private class MessageHandlerWrapper implements BuilderMessageHandler {
		private final BuilderMessageHandler myHandler;

		public MessageHandlerWrapper(BuilderMessageHandler handler) {
			myHandler = handler;
		}

		@Override
		public void buildStarted(UUID sessionId) {
			myHandler.buildStarted(sessionId);
		}

		@Override
		public void handleBuildMessage(Channel channel, UUID sessionId, CmdlineRemoteProto.Message.BuilderMessage msg) {
			myHandler.handleBuildMessage(channel, sessionId, msg);
		}

		@Override
		public void handleFailure(UUID sessionId, CmdlineRemoteProto.Message.Failure failure) {
			myHandler.handleFailure(sessionId, failure);
		}

		@Override
		public void sessionTerminated(UUID sessionId) {
			myHandler.sessionTerminated(sessionId);
		}
	}


	private static class ProjectData {
		final SequentialTaskExecutor taskQueue;
		private final Set<String> myChanged = new THashSet<String>();
		private final Set<String> myDeleted = new THashSet<String>();
		private long myNextEventOrdinal = 0L;
		private boolean myNeedRescan = true;

		private ProjectData(SequentialTaskExecutor taskQueue) {
			this.taskQueue = taskQueue;
		}

		public void addChanged(Collection<String> paths) {
			if (!myNeedRescan) {
				for (String path : paths) {
					myDeleted.remove(path);
					myChanged.add(path);
				}
			}
		}

		public void addDeleted(Collection<String> paths) {
			if (!myNeedRescan) {
				for (String path : paths) {
					myChanged.remove(path);
					myDeleted.add(path);
				}
			}
		}

		public CmdlineRemoteProto.Message.ControllerMessage.FSEvent createNextEvent() {
			final CmdlineRemoteProto.Message.ControllerMessage.FSEvent.Builder builder =
					CmdlineRemoteProto.Message.ControllerMessage.FSEvent.newBuilder();
			builder.setOrdinal(++myNextEventOrdinal);

			for (String path : myChanged) {
				builder.addChangedPaths(path);
			}
			myChanged.clear();

			for (String path : myDeleted) {
				builder.addDeletedPaths(path);
			}
			myDeleted.clear();

			return builder.build();
		}

		public boolean getAndResetRescanFlag() {
			final boolean rescan = myNeedRescan;
			myNeedRescan = false;
			return rescan;
		}

		public void dropChanges() {
			myNeedRescan = true;
			myNextEventOrdinal = 0L;
			myChanged.clear();
			myDeleted.clear();
		}
	}


}
