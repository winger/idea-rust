package vektah.rust.cargo.project;

import com.google.gson.Gson;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.SimpleJavaParameters;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.externalSystem.ExternalSystemAutoImportAware;
import com.intellij.openapi.externalSystem.ExternalSystemManager;
import com.intellij.openapi.externalSystem.model.ProjectSystemId;
import com.intellij.openapi.externalSystem.service.project.ExternalSystemProjectResolver;
import com.intellij.openapi.externalSystem.service.project.autoimport.CachingExternalSystemAutoImportAware;
import com.intellij.openapi.externalSystem.task.ExternalSystemTaskManager;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Function;
import com.intellij.util.PathUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.cargo.project.settings.*;
import vektah.rust.config.RustConfigService;

import java.net.URL;
import java.util.List;

public class CargoExternalSystemManager implements ExternalSystemAutoImportAware,
    ExternalSystemManager<CargoProjectSettings, CargoProjectSettingsListener, CargoSettings, CargoLocalSettings, CargoExecutionSettings> {

    @NotNull
    @Override
    public ProjectSystemId getSystemId() {
        return CargoProjectSystem.ID;
    }

    @NotNull
    @Override
    public Function<Project, CargoSettings> getSettingsProvider() {
        return new Function<Project, CargoSettings>() {
            @Override
            public CargoSettings fun(Project project) {
                return CargoSettings.getInstance(project);
            }
        };
    }

    @NotNull
    @Override
    public Function<Project, CargoLocalSettings> getLocalSettingsProvider() {
        return new Function<Project, CargoLocalSettings>() {
            @Override
            public CargoLocalSettings fun(Project project) {
                return CargoLocalSettings.getInstance(project);
            }
        };
    }

    @NotNull
    @Override
    public Function<Pair<Project, String>, CargoExecutionSettings> getExecutionSettingsProvider() {
        return new Function<Pair<Project, String>, CargoExecutionSettings>() {
            @Override
            public CargoExecutionSettings fun(Pair<Project, String> pair) {
                return executionSettingsFor(pair.getFirst(), pair.getSecond());
            }
        };
    }

    @NotNull
    @Override
    public Class<? extends ExternalSystemProjectResolver<CargoExecutionSettings>> getProjectResolverClass() {
        return CargoProjectResolver.class;
    }

    @Override
    public Class<? extends ExternalSystemTaskManager<CargoExecutionSettings>> getTaskManagerClass() {
        return CargoTaskManager.class;
    }

    @NotNull
    @Override
    public FileChooserDescriptor getExternalProjectDescriptor() {
        return new CargoOpenProjectDescriptor();
    }

    private CachingExternalSystemAutoImportAware delegate = new CachingExternalSystemAutoImportAware(new CargoAutoImport());

    @Nullable
    @Override
    public String getAffectedExternalProjectPath(String changedFileOrDirPath, Project project) {
        return delegate.getAffectedExternalProjectPath(changedFileOrDirPath, project);
    }

    @Override
    public void enhanceRemoteProcessing(@NotNull SimpleJavaParameters parameters) throws ExecutionException {
        parameters.getClassPath().add(PathUtil.getJarPathForClass(StringUtils.class));
        parameters.getClassPath().add(PathUtil.getJarPathForClass(Gson.class));
//        parameters.getVMParametersList().add("-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005");
    }

    @Override
    public void enhanceLocalProcessing(@NotNull List<URL> urls) {
    }

    static CargoExecutionSettings executionSettingsFor(Project project, String path) {
        return new CargoExecutionSettings(
            ServiceManager.getService(RustConfigService.class)
                          .getState()
                          .getCargoBinary(),
            ServiceManager.getService(project, CargoProjectSettings.class).getFeatures());
    }
}
