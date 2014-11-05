package vektah.rust.psi.mixin.item;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.ide.structure.HasStructureViewChildren;
import vektah.rust.psi.RustModItem;
import vektah.rust.psi.RustTokens;
import vektah.rust.psi.impl.RustItemImpl;

import java.util.List;

public abstract class RustModItemMixin extends RustItemImpl implements RustModItem, PsiNameIdentifierOwner, HasStructureViewChildren{
    public RustModItemMixin(ASTNode node) {
        super(node);
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        return null;
    }

    @Nullable
    @Override
    public String getName() {
        PsiElement nameIdentifier = getNameIdentifier();

        if (nameIdentifier != null) {
            return nameIdentifier.getText();
        }

        return null;
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        List<PsiElement> children = findChildrenByType(RustTokens.IDENTIFIER);

        if (children.size() == 0) return null;

        return children.get(children.size()-1);
    }

    @Override
    public List<? extends com.intellij.psi.PsiNamedElement> getChildrenItems() {
        return getItemList();
    }
}
