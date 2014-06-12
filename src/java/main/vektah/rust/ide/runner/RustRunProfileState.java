package vektah.rust.ide.runner;

import com.intellij.execution.CantRunException;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;
import vektah.rust.i18n.RustBundle;
import vektah.rust.ide.sdk.RustSdkData;
import vektah.rust.ide.sdk.RustSdkUtil;

import java.io.File;
import java.util.Map;

public class RustRunProfileState extends CommandLineState {
	private final Project project;
	private final RustConfiguration rustConfiguration;

	protected RustRunProfileState(Project project, ExecutionEnvironment environment, RustConfiguration rustConfiguration) {
		super(environment);

		this.project = project;
		this.rustConfiguration = rustConfiguration;
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

		// Build and run
//		String execName = rustConfiguration.outputDir.concat("/").concat(rustConfiguration.getName());
		String execName = "/tmp".concat("/").concat(rustConfiguration.getName());

		if (execName.endsWith(".rs")) {
			execName = execName.substring(0, execName.length() - 3);
		}

		if (SystemInfo.isWindows) {
			execName = execName.concat(".exe");
		}

		buildProject(sdkData, execName, projectDir);

		// Now run the build
		GeneralCommandLine commandLine = new GeneralCommandLine();

		commandLine.setExePath(execName);
//		commandLine.setWorkDirectory(rustConfiguration.workingDir);
//		if (rustConfiguration.scriptArguments != null && rustConfiguration.scriptArguments.trim().length() > 0) {
//			commandLine.getParametersList().addParametersString(rustConfiguration.scriptArguments);
//		}
		commandLine.setWorkDirectory("/tmp");

		return RustProcessHandler.runCommandLine(commandLine);
	}


	private void buildProject(RustSdkData sdkData, String execName, String projectDir) throws CantRunException {
		RustToolWindow toolWindow = RustToolWindow.getInstance(project);
		toolWindow.setTitle(RustBundle.message("rust.ui.title.build"));

		try {
//			String[] goEnv = GoSdkUtil.convertEnvMapToArray(sysEnv);

//			String[] command = RustSdkUtil.computeBuildCommand(sdkData, rustConfiguration.runBuilderArguments, execName, rustConfiguration.scriptName);
			String[] command = RustSdkUtil.computeBuildCommand(sdkData, "", execName, "src/main.rs");

			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(command, new String[]{}, new File(projectDir));
			OSProcessHandler handler = new OSProcessHandler(proc, null);
			toolWindow.attachConsoleViewToProcess(handler);
			toolWindow.printNormalMessage(String.format("%s%n", StringUtil.join(command, " ")));
			handler.startNotify();

			if (proc.waitFor() == 0) {
				VirtualFileManager.getInstance().syncRefresh();
				toolWindow.printNormalMessage(String.format("%nFinished building project %s%n", execName));
			} else {
				toolWindow.printErrorMessage(String.format("%nCould't build project %s%n", execName));
				throw new CantRunException(String.format("Error while processing build command."));
			}
		} catch (Exception e) {
			throw new CantRunException(String.format("Error while processing build command."));
		}
	}
}
