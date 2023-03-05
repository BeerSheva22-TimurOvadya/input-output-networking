package telran.io.test;
import java.io.*;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InputOutputTest {
String fileName = "myFile";
String directoryName = "myDirectory1/myDirectory2";
	@BeforeEach
	void setUp() throws Exception {
		new File(fileName).delete();
		new File(directoryName).delete();
	}

	@Test
	void testFile() throws IOException {
		File f1 = new File(fileName);
		assertTrue(f1.createNewFile());
		File dir1 = new File(directoryName);
		assertTrue(dir1.mkdirs());
		System.out.println(dir1.getAbsolutePath());
		
		
	}
	@Test
	void printDirectoryFileTest() {
		//TODO testing printDirectoryFile
	}
	void printDirectoryFile(String path, int maxLevel) {
		//TODO based on class File
		//path -directory path
		//maxLevel - maximal level of printing, if maxLevel < 1, all levels should be printed
		//output format
		//  <directory name (no points, no full absolute path)
		//     <node name> - dir | file
		//          <node_name> .....
		//     <node name> -
		//          <node name> - dir | file
		//                <node_name> .....
		//     <node name> -
	}
	@Test
	void testFiles() {
		Path path = Path.of(".");
		System.out.println(path.toAbsolutePath().getNameCount());
		
		
	}
	@Test
	void printDirectoryFilesTest() {
		//TODO testing printDirectoryFiles
	}
	void printDirectoryFiles(String path, int maxLevel) {
		//TODO based on class Files
		//path -directory path
		//maxLevel - maximal level of printing, if maxLevel < 1, all levels should be printed
		//output format
		//  <directory name (no points, no full absolute path)
		//     <node name> - dir | file
		//          <node_name> .....
		//     <node name> -
		//          <node name> - dir | file
		//                <node_name> .....
		//     <node name> -
	}

}