package vektah.rust;

import com.intellij.lang.Language;
import vektah.rust.i18n.RustBundle;

public class RustLanguage extends Language {
	public static final RustLanguage INSTANCE = new RustLanguage();

	private RustLanguage() {
		super(RustBundle.message("language.name.rust"));
	}
}
