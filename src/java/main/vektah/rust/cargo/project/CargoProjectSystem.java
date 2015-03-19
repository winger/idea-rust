package vektah.rust.cargo.project;

import com.intellij.openapi.externalSystem.model.ProjectSystemId;
import vektah.rust.cargo.Cargo;

public class CargoProjectSystem {
    public final static ProjectSystemId ID = new ProjectSystemId(Cargo.ID, Cargo.NAME);
}
