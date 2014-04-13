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

public class RustStatementBlockInternalImpl extends ASTWrapperPsiElement implements RustStatementBlockInternal {

  public RustStatementBlockInternalImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RustVisitor) ((RustVisitor)visitor).visitStatementBlockInternal(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RustAttribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustAttribute.class);
  }

  @Override
  @NotNull
  public List<RustExternBlock> getExternBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustExternBlock.class);
  }

  @Override
  @NotNull
  public List<RustExternCrate> getExternCrateList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustExternCrate.class);
  }

  @Override
  @NotNull
  public List<RustFunction> getFunctionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustFunction.class);
  }

  @Override
  @NotNull
  public List<RustLet> getLetList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustLet.class);
  }

  @Override
  @NotNull
  public List<RustStatementBlock> getStatementBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustStatementBlock.class);
  }

  @Override
  @NotNull
  public List<RustStatementWildcard> getStatementWildcardList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustStatementWildcard.class);
  }

  @Override
  @NotNull
  public List<RustStruct> getStructList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustStruct.class);
  }

  @Override
  @NotNull
  public List<RustTypeDefinition> getTypeDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustTypeDefinition.class);
  }

  @Override
  @NotNull
  public List<RustUse> getUseList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustUse.class);
  }

}
