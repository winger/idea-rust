package vektah.rust.ide.structure;

import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

class RustTreeItemPresentation implements ItemPresentation {
    public final String name;
    public final Icon icon;

    RustTreeItemPresentation(String name, Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    @Nullable
    @Override
    public String getPresentableText() {
        return name;
    }

    @Nullable
    @Override
    public String getLocationString() {
        return null;
    }

    @Nullable
    @Override
    public Icon getIcon(boolean b) {
        return icon;
    }
}
