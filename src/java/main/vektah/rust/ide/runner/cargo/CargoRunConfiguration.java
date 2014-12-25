package vektah.rust.ide.runner.cargo;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.ide.sdk.CargoUtil;

/**
 * This is the model backing an instance of a run configuration.
 */
public class CargoRunConfiguration extends RunConfigurationBase {
    private CargoTarget target = CargoTarget.RUN;
    private String workingDirectory;


    public CargoRunConfiguration(Project project, ConfigurationFactory factory) {
        super(project, factory, "Cargo");
        workingDirectory = CargoUtil.findCargoWorkingDirectory(project);
    }

    public CargoTarget getTarget() {
        return target;
    }

    public void setTarget(CargoTarget target) {
        this.target = target;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new CargoSettingsEditor();
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {

    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws ExecutionException {
        return new CargoRunProfileState(getProject(), env, this);
    }
}
