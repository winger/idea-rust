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

public class RustTraitFunctionDeclarationImpl extends ASTWrapperPsiElement implements RustTraitFunctionDeclaration {

  public RustTraitFunctionDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RustVisitor) ((RustVisitor)visitor).visitTraitFunctionDeclaration(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RustFunctionType> getFunctionTypeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustFunctionType.class);
  }

  @Override
  @Nullable
  public RustGenericParams getGenericParams() {
    return findChildByClass(RustGenericParams.class);
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
  @NotNull
  public List<RustTupleArg> getTupleArgList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustTupleArg.class);
  }

  @Override
  @NotNull
  public List<RustTypeBasic> getTypeBasicList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustTypeBasic.class);
  }

  @Override
  @NotNull
  public List<RustTypeClosure> getTypeClosureList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustTypeClosure.class);
  }

  @Override
  @NotNull
  public List<RustTypeNone> getTypeNoneList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustTypeNone.class);
  }

  @Override
  @NotNull
  public List<RustTypeProc> getTypeProcList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustTypeProc.class);
  }

  @Override
  @NotNull
  public List<RustTypeTuple> getTypeTupleList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustTypeTuple.class);
  }

}
