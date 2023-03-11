package telran.io.Logger.test;

import org.junit.jupiter.api.*;

import java.io.*;

import telran.io.Logger.*;

class LoggerTests {

	Handler handler;
	Logger logger;

	@Test
	void loggerFileTest() throws Exception {
		File LogTxtFile = new File("log.txt");
		PrintStream printStream = new PrintStream(LogTxtFile);
		handler = new SimpleStreamHandler(printStream);
		logerSetLevel();
		logsEntry();

	}

	private void logerSetLevel() {
		logger = new Logger(handler, "TEST");
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.WARN);
		logger.setLevel(Level.INFO);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.TRACE);
	}

	private void logsEntry() {
		logger.error("***ERROR***");
		logger.warn("***WARN***");
		logger.info("***INFO***");
		logger.debug("***DEBUG***");
		logger.trace("***TRACE***");
	}

	@Test
	void loggerConsoleTest() {
		handler = new SimpleStreamHandler(System.err);
		logerSetLevel();
		logsEntry();
	}

}
