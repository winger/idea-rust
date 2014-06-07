package vektah.rust.highlight;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.psi.RustTokens;

public class RustBraceMatcher implements PairedBraceMatcher {
	private final BracePair[] pairs = new BracePair[] {
			new BracePair(RustTokens.OPEN_PAREN, RustTokens.CLOSE_PAREN, false),
			new BracePair(RustTokens.OPEN_BRACE, RustTokens.CLOSE_BRACE, true),
			new BracePair(RustTokens.OPEN_SQUARE_BRACKET, RustTokens.CLOSE_SQUARE_BRACKET, false),
	};

	@Override
	public BracePair[] getPairs() {
		return pairs;
	}

	@Override
	public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
		// FIXME: We can do better
		return true;
	}

	@Override
	public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
		return openingBraceOffset;
	}
}
