package vektah.rust.cargo.project;

import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.externalSystem.model.DataNode;
import com.intellij.openapi.externalSystem.model.project.ProjectData;
import com.intellij.openapi.externalSystem.service.project.manage.ProjectDataManager;
import com.intellij.openapi.externalSystem.service.project.wizard.AbstractExternalProjectImportBuilder;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import vektah.rust.cargo.Cargo;

import javax.swing.*;
import java.io.File;

public class CargoProjectImportBuilder extends AbstractExternalProjectImportBuilder<CargoImportControl> {

    public CargoProjectImportBuilder(@NotNull ProjectDataManager projectDataManager) {
        super(projectDataManager, new CargoImportControl(), CargoProjectSystem.ID);
    }

    @NotNull
    @Override
    public String getName() {
        return Cargo.NAME;
    }

    @Override
    public Icon getIcon() {
        return Cargo.ICON;
    }

    @Override
    protected void doPrepare(WizardContext wizardContext) {}

    @Override
    protected void beforeCommit(DataNode<ProjectData> dataNode, Project project) {}

    @NotNull
    @Override
    protected File getExternalProjectConfigToUse(File file) {
        return file;
    }

    @Override
    protected void applyExtraSettings(@NotNull WizardContext wizardContext) {}
}
