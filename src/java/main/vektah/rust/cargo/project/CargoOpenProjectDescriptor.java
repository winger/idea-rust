package vektah.rust.cargo.project;

import com.intellij.ide.actions.OpenProjectFileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;
import vektah.rust.cargo.Cargo;

public class CargoOpenProjectDescriptor extends OpenProjectFileChooserDescriptor {
    public CargoOpenProjectDescriptor() {
        super(true);
    }

    @Override
    public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
        return super.isFileVisible(file, showHiddenFiles) &&
               (file.isDirectory() || Cargo.BUILD_FILE.equals(file.getName()));
    }

    @Override
    public boolean isFileSelectable(VirtualFile file) {
        return super.isFileSelectable(file) && CargoProjectImportProvider.canImport(file);
    }
}
