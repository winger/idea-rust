package vektah.rust.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import vektah.rust.RustFileType;
import vektah.rust.RustLanguage;
import vektah.rust.i18n.RustBundle;

import javax.swing.*;

public class RustFile extends PsiFileBase {
	public RustFile(@NotNull FileViewProvider fileViewProvider) {
		super(fileViewProvider, RustLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType() {
		return RustFileType.INSTANCE;
	}

	@Override
	public String toString() {
		return RustBundle.message("file.name.rust");
	}

	@Override
	public Icon getIcon(int flags) {
		return super.getIcon(flags);
	}
}
