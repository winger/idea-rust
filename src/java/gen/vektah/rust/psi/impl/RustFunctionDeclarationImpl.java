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

public class RustFunctionDeclarationImpl extends ASTWrapperPsiElement implements RustFunctionDeclaration {

  public RustFunctionDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RustVisitor) ((RustVisitor)visitor).visitFunctionDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RustGeneric getGeneric() {
    return findChildByClass(RustGeneric.class);
  }

  @Override
  @NotNull
  public List<RustSelfArg> getSelfArgList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustSelfArg.class);
  }

  @Override
  @NotNull
  public List<RustStandardArg> getStandardArgList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustStandardArg.class);
  }

  @Override
  @Nullable
  public RustTypeBasic getTypeBasic() {
    return findChildByClass(RustTypeBasic.class);
  }

  @Override
  @Nullable
  public RustTypeClosure getTypeClosure() {
    return findChildByClass(RustTypeClosure.class);
  }

  @Override
  @Nullable
  public RustTypeFn getTypeFn() {
    return findChildByClass(RustTypeFn.class);
  }

  @Override
  @Nullable
  public RustTypeNone getTypeNone() {
    return findChildByClass(RustTypeNone.class);
  }

  @Override
  @Nullable
  public RustTypeProc getTypeProc() {
    return findChildByClass(RustTypeProc.class);
  }

  @Override
  @Nullable
  public RustTypeTuple getTypeTuple() {
    return findChildByClass(RustTypeTuple.class);
  }

}
