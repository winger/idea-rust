package vektah.rust.cargo.project;

import com.intellij.openapi.externalSystem.service.project.wizard.AbstractExternalProjectImportProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.cargo.Cargo;

import javax.swing.*;

public class CargoProjectImportProvider extends AbstractExternalProjectImportProvider {

    public CargoProjectImportProvider(CargoProjectImportBuilder builder) {
        super(builder, CargoProjectSystem.ID);
    }

    @NotNull
    @Override
    public String getId() {
        return Cargo.ID;
    }

    @NotNull
    @Override
    public String getName() {
        return Cargo.NAME;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return Cargo.ICON;
    }

    @Override
    public boolean canImport(VirtualFile entry, Project project) {
        return canImport(entry);
    }

    public static boolean canImport(VirtualFile entry) {
        if (entry.isDirectory()) {
            entry = entry.findChild(Cargo.BUILD_FILE);
        }
        return entry != null && !entry.isDirectory() && Cargo.BUILD_FILE.equals(entry.getName());
    }

    @Override
    public String getPathToBeImported(VirtualFile file) {
        return file.isDirectory() ? file.getPath() : file.getParent().getPath();
    }

    @Nullable
    @Override
    public String getFileSample() {
        return "<b>Cargo</b> project file (Cargo.toml)";
    }
}
