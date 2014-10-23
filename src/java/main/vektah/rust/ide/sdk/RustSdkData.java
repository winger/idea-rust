package vektah.rust.ide.sdk;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.projectRoots.SdkAdditionalData;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.i18n.RustBundle;

public class RustSdkData implements SdkAdditionalData, PersistentStateComponent<RustSdkData> {
	public String pathRustc;
	public String version;

	// For serialization / deserialization
	private RustSdkData() {}

	public RustSdkData(@NotNull String pathRustc) {
		this.pathRustc = pathRustc;
	}

	@Nullable
	@Override
	public RustSdkData getState() {
		return this;
	}

	@Override
	public void loadState(RustSdkData state) {
		XmlSerializerUtil.copyBean(state, this);
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getSdkName() {
		return RustBundle.message("sdk.fullname.rust", version);
	}
}
