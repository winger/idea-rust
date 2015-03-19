package vektah.rust.cargo.project.settings;

import com.intellij.openapi.externalSystem.model.settings.ExternalSystemExecutionSettings;

import java.util.List;

public class CargoExecutionSettings extends ExternalSystemExecutionSettings {
    private String cargoExecutable;
    private List<String> features;

    public CargoExecutionSettings(String cargoExecutable, List<String> features) {
        this.cargoExecutable = cargoExecutable;
        this.features = features;
    }

    public String getCargoExecutable() {
        return cargoExecutable;
    }

    public void setCargoExecutable(String cargoExecutable) {
        this.cargoExecutable = cargoExecutable;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        CargoExecutionSettings that = (CargoExecutionSettings) o;

        if (cargoExecutable != null ? !cargoExecutable.equals(that.cargoExecutable) : that.cargoExecutable != null) {
            return false;
        }
        if (features != null ? !features.equals(that.features) : that.features != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cargoExecutable != null ? cargoExecutable.hashCode() : 0);
        result = 31 * result + (features != null ? features.hashCode() : 0);
        return result;
    }
}
