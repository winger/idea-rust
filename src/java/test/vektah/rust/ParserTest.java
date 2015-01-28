package vektah.rust;

import com.intellij.testFramework.ParsingTestCase;

import java.io.File;

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
	public void testmacro_rules() { doTest(true); }
	public void testconst() { doTest(true); }
	public void testlifetime() { doTest(true); }
	public void testassociated() { doTest(true); }
	public void testwhere() { doTest(true); }
	public void testiflet() { doTest(true); }
	public void testarray() { doTest(true); }
	public void testbounds() { doTest(true); }

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

	@Override
	protected void doTest(boolean checkResult) {
		validateTestFile();
		super.doTest(checkResult);
	}

	/**
	 * Validates test .rs file against rustc compiler.
	 * Rust bin directory must be in system environment as {@code %PATH%}
	 * or in gradle.properties as {@code rust_binary}.
	 */
	private void validateTestFile() {
		String filename = getTestName(false) + ".rs";
		try {
			Process p = Runtime.getRuntime().exec(
					new String[]{rustBinaryPath() + "rustc", "-Z", "parse-only", filename},
					null, new File(getTestDataPath()));

			p.waitFor();
			String output = convertStreamToString(p.getErrorStream());
			System.out.println(output);
			if (output.contains("error:")) {
				fail(filename + " is not valid");
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	private static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	private static String rustBinaryPath() {
		String path = System.getProperty("rust.binary");
		return (path == null) ? "" : path; // running it from current dir if not specified
	}
}
