// This is a generated file. Not intended for manual editing.
package vektah.rust.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RustTraitFunctionDeclaration extends PsiElement {

  @NotNull
  List<RustFunctionType> getFunctionTypeList();

  @Nullable
  RustGenericParams getGenericParams();

  @NotNull
  List<RustSelfArg> getSelfArgList();

  @NotNull
  List<RustStandardArg> getStandardArgList();

  @NotNull
  List<RustTupleArg> getTupleArgList();

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
