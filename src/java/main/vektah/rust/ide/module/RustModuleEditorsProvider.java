package vektah.rust.ide.module;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleConfigurationEditor;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ui.configuration.DefaultModuleConfigurationEditorFactory;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationEditorProvider;
import com.intellij.openapi.roots.ui.configuration.ModuleConfigurationState;

import java.util.ArrayList;
import java.util.List;

public class RustModuleEditorsProvider implements ModuleConfigurationEditorProvider {
    public ModuleConfigurationEditor[] createEditors(ModuleConfigurationState state) {
        final Module module = state.getRootModel().getModule();

        final ModuleType moduleType = ModuleType.get(module);

        if (!(moduleType instanceof RustModuleType)) {
              return ModuleConfigurationEditor.EMPTY;
        }

        final DefaultModuleConfigurationEditorFactory editorFactory = DefaultModuleConfigurationEditorFactory.getInstance();
        List<ModuleConfigurationEditor> editors = new ArrayList<ModuleConfigurationEditor>();
        editors.add(editorFactory.createModuleContentRootsEditor(state));
        editors.add(editorFactory.createOutputEditor(state));
        editors.add(editorFactory.createClasspathEditor(state));

        return editors.toArray(new ModuleConfigurationEditor[editors.size()]);
    }
}
