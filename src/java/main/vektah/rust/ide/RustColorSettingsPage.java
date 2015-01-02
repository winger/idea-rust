package vektah.rust.ide;

import com.intellij.application.options.colors.InspectionColorSettingsPage;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.psi.codeStyle.DisplayPrioritySortable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vektah.rust.RustIcons;
import vektah.rust.RustSyntaxHighlighter;
import vektah.rust.i18n.RustBundle;

import javax.swing.*;
import java.util.Collections;
import java.util.Map;

public class RustColorSettingsPage implements ColorSettingsPage, InspectionColorSettingsPage, DisplayPrioritySortable {
	private static final AttributesDescriptor[] descriptors = new AttributesDescriptor[] {
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.keyword"), RustSyntaxHighlighter.KEYWORD),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.identifier"), RustSyntaxHighlighter.IDENTIFIER),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.block_comment"), RustSyntaxHighlighter.BLOCK_COMMENT),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.line_comment"), RustSyntaxHighlighter.LINE_COMMENT),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.doc_comment"), RustSyntaxHighlighter.DOC_COMMENT),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.string"), RustSyntaxHighlighter.STRING),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.number"), RustSyntaxHighlighter.NUMBER),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.brackets"), RustSyntaxHighlighter.BRACKETS),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.braces"), RustSyntaxHighlighter.BRACES),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.parentheses"), RustSyntaxHighlighter.PARENTHESES),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.comma"), RustSyntaxHighlighter.COMMA),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.symbol"), RustSyntaxHighlighter.SYMBOL),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.semicolon"), RustSyntaxHighlighter.SEMICOLON),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.operator"), RustSyntaxHighlighter.OPERATOR),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.path_separator"), RustSyntaxHighlighter.PATH_SEPARATOR),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.attribute"), RustSyntaxHighlighter.ATTRIBUTE),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.delimiter"), RustSyntaxHighlighter.DELIMITER),
			new AttributesDescriptor(RustBundle.message("options.rust.attribute.descriptor.bad_char"), RustSyntaxHighlighter.BAD_CHAR),
	};

	@Nullable
	@Override
	public Icon getIcon() {
		return RustIcons.ICON_RUST_16;
	}

	@NotNull
	@Override
	public SyntaxHighlighter getHighlighter() {
		return new RustSyntaxHighlighter();
	}

	@NotNull
	@Override
	public String getDemoText() {
		return "use std::rand;\n" +
				"\n" +
				"/**\n" +
				" * Our main function\n" +
				" */\n" +
				"fn main() {\n" +
				"    println!(\"Hello world!\"); // Typical first line\n" +
				"    /* This is a very long comment\n" +
				"    in two lines */\n" +
				"    let rng : f32 = rand::random();\n" +
				"    println(\"My random number {}, a tuple {} and a char {}\", rng + 42, (1, 2), 'i');\n" +
				"}\n";
	}

	@Nullable
	@Override
	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
		return Collections.emptyMap();
	}

	@NotNull
	@Override
	public AttributesDescriptor[] getAttributeDescriptors() {
		return descriptors;
	}

	@NotNull
	@Override
	public ColorDescriptor[] getColorDescriptors() {
		return ColorDescriptor.EMPTY_ARRAY;
	}

	@NotNull
	@Override
	public String getDisplayName() {
		return RustBundle.message("language.name.rust");
	}

	@Override
	public DisplayPriority getPriority() {
		return DisplayPriority.KEY_LANGUAGE_SETTINGS;
	}


}
