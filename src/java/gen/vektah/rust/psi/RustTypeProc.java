// This is a generated file. Not intended for manual editing.
package vektah.rust.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RustTypeProc extends PsiElement {

  @NotNull
  List<RustStandardArg> getStandardArgList();

  @NotNull
  List<RustTypeBasic> getTypeBasicList();

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
