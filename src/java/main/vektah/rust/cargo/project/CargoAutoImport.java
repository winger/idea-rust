package vektah.rust.cargo.project;

import com.intellij.openapi.externalSystem.ExternalSystemAutoImportAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtilCore;
import org.jetbrains.annotations.Nullable;
import vektah.rust.cargo.Cargo;

import java.io.File;

public class CargoAutoImport implements ExternalSystemAutoImportAware {
    @Nullable
    @Override
    public String getAffectedExternalProjectPath(String changedFileOrDirPath, Project project) {
        File changed = new File(changedFileOrDirPath);
        String name = changed.getName();
        File base = new File(project.getBasePath());
        if ((Cargo.BUILD_FILE.equals(name) || Cargo.LOCK_FILE.equals(name)) && VfsUtilCore.isAncestor(base, changed, true)) {
            return project.getBasePath();
        }
        return null;
    }
}
