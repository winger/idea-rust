package vektah.rust.ide.runner;

import com.intellij.execution.configuration.ConfigurationFactoryEx;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import vektah.rust.RustIcons;
import vektah.rust.i18n.RustBundle;

import javax.swing.*;

public class RustConfigurationType implements ConfigurationType {
	public static final String ID = "RustRunConfiguration";
	private final ConfigurationFactory configurationFactory;

	public RustConfigurationType() {
		configurationFactory = new RustConfigurationFactory(this);
	}

	@Override
	public String getDisplayName() {
		return RustBundle.message("language.name.rust");
	}

	@Override
	public String getConfigurationTypeDescription() {
		return RustBundle.message("language.name.rust");
	}

	@Override
	public Icon getIcon() {
		return RustIcons.ICON_RUST_16;
	}

	@NotNull
	@Override
	public String getId() {
		return ID;
	}

	@Override
	public ConfigurationFactory[] getConfigurationFactories() {
		return new ConfigurationFactory[]{configurationFactory};
	}

	public static RustConfigurationType getInstance() {
		return ContainerUtil.findInstance(Extensions.getExtensions(CONFIGURATION_TYPE_EP), RustConfigurationType.class);
	}

	private class RustConfigurationFactory extends ConfigurationFactoryEx {
		public RustConfigurationFactory(RustConfigurationType rustConfigurationType) {
			super(rustConfigurationType);
		}

		@Override
		public RunConfiguration createTemplateConfiguration(Project project) {
			return new RustConfiguration(RustBundle.message("language.name.rust"), project, getInstance());
		}

		@Override
		public void onNewConfigurationCreated(@NotNull RunConfiguration configuration) {
			((ModuleBasedConfiguration)configuration).onNewConfigurationCreated();
		}
	}
}
