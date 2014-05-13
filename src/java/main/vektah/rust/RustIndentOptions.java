package vektah.rust;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.FileTypeIndentOptionsProvider;

class RustIndentOptionsProvider implements FileTypeIndentOptionsProvider {
	@Override
	public CommonCodeStyleSettings.IndentOptions createIndentOptions() {
		CommonCodeStyleSettings.IndentOptions options =  new CommonCodeStyleSettings.IndentOptions();

		options.USE_TAB_CHARACTER = false;
		options.INDENT_SIZE = 4;

		return options;
	}

	@Override
	public FileType getFileType() {
		return RustFileType.INSTANCE;
	}

	@Override
	public IndentOptionsEditor createOptionsEditor() {
		return new IndentOptionsEditor();
	}

	@Override
	public String getPreviewText() {
		// Where are raw strings when you need them
		return "mod {\n" +
			"\tpub fn main() {\n" +
			"\t\tif true {\n" +
			"\t\t\tprintln!(\"TRUE\");\n" +
			"\t\t}\n" +
			"\t}\n" +
			"}\n";
	}

	@Override
	public void prepareForReformat(PsiFile psiFile) {

	}
}
