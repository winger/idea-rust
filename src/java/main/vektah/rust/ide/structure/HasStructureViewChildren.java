package vektah.rust.ide.structure;

import com.intellij.psi.PsiNamedElement;

import java.util.List;

/**
 * (C) Atlassian 2014
 */
public interface HasStructureViewChildren {
    public List<? extends PsiNamedElement> getChildrenItems();
}
