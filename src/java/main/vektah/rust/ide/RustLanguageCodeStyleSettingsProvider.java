package vektah.rust.ide;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import vektah.rust.RustLanguage;

public class RustLanguageCodeStyleSettingsProvider
		extends LanguageCodeStyleSettingsProvider {

	public static final String CODE_SAMPLE = "// Prime sieve\n" +
			"\n" +
			"extern mod extra;\n" +
			"use std::task::spawn_with;\n" +
			"use extra::comm::{SyncPort, SyncChan, rendezvous};\n" +
			"use std::iter::count;\n" +
			"\n" +
			"fn generate(ch: SyncChan<int>) {\n" +
			"    for i in count(2, 1) {\n" +
			"        if !ch.try_send(i) {\n" +
			"            break;\n" +
			"        }\n" +
			"    }\n" +
			"}\n" +
			"\n" +
			"fn filter(in_ch: SyncPort<int>, out_ch: SyncChan<int>, prime: int) {\n" +
			"    loop {\n" +
			"        let i = in_ch.recv();\n" +
			"        if i % prime != 0 {\n" +
			"            out_ch.send(i);\n" +
			"        }\n" +
			"    }\n" +
			"}\n" +
			"\n" +
			"fn main() {\n" +
			"    let (port, chan) = rendezvous();\n" +
			"\n" +
			"    let mut prev_port = port;\n" +
			"\n" +
			"    do spawn_with(chan) |chan| {\n" +
			"        generate(chan);\n" +
			"    }\n" +
			"\n" +
			"    loop {\n" +
			"        let prime = prev_port.recv();\n" +
			"        println!(\"{}\", prime);\n" +
			"\n" +
			"        let (new_port, new_chan) = rendezvous();\n" +
			"\n" +
			"        do spawn_with((prev_port, new_chan)) |(prev_port, new_chan)| {\n" +
			"            filter(prev_port, new_chan, prime);\n" +
			"        }\n" +
			"        prev_port = new_port;\n" +
			"    }\n" +
			"}";

	@NotNull
	@Override
	public Language getLanguage() {
		return RustLanguage.INSTANCE;
	}

	@Override
	public CommonCodeStyleSettings getDefaultCommonSettings() {
		CommonCodeStyleSettings defaultSettings =
				new CommonCodeStyleSettings(RustLanguage.INSTANCE);

		CommonCodeStyleSettings.IndentOptions indentOptions =
				defaultSettings.initIndentOptions();

		indentOptions.USE_TAB_CHARACTER = true;
		indentOptions.INDENT_SIZE = 4;
		indentOptions.TAB_SIZE = 4;
		indentOptions.CONTINUATION_INDENT_SIZE = 8;

		return defaultSettings;
	}

	@Override
	public String getCodeSample(@NotNull SettingsType settingsType) {
		return CODE_SAMPLE;
	}

	@Override
	public IndentOptionsEditor getIndentOptionsEditor() {
		return new SmartIndentOptionsEditor();
	}
}
