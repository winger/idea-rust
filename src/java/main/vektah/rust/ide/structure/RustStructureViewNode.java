package vektah.rust.ide.structure;

import com.google.common.collect.ImmutableList;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.psi.*;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static vektah.rust.RustIcons.forNode;

/**
 * (C) Atlassian 2014
 */
class RustStructureViewNode extends PsiTreeElementBase<PsiNamedElement> {
    private PsiNamedElement psiElement;

    public RustStructureViewNode(PsiNamedElement psiElement) {
        super(psiElement);
        this.psiElement = psiElement;
    }

    @NotNull
    @Override
    public Collection<StructureViewTreeElement> getChildrenBase() {
        ImmutableList.Builder<StructureViewTreeElement> listBuilder = ImmutableList.builder();

        if (psiElement instanceof RustItem) {
            for (PsiNamedElement child : ((RustItem) psiElement).getChildrenItems()) {
                    listBuilder.add(new RustStructureViewNode(child));
            }
        }

        return listBuilder.build();
    }


    @Override
    public Icon getIcon(boolean open) {
        return forNode(psiElement);
    }

    @Nullable
    @Override
    public String getPresentableText() {
        return psiElement.getName();
    }
}
