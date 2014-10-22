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
        final PsiElement[] children;

        if (psiElement instanceof RustFile) {
            children = psiElement.getChildren();
        } else if (psiElement instanceof RustStructItem) {
            children = ((RustStructItem) psiElement).getStructBody().getChildren();
        } else if (psiElement instanceof RustImplItem) {
            RustImplBlock implBlock = ((RustImplItem) psiElement).getImplBlock();

            if (implBlock == null) {
                return Collections.emptyList();
            }

            children = implBlock.getImplBody().getChildren();
        } else if (psiElement instanceof RustModItem) {
            List<RustItem> itemList = ((RustModItem) psiElement).getItemList();

            children = itemList.toArray(new RustItem[itemList.size()]);
        } else {
            return Collections.emptyList();
        }

        if (children.length == 0) {
            return Collections.emptyList();
        }

        ImmutableList.Builder<StructureViewTreeElement> listBuilder = ImmutableList.builder();

        for (PsiElement child : children) {
            if (child instanceof RustStructItem
                    || child instanceof RustImplItem
                    || child instanceof RustModItem
                    || child instanceof RustStaticItem
                    || child instanceof RustFnItem
                    || child instanceof RustStructProperty
                    || child instanceof RustEnumItem
                    || child instanceof RustTraitItem) {
                listBuilder.add(new RustStructureViewNode((PsiNamedElement) child));
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
