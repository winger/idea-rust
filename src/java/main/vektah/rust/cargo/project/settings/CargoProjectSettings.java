package vektah.rust.cargo.project.settings;

import com.intellij.openapi.externalSystem.settings.ExternalProjectSettings;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class CargoProjectSettings extends ExternalProjectSettings {
    List<String> features = Collections.singletonList("default");

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    protected void copyTo(@NotNull CargoProjectSettings receiver) {
        receiver.features = features;
        super.copyTo(receiver);
    }

    @NotNull
    @Override
    public ExternalProjectSettings clone() {
        CargoProjectSettings result = new CargoProjectSettings();
        copyTo(result);
        return result;
    }
}
