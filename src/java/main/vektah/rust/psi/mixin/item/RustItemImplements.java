package vektah.rust.psi.mixin.item;

import com.intellij.psi.PsiNamedElement;

import java.util.List;

/**
 * (C) Atlassian 2014
 */
public interface RustItemImplements extends PsiNamedElement {
    public List<? extends PsiNamedElement> getChildrenItems();
}
