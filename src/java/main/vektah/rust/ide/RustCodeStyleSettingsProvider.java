package vektah.rust.ide;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.lang.Language;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.RustLanguage;
import vektah.rust.i18n.RustBundle;

public class RustCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
	@NotNull
	@Override
	public Configurable createSettingsPage(CodeStyleSettings settings, CodeStyleSettings originalSettings) {
		return new CodeStyleAbstractConfigurable(settings, originalSettings, RustBundle.message("language.name.rust")) {
			@Override
			protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
				return new TabbedLanguageCodeStylePanel(RustLanguage.INSTANCE, getCurrentSettings(), settings) {
					@Override
					protected void initTabs(CodeStyleSettings settings) {
						addIndentOptionsTab(settings);
					}
				};
			}

			@Override
			public String getHelpTopic() {
				return null;
			}
		};
	}

	@Nullable
	@Override
	public Language getLanguage() {
		return RustLanguage.INSTANCE;
	}
}
