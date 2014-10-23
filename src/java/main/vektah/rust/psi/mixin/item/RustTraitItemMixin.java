package vektah.rust.psi.mixin.item;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.Nullable;
import vektah.rust.psi.RustTraitItem;
import vektah.rust.psi.impl.RustItemImpl;

public abstract class RustTraitItemMixin extends RustItemImpl implements RustTraitItem, PsiNameIdentifierOwner{
    public RustTraitItemMixin(ASTNode node) {
        super(node);
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
        return getItemName();
    }
}
