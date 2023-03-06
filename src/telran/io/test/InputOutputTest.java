package telran.io.test;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class InputOutputTest {
	private static final int SPACE = 4;
	String fileName = "myFile";
	String directoryName = "myDirectory1/myDirectory2";

	@BeforeEach
	void setUp() throws Exception {
		new File(fileName).delete();
		new File(directoryName).delete();
	}

	@Test
	@Disabled
	void testFile() throws IOException {
		File f1 = new File(fileName);
		assertTrue(f1.createNewFile());
		File dir1 = new File(directoryName);
		assertTrue(dir1.mkdirs());
		System.out.println(dir1.getAbsolutePath());

	}

	@Test
//	@Disabled
	void printDirectoryFileTest() throws IOException {
		String directory = "C:\\Program Files\\Java\\jdk-17.0.5";
		printDirectoryFile(directory, 1);
	}

	void printDirectoryFile(String path, int maxLevel) throws IOException {
		if (maxLevel < 1) {
			maxLevel = Integer.MAX_VALUE;
		}
		File directory = new File(path).getCanonicalFile();
		printDirectoryFile(directory, maxLevel, 0);
	}

	void printDirectoryFile(File file, int maxLevel, int currentLevel) {
		if (maxLevel >= currentLevel) {
			String type = file.isDirectory() ? "dir" : "file";
			System.out.printf("%s%s (%s)\n", " ".repeat(currentLevel * SPACE), file.getName(), type);
			if (file.isDirectory()) {
				Arrays.stream(file.listFiles()).forEach(n -> printDirectoryFile(n, maxLevel, currentLevel + 1));
			}
		}
	}

	@Test
	@Disabled
	void testFiles() {
		Path path = Path.of(".");
		System.out.println(path.toAbsolutePath().getNameCount());

	}

	@Test
//	@Disabled
	void printDirectoryFilesTest() throws IOException {
		Path directory = Path.of("C:\\Program Files\\Java\\jdk-17.0.5");
		printDirectoryFiles(directory, 1);
	}

	void printDirectoryFiles(Path directory, int maxLevel) throws IOException {
		if (maxLevel < 1) {
			maxLevel = Integer.MAX_VALUE;
		}
		System.out.println(directory.toAbsolutePath().getFileName());
		Files.walk(directory, maxLevel).filter(dir -> dir != directory)
				.forEach(dir -> System.out.printf("%s%s (%s)\n",
						" ".repeat((dir.getNameCount() - directory.getNameCount()) * SPACE), dir.getFileName(),
						Files.isDirectory(dir) ? "dir" : "file"));
	}

}