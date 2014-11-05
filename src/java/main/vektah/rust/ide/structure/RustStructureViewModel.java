package vektah.rust.ide.structure;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

class RustStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
    public RustStructureViewModel(@NotNull PsiFile psiFile) {
        super(psiFile, new RustStructureViewNode(psiFile));
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement structureViewTreeElement) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement structureViewTreeElement) {
        return structureViewTreeElement instanceof RustStructureViewNode
                && ((RustStructureViewNode) structureViewTreeElement).isLeafNode();

    }
}

