package vektah.rust.cargo.project.model;

import java.util.Collections;
import java.util.Map;

public class CargoProjectInfo {
    public final String root;
    public final Map<String, CargoPackageInfo> packages;

    public CargoProjectInfo(String root, Map<String, CargoPackageInfo> packages) {
        this.root = root;
        this.packages = Collections.unmodifiableMap(packages);
    }
}
