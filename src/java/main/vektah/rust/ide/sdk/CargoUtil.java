package vektah.rust.ide.sdk;

import com.intellij.openapi.vfs.VirtualFile;

public class CargoUtil {

    public static String findCargoPath() {
        return "cargo";
    }

    public static String findOutCrateNameOf(VirtualFile contentRoot) throws Exception {
        VirtualFile toml = contentRoot.findChild("Cargo.toml");
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
