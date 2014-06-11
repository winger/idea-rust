package vektah.rust.ide.module;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleBuilderListener;
import com.intellij.ide.util.projectWizard.SourcePathsBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import vektah.rust.ide.sdk.RustSdkType;

public class RustModuleBuilder extends JavaModuleBuilder implements SourcePathsBuilder, ModuleBuilderListener {
	private static final Logger LOG = Logger.getInstance(RustModuleBuilder.class);

	public RustModuleBuilder() {
		addListener(this);
	}

	@Override
	public void moduleCreated(@NotNull Module module) {
		ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
		VirtualFile sourceRoots[] = moduleRootManager.getSourceRoots();

		if (sourceRoots.length != 1 ) {
			return;
		}

		PsiDirectory directory = PsiManager.getInstance(module.getProject()).findDirectory(sourceRoots[0]);

		if (directory == null || directory.getParentDirectory() == null) {
			return;
		}

		final PsiDirectory baseDir = directory.getParentDirectory();

		ApplicationManager.getApplication().runWriteAction(new Runnable() {
			@Override
			public void run() {
				try {
					baseDir.createSubdirectory("bin");
					baseDir.createSubdirectory("lib");
					baseDir.createSubdirectory("build");
				} catch (Exception e) {
					LOG.error(e.getMessage());
				}
			}
		});

//		try {
//			RustTemplatesFactory.createFromTemplate(directory, "main", module.getProject().getName().concat(".rs"), RustTemplatesFactory.Template.RustAppMain);
//		} catch (Exception e) {
//			LOG.error(e.getMessage());
//		}
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
