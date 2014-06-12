package vektah.rust.ide.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import vektah.rust.ide.runner.RustToolWindow;

public class RustToolWindowFactory implements ToolWindowFactory {
	@Override
	public void createToolWindowContent(Project project, ToolWindow toolWindow) {
		RustToolWindow window = RustToolWindow.getInstance(project);
		window.initRustToolWindow(toolWindow);
	}
}
