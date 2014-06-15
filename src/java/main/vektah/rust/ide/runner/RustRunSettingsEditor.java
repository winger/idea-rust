package vektah.rust.ide.runner;

import com.intellij.execution.configurations.ConfigurationUtil;
import com.intellij.execution.ui.ConfigurationModuleSelector;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.psi.JavaCodeFragment;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiMethodUtil;
import com.intellij.ui.EditorTextFieldWithBrowseButton;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.ide.ui.RustParametersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RustRunSettingsEditor extends SettingsEditor<RustConfiguration> implements PanelWithAnchor {
	private final Project project;

	private RustParametersPanel rustParametersPanel;
	private LabeledComponent<EditorTextFieldWithBrowseButton> cmbMainFile;
	private LabeledComponent<JComboBox> cmbModule;
	private JPanel pnlMain;

	private JComponent anchor;

	private final ConfigurationModuleSelector moduleSelector;

	public RustRunSettingsEditor(Project project) {
		this.project = project;

		moduleSelector = new ConfigurationModuleSelector(project, cmbModule.getComponent());
		rustParametersPanel.setModuleContext(moduleSelector.getModule());
		cmbModule.getComponent().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				rustParametersPanel.setModuleContext(moduleSelector.getModule());
			}
		});

		anchor = UIUtil.mergeComponentsWithAnchor(cmbMainFile, rustParametersPanel, cmbModule);
	}

	@Override
	protected void resetEditorFrom(RustConfiguration configuration) {
		rustParametersPanel.reset(configuration);
		moduleSelector.reset(configuration);
		getMainFileField().setText(configuration.mainFile);
	}

	@Override
	protected void applyEditorTo(RustConfiguration configuration) throws ConfigurationException {
		rustParametersPanel.applyTo(configuration);
		moduleSelector.applyTo(configuration);
		configuration.mainFile = getMainFileField().getText();
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

	@Override
	public JComponent getAnchor() {
		return anchor;
	}

	@Override
	public void setAnchor(@Nullable JComponent anchor) {
		this.anchor = anchor;
		cmbMainFile.setAnchor(anchor);
		cmbModule.setAnchor(anchor);
		rustParametersPanel.setAnchor(anchor);
	}

	private void createUIComponents() {
		cmbMainFile = new LabeledComponent<EditorTextFieldWithBrowseButton>();
		cmbMainFile.setComponent(new EditorTextFieldWithBrowseButton(project, true, new JavaCodeFragment.VisibilityChecker() {
			@Override
			public Visibility isDeclarationVisible(PsiElement declaration, PsiElement place) {
//				if (declaration instanceof PsiClass) {
//					final PsiClass aClass = (PsiClass)declaration;
//					if (ConfigurationUtil.MAIN_CLASS.value(aClass) && PsiMethodUtil.findMainMethod(aClass) != null) {
//						return Visibility.VISIBLE;
//					}
//				}
				return Visibility.VISIBLE;
			}
		}));
	}

	public EditorTextFieldWithBrowseButton getMainFileField() {
		return cmbMainFile.getComponent();
	}
}
