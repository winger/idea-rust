package vektah.rust.ide.runner.cargo;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import vektah.rust.RustIcons;
import vektah.rust.i18n.RustBundle;

import javax.swing.*;

public class CargoConfigurationType implements ConfigurationType {
	public static final String ID = "CargoRunConfiguration";
	private final ConfigurationFactory configurationFactory;

	public CargoConfigurationType() {
		configurationFactory = new CargoConfigurationFactory(this);
	}

	@Override
	public String getDisplayName() {
		return RustBundle.message("runner.cargo.name");
	}

	@Override
	public String getConfigurationTypeDescription() {
		return RustBundle.message("runner.cargo.description");
	}

	@Override
	public Icon getIcon() {
		return RustIcons.ICON_CARGO_16;
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

	public static CargoConfigurationType getInstance() {
		return ContainerUtil.findInstance(Extensions.getExtensions(CONFIGURATION_TYPE_EP), CargoConfigurationType.class);
	}

	private class CargoConfigurationFactory extends ConfigurationFactory {
		public CargoConfigurationFactory(CargoConfigurationType cargoConfigurationType) {
			super(cargoConfigurationType);
		}

		@Override
		public RunConfiguration createTemplateConfiguration(Project project) {
			return new CargoRunConfiguration(project, this);
		}
	}
}
