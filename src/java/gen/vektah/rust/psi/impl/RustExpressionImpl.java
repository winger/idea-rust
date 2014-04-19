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

public class RustExpressionImpl extends ASTWrapperPsiElement implements RustExpression {

  public RustExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RustVisitor) ((RustVisitor)visitor).visitExpression(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RustAttribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustAttribute.class);
  }

  @Override
  @NotNull
  public List<RustExtern> getExternList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustExtern.class);
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
  public List<RustTypeDefinition> getTypeDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustTypeDefinition.class);
  }

  @Override
  @NotNull
  public List<RustUse> getUseList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustUse.class);
  }

}
