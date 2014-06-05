package vektah.rust;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.i18n.RustBundle;

import javax.swing.*;

public class RustFileType extends LanguageFileType {
	public static final RustFileType INSTANCE = new RustFileType();

	private RustFileType() {
		super(RustLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public String getName() {
		return RustBundle.message("file.type.name.rust");
	}

	@NotNull
	@Override
	public String getDescription() {
		return RustBundle.message("file.type.description.rust");
	}

	@NotNull
	@Override
	public String getDefaultExtension() {
		return "rs";
	}

	@Nullable
	@Override
	public Icon getIcon() {
		return RustIcons.ICON_RUST_16;
	}
}
