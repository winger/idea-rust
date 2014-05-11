package vektah.rust;

import com.intellij.openapi.fileEditor.impl.LoadTextUtil;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.testFramework.ParsingTestCase;

import java.io.File;
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
		doAllTests("libregex");
	}

	protected void doAllTests(String dir) {
		doAllTests(new File(getTestDataPath() + dir));
	}

	protected void doAllTests(File dir_or_filename)
	{
		if (dir_or_filename.isFile() && !dir_or_filename.getName().endsWith(".rs")) {
			return;
		}

		if (!dir_or_filename.isDirectory()) {
			doTest(dir_or_filename);
		}

		File[] files = dir_or_filename.listFiles();
		if (files != null) {
			for (File file : files) {
				doAllTests(file);
			}
		}
	}

	public void doTest(File file) {
		try {
			String text = FileUtil.loadFile(file, CharsetToolkit.UTF8, true).trim();
			myFile = createFile(file.getName(), text);
			ensureParsed(myFile);
			assertEquals("light virtual file text mismatch", text, ((LightVirtualFile)myFile.getVirtualFile()).getContent().toString());
			assertEquals("virtual file text mismatch", text, LoadTextUtil.loadText(myFile.getVirtualFile()));
			assertEquals("doc text mismatch", text, myFile.getViewProvider().getDocument().getText());
			assertEquals("psi text mismatch", text, myFile.getText());

			String parseTree = toParseTreeText(myFile, skipSpaces(), includeRanges());
			assertFalse("rust source tree file " + file.getName() + " contains errors!", parseTree.contains("PsiErrorElement"));
			assertFalse("rust source tree file " + file.getName() + " contains dummyblocks!", parseTree.contains("DummyBlock"));

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
