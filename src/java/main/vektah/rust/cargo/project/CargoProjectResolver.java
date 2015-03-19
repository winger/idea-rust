package vektah.rust.cargo.project;

import com.google.gson.Gson;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.*;
import com.intellij.openapi.externalSystem.model.DataNode;
import com.intellij.openapi.externalSystem.model.ExternalSystemException;
import com.intellij.openapi.externalSystem.model.ProjectKeys;
import com.intellij.openapi.externalSystem.model.project.*;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskId;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskNotificationEvent;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskNotificationListener;
import com.intellij.openapi.externalSystem.service.project.ExternalSystemProjectResolver;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VfsUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;
import vektah.rust.cargo.Cargo;
import vektah.rust.cargo.project.model.CargoPackageInfo;
import vektah.rust.cargo.project.model.CargoProjectInfo;
import vektah.rust.cargo.project.settings.CargoExecutionSettings;
import vektah.rust.ide.module.RustModuleType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CargoProjectResolver implements ExternalSystemProjectResolver<CargoExecutionSettings> {
    @Nullable
    @Override
    public DataNode<ProjectData> resolveProjectInfo(
        final ExternalSystemTaskId id,
        String projectPath,
        boolean isPreviewMode,
        CargoExecutionSettings settings,
        final ExternalSystemTaskNotificationListener listener) throws ExternalSystemException, IllegalArgumentException, IllegalStateException {
        listener.onStatusChange(new ExternalSystemTaskNotificationEvent(id, "Resolving dependencies..."));
        CargoProjectInfo data;
        try {
            GeneralCommandLine cmd = new GeneralCommandLine();
            cmd.setExePath(settings.getCargoExecutable());
            cmd.addParameter("export");
            cmd.addParameters("--output-format", "json");
            cmd.addParameters("--manifest-path", new File(projectPath, Cargo.BUILD_FILE).getAbsolutePath());
            cmd.addParameters("--features", StringUtils.join(settings.getFeatures(), ","));
            Process process = cmd.createProcess();
            CapturingProcessHandler handler = new CapturingProcessHandler(process);
            handler.addProcessListener(
                new ProcessAdapter() {
                    @Override
                    public void onTextAvailable(ProcessEvent event, Key outputType) {
                        if (outputType == ProcessOutputTypes.STDERR && (
                            event.getText().startsWith("    Updating") ||
                            event.getText().startsWith(" Downloading"))) {
                            listener.onStatusChange(new ExternalSystemTaskNotificationEvent(id, event.getText().trim()));
                        } else {
                            listener.onTaskOutput(id, event.getText(), outputType == ProcessOutputTypes.STDOUT);
                        }
                    }
                });
            ProcessOutput output = handler.runProcess();
            if (output.getExitCode() != 0) {
                throw new ExternalSystemException("Failed to execute cargo: " + output.getStderr());
            }
            data = new Gson().fromJson(output.getStdout(), CargoProjectInfo.class);
        } catch (ExecutionException e) {
            throw new ExternalSystemException(e);
        }
        DataNode<ProjectData> projectNode = new DataNode<ProjectData>(
            ProjectKeys.PROJECT,
            new ProjectData(CargoProjectSystem.ID, data.root, projectPath, projectPath),
            null
        );
        File projectRoot = new File(projectPath);
        HashMap<String, DataNode<?>> moduleOrLibrary = new HashMap<String, DataNode<?>>();
        for (Map.Entry<String, CargoPackageInfo> entry : data.packages.entrySet()) {
            final String name = entry.getKey();
            final CargoPackageInfo pkg = entry.getValue();
            final File packageRoot = new File(pkg.path);
            if (VfsUtil.isAncestor(projectRoot, packageRoot, false)) {
                // Create module
                ModuleData moduleData = new ModuleData(
                    name,
                    CargoProjectSystem.ID,
                    RustModuleType.MODULE_TYPE_ID,
                    name,
                    packageRoot.getAbsolutePath(),
                    projectPath);
                DataNode<ModuleData> moduleNode = projectNode.createChild(ProjectKeys.MODULE, moduleData);
                moduleOrLibrary.put(name, moduleNode);
                addSourceRoot(moduleNode, pkg.path);
            } else {
                // Create library
                LibraryData libraryData = new LibraryData(CargoProjectSystem.ID, name + " " + pkg.version);
                libraryData.addPath(LibraryPathType.BINARY, pkg.path);
                DataNode<LibraryData> libraryNode = projectNode.createChild(ProjectKeys.LIBRARY, libraryData);
                moduleOrLibrary.put(name, libraryNode);
            }
        }
        // Add dependencies
        for (Map.Entry<String, CargoPackageInfo> entry : data.packages.entrySet()) {
            final String name = entry.getKey();
            final CargoPackageInfo pkg = entry.getValue();
            DataNode<?> pkgNode = moduleOrLibrary.get(name);
            if (!pkgNode.getKey().equals(ProjectKeys.MODULE)) {
                // Skip dependencies for libraries
                continue;
            }
            for (String dependency : pkg.dependencies) {
                DataNode<?> dependencyNode = moduleOrLibrary.get(dependency);
                if (dependencyNode.getKey().equals(ProjectKeys.MODULE)) {
                    pkgNode.createChild(
                        ProjectKeys.MODULE_DEPENDENCY,
                        new ModuleDependencyData(
                            (ModuleData) pkgNode.getData(),
                            (ModuleData) dependencyNode.getData()));
                } else if (dependencyNode.getKey().equals(ProjectKeys.LIBRARY)) {
                    pkgNode.createChild(
                        ProjectKeys.LIBRARY_DEPENDENCY,
                        new LibraryDependencyData(
                            (ModuleData) pkgNode.getData(),
                            (LibraryData) dependencyNode.getData(),
                            LibraryLevel.PROJECT));
                } else {
                    throw new AssertionError("unreachable");
                }
            }
        }
        return projectNode;
    }

    private void addSourceRoot(DataNode<ModuleData> node, String path) {
        node.createChild(ProjectKeys.CONTENT_ROOT, new ContentRootData(CargoProjectSystem.ID, path));
    }

    @Override
    public boolean cancelTask(ExternalSystemTaskId taskId, ExternalSystemTaskNotificationListener listener) {
        return false;
    }
}
