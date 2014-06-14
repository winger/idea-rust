package vektah.rust.ide.runner;

import com.intellij.execution.CantRunException;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.SimpleProgramParameters;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.util.ProgramParametersUtil;
import com.intellij.openapi.compiler.CompilerPaths;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.SystemInfo;
import org.jetbrains.annotations.NotNull;
import vektah.rust.ide.sdk.RustSdkData;
import vektah.rust.ide.sdk.RustSdkUtil;

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

		final SimpleProgramParameters params = new SimpleProgramParameters();
		ProgramParametersUtil.configureConfiguration(params, rustConfiguration);

		String outputPath = CompilerPaths.getModuleOutputPath(rustConfiguration.getModules()[0], false);
		if (outputPath == null) {
			throw new CantRunException("Could not retrieve the output directory");
		}

		// Build and run
		String execName = outputPath.concat("/").concat(rustConfiguration.getName());

		if (execName.endsWith(".rs")) {
			execName = execName.substring(0, execName.length() - 3);
		}

		if (SystemInfo.isWindows) {
			execName = execName.concat(".exe");
		}

		// Now run the build
		GeneralCommandLine commandLine = new GeneralCommandLine();

		commandLine.setExePath(execName);
		commandLine.addParameters(params.getProgramParametersList().getParameters());
		commandLine.getEnvironment().putAll(params.getEnv());
		commandLine.setWorkDirectory(params.getWorkingDirectory());

		return RustProcessHandler.runCommandLine(commandLine);
	}
}
