package vektah.rust.ide.sdk;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

public class CargoUtil {

    public static String findCargoPath() {
        return "cargo";
    }

    @Nullable
    public static VirtualFile findTomlOf(Project project) {
        return findTomlIn(project.getBaseDir());
    }

    @Nullable
    private static VirtualFile findTomlIn(VirtualFile dir) {
        return dir.findChild("Cargo.toml");
    }

    public static String findCargoWorkingDirectory(Project project) {
        return CargoUtil.findTomlOf(project).getParent().getCanonicalPath();
    }

    public static String findOutCrateNameOf(VirtualFile contentRoot) throws Exception {
        VirtualFile toml = findTomlIn(contentRoot);
        if (toml == null) {
            throw new Exception("Can't find Cargo.toml file");
        }

        String[] tomlLines = new String(toml.contentsToByteArray()).split("\n");
        for (String line : tomlLines) {
            if (line.startsWith("name = \"")) {
                return line.split("\"")[1];
            }
        }

        throw new Exception("Can't find out name of the crate");
    }

}
