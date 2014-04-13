package vektah.rust;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import vektah.rust.psi.RustAttribute;

public class RustAnnotator implements Annotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		
		if (element instanceof RustAttribute) {
			holder.createInfoAnnotation(element, null).setTextAttributes(RustSyntaxHighlighter.ATTRIBUTE);
		}
	}
}
