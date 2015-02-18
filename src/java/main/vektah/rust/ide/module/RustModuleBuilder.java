package vektah.rust.ide.module;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleBuilderListener;
import com.intellij.ide.util.projectWizard.SourcePathsBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import vektah.rust.ide.sdk.RustSdkType;

import java.io.IOException;

public class RustModuleBuilder extends JavaModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {
	private static final Logger LOG = Logger.getInstance(RustModuleBuilder.class);

	public RustModuleBuilder() {
		addListener(this);
	}

	@Override
	public void moduleCreated(@NotNull final Module module) {
		final Project project = module.getProject();
		ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
		VirtualFile sourceRoots[] = moduleRootManager.getSourceRoots();

		if (sourceRoots.length != 1 ) {
			return;
		}

		PsiDirectory directory = PsiManager.getInstance(module.getProject()).findDirectory(sourceRoots[0]);
		if (directory == null) {
			throw new RuntimeException("Unable to determine module directory!");
		}

		final PsiDirectory baseDir = PsiManager.getInstance(module.getProject()).findDirectory(project.getBaseDir());
		if (baseDir == null) {
			throw new RuntimeException("Unable to determine base directory!");
		}

		createFromTemplate("Rust Hello", directory, "main.rs");
		createFromTemplate("Cargo", baseDir, "Cargo.toml");
	}

	private void createFromTemplate(String templateName, PsiDirectory dir, String name) {
		final FileTemplate template = FileTemplateManager.getInstance().getInternalTemplate(templateName);

		Project project = dir.getProject();
		final String fileContents;

		try {
			fileContents = template.getText(FileTemplateManager.getInstance().getDefaultProperties(project));
		} catch (IOException e) {
			throw new RuntimeException("Unable to parse template '" + templateName + "':" + e.getMessage());
		}

		try {
			dir.createFile(name).getVirtualFile().setBinaryContent(fileContents.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Unable to write file '" + name + ":" + e.getMessage());
		}
	}

	@Override
	public ModuleType getModuleType() {
		return RustModuleType.getInstance();
	}

	@Override
	public boolean isSuitableSdkType(SdkTypeId sdkType) {
		return sdkType instanceof RustSdkType;
	}
}
