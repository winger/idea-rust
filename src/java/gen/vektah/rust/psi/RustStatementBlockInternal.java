// This is a generated file. Not intended for manual editing.
package vektah.rust.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RustStatementBlockInternal extends PsiElement {

  @NotNull
  List<RustAttribute> getAttributeList();

  @NotNull
  List<RustExternBlock> getExternBlockList();

  @NotNull
  List<RustExternCrate> getExternCrateList();

  @NotNull
  List<RustFunction> getFunctionList();

  @NotNull
  List<RustLet> getLetList();

  @NotNull
  List<RustStatementBlock> getStatementBlockList();

  @NotNull
  List<RustStatementWildcard> getStatementWildcardList();

  @NotNull
  List<RustStruct> getStructList();

  @NotNull
  List<RustTypeDefinition> getTypeDefinitionList();

  @NotNull
  List<RustUse> getUseList();

}
