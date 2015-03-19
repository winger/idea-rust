package vektah.rust.cargo.project;

import com.intellij.openapi.externalSystem.model.ExternalSystemException;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskId;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskNotificationListener;
import com.intellij.openapi.externalSystem.task.AbstractExternalSystemTaskManager;
import vektah.rust.cargo.project.settings.CargoExecutionSettings;

import java.util.List;

public class CargoTaskManager extends AbstractExternalSystemTaskManager<CargoExecutionSettings> {
    @Override
    public void executeTasks(
        ExternalSystemTaskId id,
        List<String> taskNames,
        String projectPath,
        CargoExecutionSettings settings,
        List<String> vmOptions,
        List<String> scriptParameters,
        String debuggerSetup,
        ExternalSystemTaskNotificationListener listener) throws ExternalSystemException {

    }

    @Override
    public boolean cancelTask(
        ExternalSystemTaskId id, ExternalSystemTaskNotificationListener listener) throws ExternalSystemException {
        return false;
    }
}
