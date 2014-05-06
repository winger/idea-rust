// This is a generated file. Not intended for manual editing.
package vektah.rust.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RustMatchRule extends PsiElement {

  @Nullable
  RustAttribute getAttribute();

  @Nullable
  RustExpr getExpr();

  @Nullable
  RustExtern getExtern();

  @Nullable
  RustFunction getFunction();

  @Nullable
  RustLet getLet();

  @NotNull
  List<RustMatchFilter> getMatchFilterList();

  @Nullable
  RustReturnStatement getReturnStatement();

  @Nullable
  RustStatementBlock getStatementBlock();

  @Nullable
  RustStruct getStruct();

  @Nullable
  RustTypeDefinition getTypeDefinition();

  @Nullable
  RustUse getUse();

}
