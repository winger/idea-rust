package vektah.rust.ide.sdk;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.SystemInfo;

import java.io.File;
import java.nio.charset.Charset;

public class RustSdkUtil {
	private static final Logger LOG = Logger.getInstance(RustSdkUtil.class.getName());

	public static RustSdkData getCompilerInfo(File rustc) {
        final RustSdkData sdkData = new RustSdkData(rustc.getAbsolutePath());

		try {
			GeneralCommandLine command = new GeneralCommandLine();
			command.setExePath(rustc.getAbsolutePath());
			command.addParameter("--version");
			command.setWorkDirectory(rustc.getParent());

			ProcessOutput output = new CapturingProcessHandler(
					command.createProcess(),
					Charset.defaultCharset(),
					command.getCommandLineString()).runProcess();

			if (output.getExitCode() != 0) {
				LOG.error(
                    "rustc exited with invalid exit code: " +
                    output.getExitCode() + "\n" +
                    output.getStdout()
                );
				return null;
			}

			String[] lines = output.getStdout().split("\n");
			if (lines.length > 2 || lines.length == 0) {
				LOG.error("invalid rustc output: expected 1 or 2 lines, got " + lines.length);
				return null;
			}
			String[] line1 = lines[0].split(" ");
			if (!line1[0].equals("rustc")) {
				LOG.error("invalid rustc output: expected 'rustc', got " + line1[0]);
				return null;
			}
			sdkData.version = line1[1];
		} catch (Exception e) {
			LOG.error("Exception while executing the process:", e);
			return null;
		}
		return sdkData;
	}

	public static RustSdkData testRustSdk(String path) {
		File parent = new File(path);
		if (!parent.exists()) {
            LOG.error("Directory does not exist.");
			return null;
		}
		File rustc = new File(parent, SystemInfo.isWindows ? "rustc.exe" : "rustc");
        if (!rustc.exists()) {
            LOG.error(rustc.getAbsolutePath() + " does not exist");
            return null;
        }

        if (!rustc.isFile()) {
            LOG.error(rustc.getAbsolutePath() + " is not a file");
            return null;
        }

        if (!rustc.canExecute()) {
            LOG.error(rustc.getAbsolutePath() + " is not executable");
            return null;
        }

        RustSdkData result = RustSdkUtil.getCompilerInfo(rustc);
        // Get version and host info
        if (result == null) {
            LOG.error("Unable to parse compiler info from rust binary.");
            return null;
        }

        return result;
	}

	public static Sdk getSdk(Project project) {
		Sdk sdk = ProjectRootManager.getInstance(project).getProjectSdk();

		if (sdk != null && sdk.getSdkType() == RustSdkType.getInstance()) {
			return sdk;
		}

		return null;
	}

	public static String[] computeBuildCommand(RustSdkData sdkData, String buildArgs, String execName, String scriptName) {
		return new String[] {
				sdkData.pathRustc,
				scriptName,
				"-o",
				execName
		};
	}
}
