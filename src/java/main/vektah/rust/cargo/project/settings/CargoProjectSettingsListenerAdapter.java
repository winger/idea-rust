package vektah.rust.cargo.project.settings;

import com.intellij.openapi.externalSystem.settings.DelegatingExternalSystemSettingsListener;
import com.intellij.openapi.externalSystem.settings.ExternalSystemSettingsListener;

public class CargoProjectSettingsListenerAdapter
    extends DelegatingExternalSystemSettingsListener<CargoProjectSettings>
    implements CargoProjectSettingsListener {
    public CargoProjectSettingsListenerAdapter(ExternalSystemSettingsListener<CargoProjectSettings> delegate) {
        super(delegate);
    }
}
