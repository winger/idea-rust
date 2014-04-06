package vektah.rust;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.FlexLexer;

import java.io.Reader;

public class RustLexerAdapater extends FlexAdapter {
	public RustLexerAdapater(FlexLexer flex) {
		super(new RustLexer((Reader) null));
	}
}
