package telran.io.Logger;

import java.time.Instant;
import java.time.ZoneId;

public class Logger {
	private Level level;
	private Handler handler;
	private String name;

	public Logger(Handler handler, String name) {
		this.handler = handler;
		this.name = name;
		this.level = Level.INFO;
	}

	public void error(String message) {
		createLoggerRecords(message, Level.ERROR);
	}

	public void warn(String message) {
		createLoggerRecords(message, Level.WARN);
	}

	public void info(String message) {
		createLoggerRecords(message, Level.INFO);
	}

	public void debug(String message) {
		createLoggerRecords(message, Level.DEBUG);
	}

	public void trace(String message) {
		createLoggerRecords(message, Level.TRACE);
	}

	private void createLoggerRecords(String message, Level levelInfo) {
		if (level.compareTo(levelInfo) <= 0) {
			LoggerRecord loggerRecord = new LoggerRecord(Instant.now(), ZoneId.systemDefault().getId(), 
					levelInfo, name, message);
			handler.publish(loggerRecord);
		}
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
}