// This is a generated file. Not intended for manual editing.
package vektah.rust.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RustFunctionDeclaration extends PsiElement {

  @Nullable
  RustGeneric getGeneric();

  @NotNull
  List<RustSelfArg> getSelfArgList();

  @NotNull
  List<RustStandardArg> getStandardArgList();

  @Nullable
  RustTypeBasic getTypeBasic();

  @Nullable
  RustTypeClosure getTypeClosure();

  @Nullable
  RustTypeFn getTypeFn();

  @Nullable
  RustTypeNone getTypeNone();

  @Nullable
  RustTypeProc getTypeProc();

  @Nullable
  RustTypeTuple getTypeTuple();

}
