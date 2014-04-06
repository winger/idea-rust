package vektah.rust.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import vektah.rust.RustLanguage;

public class RustElementType extends IElementType {
	public RustElementType(@NotNull @NonNls String debugName) {
		super(debugName, RustLanguage.INSTANCE);
	}
}
