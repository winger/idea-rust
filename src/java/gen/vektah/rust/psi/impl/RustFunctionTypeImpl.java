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

public class RustFunctionTypeImpl extends ASTWrapperPsiElement implements RustFunctionType {

  public RustFunctionTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RustVisitor) ((RustVisitor)visitor).visitFunctionType(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RustFunctionType> getFunctionTypeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustFunctionType.class);
  }

  @Override
  @NotNull
  public List<RustRef> getRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustRef.class);
  }

  @Override
  @Nullable
  public RustTraitBounds getTraitBounds() {
    return findChildByClass(RustTraitBounds.class);
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
