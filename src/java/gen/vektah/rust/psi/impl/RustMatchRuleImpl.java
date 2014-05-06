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

public class RustMatchRuleImpl extends ASTWrapperPsiElement implements RustMatchRule {

  public RustMatchRuleImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RustVisitor) ((RustVisitor)visitor).visitMatchRule(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RustAttribute getAttribute() {
    return findChildByClass(RustAttribute.class);
  }

  @Override
  @Nullable
  public RustExpr getExpr() {
    return findChildByClass(RustExpr.class);
  }

  @Override
  @Nullable
  public RustExtern getExtern() {
    return findChildByClass(RustExtern.class);
  }

  @Override
  @Nullable
  public RustFunction getFunction() {
    return findChildByClass(RustFunction.class);
  }

  @Override
  @Nullable
  public RustLet getLet() {
    return findChildByClass(RustLet.class);
  }

  @Override
  @NotNull
  public List<RustMatchFilter> getMatchFilterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RustMatchFilter.class);
  }

  @Override
  @Nullable
  public RustReturnStatement getReturnStatement() {
    return findChildByClass(RustReturnStatement.class);
  }

  @Override
  @Nullable
  public RustStatementBlock getStatementBlock() {
    return findChildByClass(RustStatementBlock.class);
  }

  @Override
  @Nullable
  public RustStruct getStruct() {
    return findChildByClass(RustStruct.class);
  }

  @Override
  @Nullable
  public RustTypeDefinition getTypeDefinition() {
    return findChildByClass(RustTypeDefinition.class);
  }

  @Override
  @Nullable
  public RustUse getUse() {
    return findChildByClass(RustUse.class);
  }

}
