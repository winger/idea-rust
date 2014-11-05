package vektah.rust.psi.mixin.item;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import vektah.rust.ide.structure.HasStructureViewChildren;
import vektah.rust.psi.RustImplItem;
import vektah.rust.psi.RustType;
import vektah.rust.psi.impl.RustItemImpl;

import java.util.Collections;
import java.util.List;

public abstract class RustImplItemMixin extends RustItemImpl implements RustImplItem, PsiNamedElement, HasStructureViewChildren {
    public RustImplItemMixin(ASTNode node) {
        super(node);
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        return null;
    }

    @Override
    public String getName() {
        RustType type = getType();

        if (type != null) {
            return type.getText();
        }

        return null;
    }

    @Override
    public List<? extends PsiNamedElement> getChildrenItems() {
        if (getImplBlock() == null) {
            return Collections.emptyList();
        }

        return getImplBlock().getImplBody().getFnItemList();
    }
}
