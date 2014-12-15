package vektah.rust.ide.sdk;

import com.intellij.openapi.projectRoots.*;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.RustIcons;
import vektah.rust.i18n.RustBundle;

import javax.swing.*;
import java.io.File;

public class RustSdkType extends SdkType {
	private RustSdkData sdkData;

	public RustSdkType() {
		super(RustBundle.message("sdk.name.rust"));
	}

	public static RustSdkType getInstance() {
		return SdkType.findInstance(RustSdkType.class);
	}

	@Nullable
	@Override
	public String suggestHomePath() {
		if (SystemInfo.isMac || SystemInfo.isLinux) {
			return "/usr/local/bin";
		}
		else if (SystemInfo.isWindows) {
			String programFiles = System.getenv("ProgramFiles");
			File rustFolder = new File(programFiles, "Rust");
			if (!rustFolder.exists()) rustFolder = new File(programFiles + " (x86)", "Rust");

			try {
				return new File(rustFolder, "bin").getCanonicalPath();
			}
			catch (Exception e) {
				// nobody cares
			}
		}
		return null;
	}

	@Override
	public boolean isValidSdkHome(String path) {
		sdkData = RustSdkUtil.testRustSdk(path);
		return sdkData != null;
	}

    @Nullable
    @Override
    public String getVersionString(String sdkHome) {
        return RustSdkUtil.testRustSdk(sdkHome).version;
    }

    @Override
	public void setupSdkPaths(@NotNull Sdk sdk) {
	}

	@Override
	public String suggestSdkName(String currentSdkName, String sdkHome) {
		if (sdkData != null) {
			return sdkData.getSdkName();
		}
		return getPresentableName();
	}

	@Nullable
	@Override
	public AdditionalDataConfigurable createAdditionalDataConfigurable(SdkModel sdkModel, SdkModificator sdkModificator) {
		return null;
	}

	@Override
	public String getPresentableName() {
		return RustBundle.message("sdk.name.rust");
	}

	@Override
	public SdkAdditionalData loadAdditionalData(Element additional) {
		return XmlSerializer.deserialize(additional, RustSdkData.class);
	}

	@Override
	public void saveAdditionalData(@NotNull SdkAdditionalData additionalData, @NotNull Element additional) {
		if (additionalData instanceof RustSdkData) {
			XmlSerializer.serializeInto(additionalData, additional);
		}
	}

	@Override
	public Icon getIcon() {
		return RustIcons.ICON_RUST_16;
	}

	@Override
	public Icon getIconForAddAction() {
		return getIcon();
	}

	@Override
	public boolean isRootTypeApplicable(OrderRootType type) {
		return type == OrderRootType.CLASSES || type == OrderRootType.SOURCES;
	}
}
