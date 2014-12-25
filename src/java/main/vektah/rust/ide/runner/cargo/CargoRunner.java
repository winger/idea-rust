package vektah.rust.ide.runner.cargo;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.GenericProgramRunner;
import com.intellij.execution.runners.RunContentBuilder;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CargoRunner <Settings extends com.intellij.execution.configurations.RunnerSettings> extends GenericProgramRunner<Settings> {
    @NotNull
    @Override
    public String getRunnerId() {
        return "cargo";
    }

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
        if (!(profile instanceof CargoRunConfiguration)) {
            return false;
        }

        // No debugging for now
        if (DefaultDebugExecutor.EXECUTOR_ID.equals(executorId)) {
            return false;
        }

        //Running is always available
        return DefaultRunExecutor.EXECUTOR_ID.equals(executorId);
    }

    @Nullable
    @Override
    protected RunContentDescriptor doExecute(
            @NotNull Project project,
            @NotNull RunProfileState state,
            @Nullable RunContentDescriptor descriptor,
            @NotNull ExecutionEnvironment environment) throws ExecutionException {

        ExecutionResult execute = state.execute(environment.getExecutor(), this);

        return new RunContentBuilder(this, execute, environment).showRunContent(null);
    }
}
