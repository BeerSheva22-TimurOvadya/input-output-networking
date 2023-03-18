package telran.util;
import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;



public class TcpClientHandler implements Handler {
	Socket socket;
	PrintStream stream;
	BufferedReader input;
	public TcpClientHandler(String hostName, int port) {		
		try {
			socket = new Socket(hostName, port);
			stream = new PrintStream(socket.getOutputStream());
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			throw new RuntimeException(e.toString());
		}
	}

	

	@Override
	public void publish(LoggerRecord loggerRecord) {
		String dateTimeFormatted = ZonedDateTime.ofInstant(loggerRecord.timestamp, ZoneId.of(loggerRecord.zoneId))
				.format(DateTimeFormatter.RFC_1123_DATE_TIME);
		stream.printf("log" + "#" + "%s %s %s %s\n", loggerRecord.level, dateTimeFormatted, 
				loggerRecord.loggerName, loggerRecord.message);			
	}
	
	
	@Override
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException("not closed " + e.getMessage());
		}
	}

}