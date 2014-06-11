package vektah.rust.ide.module;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.projectRoots.Sdk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.RustIcons;
import vektah.rust.i18n.RustBundle;
import vektah.rust.ide.sdk.RustSdkType;

import javax.swing.*;

public class RustModuleType extends ModuleType<RustModuleBuilder> {
	public static final String MODULE_TYPE_ID = "RUST_MODULE";

	public RustModuleType() {
		super(MODULE_TYPE_ID);
	}

	public static RustModuleType getInstance() {
		return (RustModuleType) ModuleTypeManager.getInstance().findByID(MODULE_TYPE_ID);
	}

	@NotNull
	@Override
	public RustModuleBuilder createModuleBuilder() {
		return new RustModuleBuilder();
	}

	@NotNull
	@Override
	public String getName() {
		return RustBundle.message("rust.module.type.name");
	}

	@NotNull
	@Override
	public String getDescription() {
		return RustBundle.message("rust.module.type.description");
	}

	@Override
	public Icon getBigIcon() {
		return RustIcons.ICON_RUST_24;
	}

	@Override
	public Icon getNodeIcon(@Deprecated boolean isOpened) {
		return RustIcons.ICON_RUST_16;
	}

	@Override
	public boolean isValidSdk(@NotNull Module module, @Nullable Sdk projectSdk) {
		return projectSdk != null && projectSdk.getSdkType() == RustSdkType.getInstance();
	}
}
