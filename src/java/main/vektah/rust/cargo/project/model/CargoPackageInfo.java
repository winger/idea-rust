package vektah.rust.cargo.project.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CargoPackageInfo {
    public final String path;
    public final String version;
    public final List<String> dependencies;
    public final Map<String, List<String>> features;

    public CargoPackageInfo(
        String path,
        String version,
        List<String> dependencies,
        Map<String, List<String>> features) {
        this.path = path;
        this.version = version;
        this.dependencies = Collections.unmodifiableList(dependencies);
        this.features =
            features == null ? Collections.<String, List<String>>emptyMap() : Collections.unmodifiableMap(features);
    }
}
