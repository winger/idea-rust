package vektah.rust.psi.mixin.struct;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.psi.RustStructProperty;
import vektah.rust.psi.RustTypeItem;
import vektah.rust.psi.impl.RustItemImpl;

/**
 * (C) Atlassian 2014
 */
public abstract class RustStructPropertyMixin extends RustItemImpl implements RustStructProperty, PsiNameIdentifierOwner {
    public RustStructPropertyMixin(ASTNode node) {
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
        return getLetVariableBind().getItemName();
    }
}