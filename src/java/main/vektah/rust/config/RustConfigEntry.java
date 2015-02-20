package vektah.rust.config;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import vektah.rust.i18n.RustBundle;

import javax.swing.*;

public class RustConfigEntry implements Configurable {
	private RustConfigForm form;
	private RustConfigService configService = ServiceManager.getService(RustConfigService.class);
	private RustConfig config = configService.getState();

	@Nls
	@Override
	public String getDisplayName() {
		return RustBundle.message("config.name");
	}

	@Nullable
	@Override
	public String getHelpTopic() {
		return RustBundle.message("config.help-topic");
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		return form = new RustConfigForm();
	}

	@Override
	public boolean isModified() {
		return !config.getCargoBinary().equals(form.getCargoBinary());
	}

	@Override
	public void apply() throws ConfigurationException {
		config.setCargoBinary(form.getCargoBinary());
	}

	@Override
	public void reset() {
		form.setCargoBinary(config.getCargoBinary());
	}

	@Override
	public void disposeUIResources() {
		form = null;
	}
}
