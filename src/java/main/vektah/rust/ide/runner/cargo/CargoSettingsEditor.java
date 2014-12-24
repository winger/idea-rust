package vektah.rust.ide.runner.cargo;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class CargoSettingsEditor extends SettingsEditor<CargoRunConfiguration> {
    private JComboBox<String> target;

    @Override
    protected void resetEditorFrom(CargoRunConfiguration cargoRunConfiguration) {

    }

    @Override
    protected void applyEditorTo(CargoRunConfiguration cargoRunConfiguration) throws ConfigurationException {

    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 2));

        panel.add(new JLabel("target:"));
        target = new JComboBox<String>(new String[] {
                "run",
                "build",
        });
        panel.add(target);

        return panel;
    }
}
