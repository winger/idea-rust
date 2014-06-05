package vektah.rust;

import com.intellij.lang.Language;
import vektah.rust.i18n.RustBundle;

public class RustLanguage extends Language {
	public static final RustLanguage INSTANCE = new RustLanguage();

	private RustLanguage() {
		super("Rust", "text/rust", "text/x-rust", "application/x-rust");
	}

	@Override
	public boolean isCaseSensitive() {
		return true;
	}

	@Override
	public String getDisplayName() {
		return RustBundle.message("language.name.rust");
	}
}
