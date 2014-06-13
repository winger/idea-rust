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
}
