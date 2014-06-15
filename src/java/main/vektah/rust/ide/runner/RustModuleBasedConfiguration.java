package vektah.rust.ide.runner;

import com.google.common.base.Strings;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.execution.configurations.RuntimeConfigurationError;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.openapi.project.Project;

class RustModuleBasedConfiguration extends RunConfigurationModule {
	public RustModuleBasedConfiguration(Project project) {
		super(project);
	}

	public void checkModuleAndFile(final String fileName, final String expectedFileMessage) throws RuntimeConfigurationException {
		checkForWarning();
		checkFile(fileName, expectedFileMessage);
	}

	private void checkFile(String fileName, String expectedFileMessage) throws RuntimeConfigurationError {
		if (Strings.isNullOrEmpty(fileName)) {
			throw new RuntimeConfigurationError(expectedFileMessage);
		}
		findNotNullFile(fileName);
	}

	private void findNotNullFile(String fileName) {
		// FIXME
	}
}
