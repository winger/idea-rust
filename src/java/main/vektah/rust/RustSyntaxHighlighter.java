package vektah.rust;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import vektah.rust.psi.RustTokens;

import java.io.Reader;

public class RustSyntaxHighlighter extends SyntaxHighlighterBase {
	public static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey("KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey("IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
	public static final TextAttributesKey BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
	public static final TextAttributesKey LINE_COMMENT = TextAttributesKey.createTextAttributesKey("LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey("STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey("NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey BRACKETS = TextAttributesKey.createTextAttributesKey("BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
	public static final TextAttributesKey BRACES = TextAttributesKey.createTextAttributesKey("BRACES", DefaultLanguageHighlighterColors.BRACES);
	public static final TextAttributesKey PARENTHESES = TextAttributesKey.createTextAttributesKey("PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
	public static final TextAttributesKey COMMA = TextAttributesKey.createTextAttributesKey("COMMA", DefaultLanguageHighlighterColors.COMMA);
	public static final TextAttributesKey SYMBOL = TextAttributesKey.createTextAttributesKey("SYMBOL", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);
	public static final TextAttributesKey SEMICOLON = TextAttributesKey.createTextAttributesKey("SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
	public static final TextAttributesKey OPERATOR = TextAttributesKey.createTextAttributesKey("OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

	public static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
	public static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
	public static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
	public static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
	public static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
	public static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
	public static final TextAttributesKey[] BRACKET_KEYS = new TextAttributesKey[]{BRACKETS};
	public static final TextAttributesKey[] BRACE_KEYS = new TextAttributesKey[]{BRACES};
	public static final TextAttributesKey[] PARENTHESES_KEYS = new TextAttributesKey[]{PARENTHESES};
	public static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
	public static final TextAttributesKey[] SYMBOL_KEYS = new TextAttributesKey[]{SYMBOL};
	public static final TextAttributesKey[] SEMICOLON_KEYS = new TextAttributesKey[]{SEMICOLON};
	public static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
	public static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

	@NotNull
	@Override
	public Lexer getHighlightingLexer() {
		return new FlexAdapter(new RustLexer((Reader) null));
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType type) {
		if (type.equals(RustTokens.KEYWORD)) {
			return KEYWORD_KEYS;
		}
		if (type.equals(RustTokens.IDENTIFIER)) {
			return IDENTIFIER_KEYS;
		}
		if (type.equals(RustTokens.BLOCK_COMMENT)) {
			return BLOCK_COMMENT_KEYS;
		}
		if (type.equals(RustTokens.LINE_COMMENT)) {
			return LINE_COMMENT_KEYS;
		}
		if (type.equals(RustTokens.CHAR_LIT) || type.equals(RustTokens.STRING_LIT) || type.equals(RustTokens.RAW_STRING_LIT)) {
			return STRING_KEYS;
		}
		if (type.equals(RustTokens.DEC_LIT) || type.equals(RustTokens.OCT_LIT) || type.equals(RustTokens.HEX_LIT) || type.equals(RustTokens.BIN_LIT)) {
			return NUMBER_KEYS;
		}
		if (type.equals(RustTokens.HASH) || type.equals(RustTokens.DOUBLE_COLON)) {
			return SYMBOL_KEYS;
		}
		if (type.equals(RustTokens.OPEN_PAREN) || type.equals(RustTokens.CLOSE_PAREN)) {
			return PARENTHESES_KEYS;
		}
		if (type.equals(RustTokens.OPEN_BRACE) || type.equals(RustTokens.CLOSE_BRACE)) {
			return BRACE_KEYS;
		}
		if (type.equals(RustTokens.OPEN_SQUARE_BRACKET) || type.equals(RustTokens.CLOSE_SQUARE_BRACKET)) {
			return BRACKET_KEYS;
		}
		if (type.equals(RustTokens.GREATER_THAN) || type.equals(RustTokens.LESS_THAN)) {
			return OPERATOR_KEYS;
		}
		if (type.equals(RustTokens.COMMA)) {
			return COMMA_KEYS;
		}
		if (type.equals(RustTokens.SEMICOLON)) {
			return SEMICOLON_KEYS;
		}

		return EMPTY_KEYS;
	}
}
