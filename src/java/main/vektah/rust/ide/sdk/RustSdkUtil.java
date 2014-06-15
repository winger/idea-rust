package vektah.rust.ide.sdk;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;
import java.nio.charset.Charset;

public class RustSdkUtil {
	private static final Logger LOG = Logger.getInstance(RustSdkUtil.class.getName());

	public static boolean getCompilerInfo(File rustc, RustSdkData sdkData) {
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
				LOG.error("rustc exited with invalid exit code: " +
						output.getExitCode());
				return false;
			}

			String[] lines = output.getStdout().split("\n");
			if (lines.length != 2) {
				LOG.error("invalid rustc output: expected 2 lines, got " + lines.length);
				return false;
			}
			String[] line1 = lines[0].split(" ");
			if (!line1[0].equals("rustc")) {
				LOG.error("invalid rustc output: expected 'rustc', got " + line1[0]);
				return false;
			}
			sdkData.version = line1[1];

			String[] line2 = lines[1].split(" ");
			if (!line2[0].equals("host:")) {
				LOG.error("invalid rustc output: expected 'host:', got " + line2[0]);
				return false;
			}
			sdkData.hostTriple = line2[1];
		} catch (Exception e) {
			LOG.error("Exception while executing the process:", e);
			return false;
		}
		return true;
	}

	public static RustSdkData testRustSdk(String path) {
		File parent = new File(path);
		if (!parent.exists()) {
			return null;
		}
		File rustc = new File(parent, "rustc");
		if (rustc.exists() && rustc.isFile() && rustc.canExecute()) {
			final RustSdkData result = new RustSdkData(rustc.getAbsolutePath());
			// Get version and host info
			if (!RustSdkUtil.getCompilerInfo(rustc, result)) {
				return null;
			}
			// With host info, get libraries path
			File pathLibs = new File(rustc.getParentFile().getParent(), "lib" + File.separator + "rustlib" + File.separator + result.hostTriple + File.separator + "lib");
			if (pathLibs.exists() && pathLibs.isDirectory()) {
				result.pathLibs = pathLibs.getAbsolutePath();
				return result;
			}
		}
		return null;
	}

	public static VirtualFile getSdkLibRoot(Sdk sdk, RustSdkData sdkData) {
		final VirtualFile homeDirectory = sdk.getHomeDirectory();

		assert homeDirectory != null;
		return homeDirectory.findFileByRelativePath("../lib/rustlib/" + sdkData.hostTriple + "/lib");
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
