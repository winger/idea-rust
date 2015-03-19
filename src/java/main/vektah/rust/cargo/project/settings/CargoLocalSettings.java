package vektah.rust.cargo.project.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.externalSystem.service.project.PlatformFacade;
import com.intellij.openapi.externalSystem.settings.AbstractExternalSystemLocalSettings;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import vektah.rust.cargo.project.CargoProjectSystem;

@State(
    name = "CargoLocalSettings",
    storages = {
        @Storage(file = StoragePathMacros.WORKSPACE_FILE)
    }
)
public class CargoLocalSettings extends AbstractExternalSystemLocalSettings
    implements PersistentStateComponent<AbstractExternalSystemLocalSettings.State> {
    protected CargoLocalSettings(Project project, PlatformFacade facade) {
        super(CargoProjectSystem.ID, project, facade);
    }

    @Nullable
    @Override
    public State getState() {
        AbstractExternalSystemLocalSettings.State state = new AbstractExternalSystemLocalSettings.State();
        fillState(state);
        return state;
    }

    public static CargoLocalSettings getInstance(Project project) {
        return ServiceManager.getService(project, CargoLocalSettings.class);
    }
}
