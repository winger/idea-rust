package vektah.rust;

import com.intellij.openapi.fileEditor.impl.LoadTextUtil;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.testFramework.ParsingTestCase;

import java.io.IOException;

/**
 * Tests that cover parts of the rust compiler source tree. Will be expanded for complete coverage over time.
 */
public class RustSourceTest extends ParsingTestCase {
	public RustSourceTest() {
		super("", "rs", new RustParserDefinition());
	}

	@Override
	protected String getTestDataPath() {
		return "/home/adam/projects/rust/src/";
	}

	/**
	 * There doesn't appear to be an easy way to do parametrized tests with junit 3 and the intellij test suites all
	 * seem to extend TestCase. Woe is me.
	 */
	public void testRegex() {
		doTest("libregex/re");
	}

	public void doTest(String name) {
		try {
			String text = loadFile(name + "." + myFileExt);
			myFile = createPsiFile(name, text);
			ensureParsed(myFile);
			assertEquals("light virtual file text mismatch", text, ((LightVirtualFile)myFile.getVirtualFile()).getContent().toString());
			assertEquals("virtual file text mismatch", text, LoadTextUtil.loadText(myFile.getVirtualFile()));
			assertEquals("doc text mismatch", text, myFile.getViewProvider().getDocument().getText());
			assertEquals("psi text mismatch", text, myFile.getText());

			String parseTree = toParseTreeText(myFile, skipSpaces(), includeRanges());
			assertFalse("rust source tree file " + name + " contains errors!", parseTree.contains("PsiErrorElement"));
			assertFalse("rust source tree file " + name + " contains dummyblocks!", parseTree.contains("DummyBlock"));

		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
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
