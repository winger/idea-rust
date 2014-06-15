package vektah.rust.ide.runner;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.filters.OpenFileHyperlinkInfo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RustConsoleFilter implements Filter {
	private static final Pattern MSG_LINE = Pattern.compile("\t?(\\S+\\.\\w+):(\\d+)[:\\s].*\n");

	private final Project project;
	private final String basePath;

	public RustConsoleFilter(Project project, String basePath) {
		this.project = project;
		this.basePath = basePath;
	}

	@Nullable
	@Override
	public Result applyFilter(String line, int entireLength) {
		Matcher matcher = MSG_LINE.matcher(line);
		if (!matcher.matches()) {
			return null;
		}

		String fileName = matcher.group(1);
		int fileLine;
		try {
			fileLine = Integer.parseInt(matcher.group(2)) - 1;
		} catch (NumberFormatException e) {
			return null;
		}
		VirtualFile vf;
		if (fileName.startsWith("/") || fileName.contains(":")) {
			vf = project.getBaseDir().getFileSystem().findFileByPath(fileName);
		} else if (fileName.contains("/")) {
			vf = project.getBaseDir().findFileByRelativePath(fileName);
		} else {
			vf = project.getBaseDir().getFileSystem().findFileByPath(basePath + "/" + fileName);
		}
		if (vf == null) {
			return null;
		}

		HyperlinkInfo hyperlinkInfo = new OpenFileHyperlinkInfo(project, vf, fileLine);
		int outputStart = entireLength - line.length();

		return new Result(outputStart + matcher.start(1), outputStart + matcher.end(2), hyperlinkInfo);
	}
}
