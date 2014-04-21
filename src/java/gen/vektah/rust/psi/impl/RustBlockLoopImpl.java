// This is a generated file. Not intended for manual editing.
package vektah.rust.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static vektah.rust.psi.RustTokens.*;
import vektah.rust.psi.*;

public class RustBlockLoopImpl extends RustExprBlockImpl implements RustBlockLoop {

  public RustBlockLoopImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RustVisitor) ((RustVisitor)visitor).visitBlockLoop(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public RustLifetime getLifetime() {
    return findChildByClass(RustLifetime.class);
  }

  @Override
  @NotNull
  public RustStatementBlock getStatementBlock() {
    return findNotNullChildByClass(RustStatementBlock.class);
  }

}
