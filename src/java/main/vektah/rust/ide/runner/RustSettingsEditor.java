package vektah.rust.ide.runner;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class RustSettingsEditor extends SettingsEditor<RustConfiguration> {
	private JPanel pnlMain;

	public RustSettingsEditor(Project project) {

	}

	@Override
	protected void resetEditorFrom(RustConfiguration s) {

	}

	@Override
	protected void applyEditorTo(RustConfiguration s) throws ConfigurationException {

	}

	@NotNull
	@Override
	protected JComponent createEditor() {
		return pnlMain;
	}

	@Override
	protected void disposeEditor() {
		super.disposeEditor();
		pnlMain.setVisible(false);
	}
}
