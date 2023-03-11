package telran.io.Logger;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class SimpleStreamHandler implements Handler {

	private PrintStream stream;

	public SimpleStreamHandler(PrintStream stream) {
		super();
		this.stream = stream;
	}

	@Override
	public void publish(LoggerRecord loggerRecord) {
		String dateTimeFormatted = ZonedDateTime.ofInstant(loggerRecord.timestamp, ZoneId.of(loggerRecord.zoneId))
				.format(DateTimeFormatter.RFC_1123_DATE_TIME);
		stream.printf("%s %s %s %s\n", loggerRecord.level, dateTimeFormatted, 
				loggerRecord.loggerName, loggerRecord.message);

	}

}