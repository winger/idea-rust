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

public class RustTypeBasicImpl extends ASTWrapperPsiElement implements RustTypeBasic {

  public RustTypeBasicImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RustVisitor) ((RustVisitor)visitor).visitTypeBasic(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RustGeneric getGeneric() {
    return findChildByClass(RustGeneric.class);
  }

  @Override
  @Nullable
  public RustLifetime getLifetime() {
    return findChildByClass(RustLifetime.class);
  }

  @Override
  @Nullable
  public RustRef getRef() {
    return findChildByClass(RustRef.class);
  }

}
