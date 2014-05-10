package vektah.rust;

import com.intellij.testFramework.ParsingTestCase;

public class ParserTest extends ParsingTestCase {
	public ParserTest() {
		super("", "rs", new RustParserDefinition());
	}

	public void testcomments() { doTest(true); }
	public void testliterals() { doTest(true); }
	public void testsimple() { doTest(true); }
	public void testtuple() { doTest(true); }
	public void teststruct() { doTest(true); }
	public void testclosure() { doTest(true); }
	public void testenum() { doTest(true); }
	public void testbox() { doTest(true); }
	public void testmatch() { doTest(true); }

	@Override
	protected String getTestDataPath() {
		return "/home/adam/projects/idea-rust/src/rust/";
	}

	@Override
	protected boolean skipSpaces() {
		return true;
	}

	@Override
	protected boolean includeRanges() {
		return false;
	}
}
