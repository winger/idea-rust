// This is a generated file. Not intended for manual editing.
package vektah.rust.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RustTupleBody extends PsiElement {

  @NotNull
  List<RustFunctionType> getFunctionTypeList();

  @NotNull
  List<RustTypeBasic> getTypeBasicList();

  @NotNull
  List<RustTypeClosure> getTypeClosureList();

  @NotNull
  List<RustTypeNone> getTypeNoneList();

  @NotNull
  List<RustTypeProc> getTypeProcList();

  @NotNull
  List<RustTypeTuple> getTypeTupleList();

}
