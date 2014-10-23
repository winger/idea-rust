package vektah.rust.ide.structure;

import com.google.common.collect.ImmutableList;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collection;

import static vektah.rust.RustIcons.forNode;

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

        if (psiElement instanceof HasStructureViewChildren) {
            for (PsiNamedElement child : ((HasStructureViewChildren) psiElement).getChildrenItems()) {
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
