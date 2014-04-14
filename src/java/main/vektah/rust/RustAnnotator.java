package vektah.rust;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import vektah.rust.psi.RustAttribute;
import vektah.rust.psi.RustTraitBounds;

public class RustAnnotator implements Annotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		
		if (element instanceof RustAttribute) {
			holder.createInfoAnnotation(element, null).setTextAttributes(RustSyntaxHighlighter.ATTRIBUTE);
		}

		if (element instanceof RustTraitBounds && element.getTextLength() == 1) {
			holder.createWarningAnnotation(element, "By default it is assumed that the value ascribes to no trait bounds").setHighlightType(ProblemHighlightType.WEAK_WARNING);
		}
	}
}
