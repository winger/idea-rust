// This is a generated file. Not intended for manual editing.
package vektah.rust.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static vektah.rust.psi.RustTokens.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import vektah.rust.psi.*;

public class RustMatchPatternImpl extends ASTWrapperPsiElement implements RustMatchPattern {

  public RustMatchPatternImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RustVisitor) ((RustVisitor)visitor).visitMatchPattern(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RustEnumMatchPattern getEnumMatchPattern() {
    return findChildByClass(RustEnumMatchPattern.class);
  }

  @Override
  @Nullable
  public RustVectorMatchPattern getVectorMatchPattern() {
    return findChildByClass(RustVectorMatchPattern.class);
  }

}
