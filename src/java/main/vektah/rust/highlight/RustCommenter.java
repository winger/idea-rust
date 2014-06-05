package vektah.rust.highlight;

import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import vektah.rust.psi.RustTokens;

import static vektah.rust.psi.RustTokens.BLOCK_COMMENT;
import static vektah.rust.psi.RustTokens.BLOCK_DOC_COMMENT;
import static vektah.rust.psi.RustTokens.LINE_COMMENT;

public class RustCommenter implements CodeDocumentationAwareCommenter {
	@Nullable
	@Override
	public IElementType getLineCommentTokenType() {
		return LINE_COMMENT;
	}

	@Nullable
	@Override
	public IElementType getBlockCommentTokenType() {
		return BLOCK_COMMENT;
	}

	@Nullable
	@Override
	public IElementType getDocumentationCommentTokenType() {
		return BLOCK_DOC_COMMENT;
	}

	@Nullable
	@Override
	public String getDocumentationCommentPrefix() {
		return "/**";
	}

	@Nullable
	@Override
	public String getDocumentationCommentLinePrefix() {
		return null;
	}

	@Nullable
	@Override
	public String getDocumentationCommentSuffix() {
		return "*/";
	}

	@Override
	public boolean isDocumentationComment(final PsiComment psiComment) {
		// FIXME
		return false;
	}

	@Nullable
	@Override
	public String getLineCommentPrefix() {
		return "//";
	}

	@Nullable
	@Override
	public String getBlockCommentPrefix() {
		return "/*";
	}

	@Nullable
	@Override
	public String getBlockCommentSuffix() {
		return "*/";
	}

	@Nullable
	@Override
	public String getCommentedBlockCommentPrefix() {
		return "/*";
	}

	@Nullable
	@Override
	public String getCommentedBlockCommentSuffix() {
		return "*/";
	}
}
