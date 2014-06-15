package vektah.rust.ide.runner;

import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class RustRunner extends DefaultProgramRunner {
	@NonNls
	public static final String RUNNER_ID = "Rust";

	@NotNull
	@Override
	public String getRunnerId() {
		return RUNNER_ID;
	}

	@Override
	public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
		if (!(profile instanceof RustConfiguration)) {
			return false;
		}

		// No debugging for now
		if (DefaultDebugExecutor.EXECUTOR_ID.equals(executorId)) {
			return false;
		}

		//Running is always available
		return DefaultRunExecutor.EXECUTOR_ID.equals(executorId);
	}
}
