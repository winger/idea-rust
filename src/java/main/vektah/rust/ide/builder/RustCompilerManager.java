package vektah.rust.ide.builder;

import com.intellij.compiler.CompilerConfigurationImpl;
import com.intellij.compiler.CompilerManagerImpl;
import com.intellij.openapi.compiler.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RustCompilerManager extends CompilerManagerImpl {
	private final Project project;
	private final CompilationStatusListener myEventPublisher;

	public RustCompilerManager(Project project, CompilerConfigurationImpl compilerConfiguration, MessageBus messageBus) {
		super(project, compilerConfiguration, messageBus);

		this.project = project;
		this.myEventPublisher = messageBus.syncPublisher(CompilerTopics.COMPILATION_STATUS);
	}

	@Override
	public void make(@NotNull CompileScope scope, CompileStatusNotification callback) {
		new RustCompilerDriver(project).make(scope, new ListenerNotificator(callback));
	}

	private class ListenerNotificator implements CompileStatusNotification {
		private final @Nullable
		CompileStatusNotification myDelegate;

		private ListenerNotificator(@Nullable CompileStatusNotification delegate) {
			myDelegate = delegate;
		}

		public void finished(boolean aborted, int errors, int warnings, final CompileContext compileContext) {
			myEventPublisher.compilationFinished(aborted, errors, warnings, compileContext);
			if (myDelegate != null) {
				myDelegate.finished(aborted, errors, warnings, compileContext);
			}
		}
	}
}
