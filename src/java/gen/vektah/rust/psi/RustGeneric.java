// This is a generated file. Not intended for manual editing.
package vektah.rust.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RustGeneric extends PsiElement {

  @NotNull
  List<RustLifetime> getLifetimeList();

  @NotNull
  List<RustTypeBasic> getTypeBasicList();

  @NotNull
  List<RustTypeClosure> getTypeClosureList();

  @NotNull
  List<RustTypeFn> getTypeFnList();

  @NotNull
  List<RustTypeNone> getTypeNoneList();

  @NotNull
  List<RustTypeProc> getTypeProcList();

  @NotNull
  List<RustTypeTuple> getTypeTupleList();

}
