package vektah.rust.cargo.project;

import com.intellij.openapi.externalSystem.service.settings.AbstractExternalProjectSettingsControl;
import com.intellij.openapi.externalSystem.service.settings.AbstractImportFromExternalSystemControl;
import com.intellij.openapi.externalSystem.util.ExternalSystemSettingsControl;
import com.intellij.openapi.externalSystem.util.PaintAwarePanel;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.cargo.project.settings.CargoProjectSettings;
import vektah.rust.cargo.project.settings.CargoProjectSettingsListener;
import vektah.rust.cargo.project.settings.CargoSettings;

public class CargoImportControl
    extends AbstractImportFromExternalSystemControl<CargoProjectSettings, CargoProjectSettingsListener, CargoSettings> {

    protected CargoImportControl() {
        super(
            CargoProjectSystem.ID,
            new CargoSettings(ProjectManager.getInstance().getDefaultProject()),
            new CargoProjectSettings());
    }

    @Override
    protected void onLinkedProjectPathChange(String s) {}

    @NotNull
    @Override
    protected ExternalSystemSettingsControl<CargoProjectSettings> createProjectSettingsControl(
        CargoProjectSettings settings) {
        return new AbstractExternalProjectSettingsControl<CargoProjectSettings>(settings) {
            @Override
            protected void fillExtraControls(@NotNull PaintAwarePanel paintAwarePanel, int identLevel) {}

            @Override
            protected boolean isExtraSettingModified() {
                return false;
            }

            @Override
            protected void resetExtraSettings(boolean b) {}

            @Override
            protected void applyExtraSettings(@NotNull CargoProjectSettings settings) {

            }

            @Override
            public boolean validate(@NotNull CargoProjectSettings settings) throws ConfigurationException {
                return true;
            }
        };
    }

    @Nullable
    @Override
    protected ExternalSystemSettingsControl<CargoSettings> createSystemSettingsControl(@NotNull CargoSettings systemSettings) {
        return new ExternalSystemSettingsControl<CargoSettings>() {
            @Override
            public void fillUi(@NotNull PaintAwarePanel paintAwarePanel, int identLevel) {}

            @Override
            public void reset() {}

            @Override
            public boolean isModified() {
                return false;
            }

            @Override
            public void apply(@NotNull CargoSettings settings) {}

            @Override
            public boolean validate(@NotNull CargoSettings settings) throws ConfigurationException {
                return true;
            }

            @Override
            public void disposeUIResources() {}

            @Override
            public void showUi(boolean show) {}
        };
    }
}
