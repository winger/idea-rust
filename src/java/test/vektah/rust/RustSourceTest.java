package vektah.rust;

import com.intellij.openapi.fileEditor.impl.LoadTextUtil;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.testFramework.ParsingTestCase;
import org.junit.Ignore;

import java.io.File;
import java.io.IOException;

/**
 * Tests that cover parts of the rust compiler source tree. Will be expanded for complete coverage over time.
 *
 * There doesn't appear to be an easy way to do parametrized tests with junit 3 and the intellij test suites all
 * seem to extend TestCase. Woe is me.
 *
 * TODO: These test cases need to be fixed at some point - these are highlighting some syntax issues
 */
@Ignore
public class RustSourceTest extends ParsingTestCase {
	public RustSourceTest() {
		super("", "rs", new RustParserDefinition());
	}

	@Override
	protected String getTestDataPath() {
		return System.getProperty("rust.source");
	}

	public void testCompileTest() { doAllTests("compiletest"); }
	public void testLibArena() { doAllTests("libarena"); }
	public void testLibCollections() { doAllTests("libcollections"); }
	public void testLibCore() { doAllTests("libcore"); }
	public void testLibFlate() { doAllTests("libflate"); }
	public void testLibGetOpts() { doAllTests("libgetopts"); }
	public void testLibGraphviz() { doAllTests("libgraphviz"); }
	public void testLibC() { doAllTests("liblibc"); }
	public void testLibLog() { doAllTests("liblog"); }
	public void testLibRand() { doAllTests("librand"); }
	public void testLibRegex() { doAllTests("libregex"); }
	public void testLibRustDoc() { doAllTests("librustdoc"); }

	public void testLibSerialize() { doAllTests("libserialize"); }
	public void testLibSyntax() { doAllTests("libsyntax"); }
	public void testLibTerm() { doAllTests("libterm"); }
	public void testLibTest() { doAllTests("libtest"); }
	public void testLibUV() { doAllTests("libuv"); }

	public void testLibRustC() {
		doAllTests("librustc", new String[] {
			"middle/typeck/infer/test.rs", // Not valid rust as of 574cbe5b07042c448c198af371803f977977b74f
		});
	}

	public void testLibStd() {
		doAllTests("libstd", new String[] {
			"vec.rs" // TODO: Unusual syntax 'let mut count_x @ mut count_y = 0;'
		});
	}

	protected void doAllTests(String dir) {
		doAllTests(dir, new String[] {});
	}

	protected void doAllTests(String dir, String[] ignore) {
		doAllTests(new File(getTestDataPath() + dir), ignore);
	}

	protected void doAllTests(File dir_or_filename, String[] ignore) {
		if (dir_or_filename.isFile() && !dir_or_filename.getName().endsWith(".rs")) {
			return;
		}

		for (String ignore_file: ignore) {
			if (dir_or_filename.getAbsolutePath().endsWith(ignore_file)) {
				return;
			}
		}

		if (!dir_or_filename.isDirectory()) {
			doTest(dir_or_filename);
		}

		File[] files = dir_or_filename.listFiles();
		if (files != null) {
			for (File file : files) {
				doAllTests(file, ignore);
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
