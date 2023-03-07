package telran.io.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InputOutputStreamTest {
	final static String fileName = "test.txt";
	final static String fileNameCopy = "test-copy.txt";
	 String hello = "Hello World";

	@BeforeAll
	static void setUp() throws Exception {
		Files.deleteIfExists(Path.of(fileName));
	}

	@Order(1)
	@Test
//	@Disabled
	void OutputStreamTest() throws Exception {
		try (OutputStream output = new FileOutputStream(fileName)) {
			byte[] helloBytes = hello.getBytes();
			output.write(helloBytes);
		}
	}
	
	@Test
	void InputStreamTest() throws Exception {
		String file = fileName;
		readFileTest(file);
	}

	private void readFileTest(String file) throws IOException, FileNotFoundException {
		try (InputStream input = new FileInputStream(file)) {
			byte[] buffer = input.readAllBytes();
			String str = new String(buffer);
			assertEquals(hello, str);
		}
	}
	
	@Order(2)
	@Test
	void tresferToTest() throws Exception {
		try(InputStream input = new FileInputStream(fileName); 
				OutputStream output = new FileOutputStream(fileNameCopy); ){
			input.transferTo(output);
		}
	}
	
	@Test
	void copyTest() throws Exception {
		readFileTest(fileNameCopy);
	}
	
}
