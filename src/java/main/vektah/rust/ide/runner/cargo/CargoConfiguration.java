package vektah.rust.ide.runner.cargo;

import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.util.ProgramParametersUtil;
import com.intellij.openapi.project.Project;
import vektah.rust.ide.runner.RustConfiguration;

public class CargoConfiguration extends RustConfiguration {
    public CargoConfiguration(String name, Project project, CargoConfigurationType configurationType) {
        super(name, project, configurationType);
    }

    @Override
    protected ModuleBasedConfiguration createInstance() {
        return new CargoConfiguration(getName(), getProject(), CargoConfigurationType.getInstance());
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        final RunConfigurationModule configurationModule = getConfigurationModule();
        configurationModule.checkForWarning();//checkModuleAndFile(mainFile, RustBundle.message("settings.editor.configuration.error.no.file"));
        ProgramParametersUtil.checkWorkingDirectoryExist(this, getProject(), configurationModule.getModule());
    }
}
