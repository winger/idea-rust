package vektah.rust;

import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiElement;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.Nullable;
import vektah.rust.psi.*;

import javax.swing.*;

public class RustIcons {
	public static final Icon ICON_RUST_16 = IconLoader.getIcon("/vektah/rust/icons/logo16.png");
	public static final Icon ICON_RUST_24 = IconLoader.getIcon("/vektah/rust/icons/logo24.png");
	public static final Icon ICON_RUST_32 = IconLoader.getIcon("/vektah/rust/icons/logo32.png");
	public static final Icon ICON_RUST_48 = IconLoader.getIcon("/vektah/rust/icons/logo48.png");

    // TODO: Replace the following items with proper icons
    public static final Icon ICON_MODULE = PlatformIcons.PACKAGE_ICON;
    public static final Icon ICON_STRUCT = PlatformIcons.CLASS_ICON;
    private static final Icon ICON_METHOD = PlatformIcons.METHOD_ICON;
    private static final Icon ICON_FUNCTION = PlatformIcons.FUNCTION_ICON;
    private static final Icon ICON_IMPL = PlatformIcons.CLASS_ICON;
    private static final Icon ICON_TRAIT = PlatformIcons.INTERFACE_ICON;
    private static final Icon ICON_STATIC = PlatformIcons.VARIABLE_ICON;
    private static final Icon ICON_ENUM = PlatformIcons.ENUM_ICON;
    private static final Icon ICON_PROPERTY = PlatformIcons.PROPERTY_ICON;

    @Nullable
    public static Icon forNode(PsiElement psiElement) {
        if (psiElement instanceof RustModItem) {
            return ICON_MODULE;
        } else if (psiElement instanceof RustStructItem) {
            return ICON_STRUCT;
        } else if (psiElement instanceof RustFnItem
                && psiElement.getParent() instanceof RustImplBody) {
            return ICON_METHOD;
        } else if (psiElement instanceof RustFnItem) {
            return ICON_FUNCTION;
        } else if (psiElement instanceof RustImplItem) {
            return ICON_IMPL;
        } else if (psiElement instanceof RustTraitItem) {
            return ICON_TRAIT;
        } else if (psiElement instanceof RustStaticItem) {
            return ICON_STATIC;
        } else if (psiElement instanceof RustEnumItem) {
            return ICON_ENUM;
        } else if (psiElement instanceof RustStructProperty) {
            return ICON_PROPERTY;
        } else if (psiElement instanceof RustFile) {
            return ICON_RUST_16;
        }

        return null;
    }
}
