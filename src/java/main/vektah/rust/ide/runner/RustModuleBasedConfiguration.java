package vektah.rust.ide.runner;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;

class RustModuleBasedConfiguration extends RunConfigurationModule {
    public RustModuleBasedConfiguration(Project project) {
        super(project);
    }
}
