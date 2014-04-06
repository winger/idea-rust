package vektah.rust;

import com.intellij.lang.Language;

public class RustLanguage extends Language {
	public static final RustLanguage INSTANCE = new RustLanguage();

	private RustLanguage() {
		super("Rust");
	}
}
