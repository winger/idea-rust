package vektah.rust.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

@State(
	name = "Rust",
	storages = {
		@Storage(id="other", file = StoragePathMacros.APP_CONFIG + "Rust.xml")
	}
)
public class RustConfigService implements PersistentStateComponent<RustConfig> {
	private RustConfig config = new RustConfig();

	@NotNull
	@Override
	public RustConfig getState() {
		return config;
	}

	@Override
	public void loadState(RustConfig config) {
		XmlSerializerUtil.copyBean(config, this.config);
	}
}
