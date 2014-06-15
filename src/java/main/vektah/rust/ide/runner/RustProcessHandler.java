package vektah.rust.ide.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RustProcessHandler extends OSProcessHandler {
	public RustProcessHandler(@NotNull Process process, @Nullable String commandLine) {
		super(process, commandLine);
	}

	public static ProcessHandler runCommandLine(GeneralCommandLine commandLine) throws ExecutionException {
		final RustProcessHandler rustProcess = new RustProcessHandler(
				commandLine.createProcess(),
				commandLine.getCommandLineString());

		ProcessTerminatedListener.attach(rustProcess);
		return rustProcess;
	}
}
