package vektah.rust.ide.runner.cargo;

import com.intellij.execution.CantRunException;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import org.jetbrains.annotations.NotNull;
import vektah.rust.ide.runner.RustProcessHandler;
import vektah.rust.ide.sdk.CargoUtil;
import vektah.rust.ide.sdk.RustSdkData;
import vektah.rust.ide.sdk.RustSdkUtil;


public class CargoRunProfileState extends CommandLineState {
    private final Project project;
    private final CargoRunConfiguration config;

    protected CargoRunProfileState(Project project, ExecutionEnvironment environment, CargoRunConfiguration config) {
        super(environment);

        this.project = project;
        this.config = config;
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        final Sdk sdk = RustSdkUtil.getSdk(project);
        if ( sdk == null ) {
            throw new CantRunException("No Rust sdk defined for this project");
        }

        final RustSdkData sdkData = (RustSdkData)sdk.getSdkAdditionalData();
        if ( sdkData == null ) {
            throw new CantRunException("No Rust sdk defined for this project");
        }

        String projectDir = project.getBasePath();

        if (projectDir == null) {
            throw new CantRunException("Could not retrieve the project directory");
        }

        try {
            GeneralCommandLine commandLine = new GeneralCommandLine();

            commandLine.setExePath(CargoUtil.findCargoPath());

            if (config.getTarget() == CargoTarget.RUN) {
                commandLine.addParameter("run");
            } else if (config.getTarget() == CargoTarget.BUILD) {
                commandLine.addParameter("build");
            }

            commandLine.setWorkDirectory(config.getWorkingDirectory());

            return RustProcessHandler.runCommandLine(commandLine);
        }
        catch (Exception e) {
            throw new CantRunException(e.getMessage());
        }
    }

}
