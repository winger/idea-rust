package vektah.rust.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import vektah.rust.RustLanguage;

public class RustTokenType extends IElementType {
	public RustTokenType(@NotNull @NonNls String debugName) {
		super(debugName, RustLanguage.INSTANCE);
	}
}
