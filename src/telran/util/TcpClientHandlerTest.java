package telran.util;

import java.net.*;

import java.io.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TcpClientHandlerTest {

	private static final int PORT = 4000;

	static TcpClientHandler handler = new TcpClientHandler("localhost", PORT);
	static Logger logger = new Logger(handler, "test");
	static Socket socket;
	static PrintStream stream;
	static BufferedReader reader;

	@BeforeAll
	static void SetUp() throws Exception {
		socket = new Socket("localhost", PORT);
		stream = new PrintStream(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		logger.setLevel(Level.DEBUG);

	}

	@Order(1)
	@Test
	void sendLogTest() {

		IntStream.range(0, 1).forEach(i -> logger.error("" + i));
		IntStream.range(0, 2).forEach(i -> logger.warn("" + i));
		IntStream.range(0, 3).forEach(i -> logger.info("" + i));
		IntStream.range(0, 4).forEach(i -> logger.debug("" + i));
		IntStream.range(0, 5).forEach(i -> logger.trace("" + i));
		handler.close();

	}

	@Test
	void errorTest() throws Exception {
		int actualError = parsing("ERROR");
		assertEquals(1, actualError);

		int actualWarn = parsing("WARN");
		assertEquals(2, actualWarn);

		int actualInfo = parsing("INFO");
		assertEquals(3, actualInfo);

		int actualDebug = parsing("DEBUG");
		assertEquals(4, actualDebug);

		int actualTrace = parsing("TRACE");
		assertNotEquals(5, actualTrace);
	}

	private int parsing(String level) throws Exception {
		stream.println("counter#" + level);
		int actual = Integer.parseInt(reader.readLine());
		return actual;
	}

	@AfterAll
	static void closing() throws IOException {
		socket.close();
	}

}