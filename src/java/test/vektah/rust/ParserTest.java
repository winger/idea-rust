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
	public void testfunctions() { doTest(true); }
	public void testlinked_list() { doTest(true); }
	public void testextern() { doTest(true); }
	public void testimpl() { doTest(true); }
	public void teststatic() { doTest(true); }
	public void testmacro_call() { doTest(true); }
	public void testconst() { doTest(true); }
	public void testlifetime() { doTest(true); }

	@Override
	protected String getTestDataPath() {
		return "./src/rust";
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
