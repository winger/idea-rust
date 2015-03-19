package vektah.rust.cargo.project.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.externalSystem.settings.AbstractExternalSystemSettings;
import com.intellij.openapi.externalSystem.settings.ExternalSystemSettingsListener;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.ContainerUtilRt;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@State(
    name = "CargoSettings",
    storages = {
        @Storage(file = StoragePathMacros.PROJECT_FILE),
        @Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/cargo.xml", scheme = StorageScheme.DIRECTORY_BASED)
    }
)
public class CargoSettings
    extends AbstractExternalSystemSettings<CargoSettings, CargoProjectSettings, CargoProjectSettingsListener>
    implements PersistentStateComponent<CargoSettingsState> {

    public CargoSettings(Project project) {
        super(CargoTopic.INSTANCE, project);
    }

    @Override
    public void subscribe(ExternalSystemSettingsListener<CargoProjectSettings> listener) {
        CargoProjectSettingsListenerAdapter adapter = new CargoProjectSettingsListenerAdapter(listener);
        getProject().getMessageBus().connect(getProject()).subscribe(CargoTopic.INSTANCE, adapter);
    }

    @Nullable
    @Override
    public CargoSettingsState getState() {
        CargoSettingsState state = new CargoSettingsState();
        fillState(state);
        return state;
    }

    @Override
    public void loadState(CargoSettingsState state) {
        super.loadState(state);
    }

    @Override
    protected void copyExtraSettingsFrom(CargoSettings settings) {}

    @Override
    protected void checkSettings(CargoProjectSettings settings1, CargoProjectSettings settings2) {}

    public static CargoSettings getInstance(Project project) {
        return ServiceManager.getService(project, CargoSettings.class);
    }
}

class CargoSettingsState implements AbstractExternalSystemSettings.State<CargoProjectSettings> {
    private Set<CargoProjectSettings> linkedExternalProjects = ContainerUtilRt.newHashSet();

    @AbstractCollection(surroundWithTag = false, elementTypes = {CargoProjectSettings.class})
    @Override
    public Set<CargoProjectSettings> getLinkedExternalProjectsSettings() {
        return linkedExternalProjects;
    }

    @Override
    public void setLinkedExternalProjectsSettings(Set<CargoProjectSettings> set) {
        linkedExternalProjects = set;
    }
}