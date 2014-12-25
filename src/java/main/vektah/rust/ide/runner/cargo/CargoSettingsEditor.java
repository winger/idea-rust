package vektah.rust.ide.runner.cargo;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;
import vektah.rust.i18n.RustBundle;

import javax.swing.*;
import java.awt.*;

public class CargoSettingsEditor extends SettingsEditor<CargoRunConfiguration> {
    private static String BUILD_TARGET = RustBundle.message("runner.cargo.target.build");
    private static String RUN_TARGET = RustBundle.message("runner.cargo.target.run");
    private JComboBox<String> target;
    private JTextField workingDir;

    @Override
    protected void resetEditorFrom(CargoRunConfiguration config) {
        if (config.getTarget() == CargoTarget.BUILD) {
            target.setSelectedItem(BUILD_TARGET);
        } else {
            target.setSelectedItem(RUN_TARGET);
        }

        workingDir.setText(config.getWorkingDirectory());
    }

    @Override
    protected void applyEditorTo(CargoRunConfiguration config) throws ConfigurationException {
        if (target.getSelectedItem().equals(BUILD_TARGET)) {
            config.setTarget(CargoTarget.BUILD);
        } else {
            config.setTarget(CargoTarget.RUN);
        }

        config.setWorkingDirectory(workingDir.getText());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 2));

        panel.add(new JLabel(RustBundle.message("runner.cargo.label.target")));
        panel.add(target = new JComboBox<String>(new String[] {
            RUN_TARGET,
            BUILD_TARGET,
        }));

        panel.add(new JLabel(RustBundle.message("runner.cargo.label.workdir")));
        panel.add(workingDir = new JTextField());

        return panel;
    }
}
