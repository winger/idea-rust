package vektah.rust.actions;

import com.intellij.ide.actions.CreateFileAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import vektah.rust.RustIcons;
import vektah.rust.i18n.RustBundle;
import vektah.rust.ide.module.RustModuleType;

public class NewRustFileAction extends CreateFileAction {

    public NewRustFileAction() {
        super(
            RustBundle.message("action.create.rust.file"),
            RustBundle.message("action.create.rust.file.description"),
            RustIcons.ICON_RUST_16
        );
    }

    @Override
    protected boolean isAvailable(DataContext dataContext) {
        Module module = (Module) dataContext.getData(LangDataKeys.MODULE.getName());
        if (module == null) return false;

        String moduleTypeId = module.getOptionValue(Module.ELEMENT_TYPE);
        return RustModuleType.MODULE_TYPE_ID.equals(moduleTypeId);
    }

    @NotNull
    @Override
    protected PsiElement[] invokeDialog(Project project, PsiDirectory directory) {
        MyValidator validator = new MyValidator(project, directory);
        Messages.showInputDialog(
            project,
            RustBundle.message("prompt.enter.new.rust.file.name"),
            RustBundle.message("title.new.rust.file"),
            Messages.getQuestionIcon(),
            null,
            validator
        );
        return validator.getCreatedElements();
    }

    @NotNull
    @Override
    protected PsiElement[] create(String newName, PsiDirectory directory) throws Exception {
        return super.create(completeFileName(newName), directory);
    }

    private String completeFileName(String name) {
        return name.endsWith(".rs") ? name : name + ".rs";
    }

    //

    protected class MyValidator extends CreateFileAction.MyValidator {
        public MyValidator(Project project, PsiDirectory directory) {
            super(project, directory);
        }

//        @Override
//        public PsiElement[] create(String newName) throws Exception {
//            if (!newName.endsWith(".rs"))
//                newName = newName + ".rs";
//            return super.create(newName);
//        }

        @Override
        public boolean canClose(String inputString) {
            return super.canClose(completeFileName(inputString));
        }
    }
}
