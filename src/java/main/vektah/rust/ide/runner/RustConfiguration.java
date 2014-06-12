package vektah.rust.ide.runner;

import com.google.common.base.Strings;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.RunConfigurationWithSuppressedDefaultRunAction;
import com.intellij.openapi.components.PathMacroManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;

public class RustConfiguration extends ModuleBasedConfiguration<RustModuleBasedConfiguration>
		implements RunConfigurationWithSuppressedDefaultDebugAction, RunConfigurationWithSuppressedDefaultRunAction {
	private String workingDir = "";

	public RustConfiguration(String name, Project project, RustConfigurationType configurationType) {
		super(name, new RustModuleBasedConfiguration(project), configurationType.getConfigurationFactories()[0]);
	}

	@Override
	public Collection<Module> getValidModules() {
		Module[] modules = ModuleManager.getInstance(getProject()).getModules();
		return Arrays.asList(modules);
	}

	@Override
	protected ModuleBasedConfiguration createInstance() {
		return new RustConfiguration(getName(), getProject(), RustConfigurationType.getInstance());
	}

	@NotNull
	@Override
	public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
		return new RustSettingsEditor(getProject());
	}

	@Nullable
	@Override
	public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws ExecutionException {
		Project project = getProject();

		if (Strings.isNullOrEmpty(this.workingDir)) {
			this.workingDir = project.getBaseDir().getCanonicalPath();
		}

		if (executor.getClass().equals(DefaultDebugExecutor.class)) {
			return null;    // FIXME: Add debugging
		}

		CommandLineState state = new RustRunProfileState(getProject(), env, this);

		state.setConsoleBuilder(TextConsoleBuilderFactory.getInstance().createBuilder(project));
		state.addConsoleFilters(new RustConsoleFilter(project, project.getBasePath()));
		return state;
	}

	@Override
	public void readExternal(final Element element) throws InvalidDataException {
		PathMacroManager.getInstance(getProject()).expandPaths(element);
		super.readExternal(element);
		XmlSerializer.deserializeInto(this, element);
		readModule(element);
	}

	@Override
	public void writeExternal(final Element element) throws WriteExternalException {
		super.writeExternal(element);
		XmlSerializer.serializeInto(this, element);
		writeModule(element);
		PathMacroManager.getInstance(getProject()).collapsePathsRecursively(element);
	}
}
