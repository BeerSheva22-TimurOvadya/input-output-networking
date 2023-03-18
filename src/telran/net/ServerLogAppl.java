package telran.net;

import java.util.HashMap;

import telran.net.application.ServerTcpExampleAppl;
import telran.util.Level;

public class ServerLogAppl {

	public static void main(String[] args) throws Exception {
		ServerTcpExampleAppl.main(args);
	}

	public static String processLogRequest(String record, HashMap<String, Integer> counters) {
		Level level = getLevel(record);
		String res = "Wrong Level";
		if (level != null) {
			res = "ok";
			counters.merge(level.toString(), 1, Integer :: sum);
		}
		return res;
	}

	private static Level getLevel(String record) {
		Level levels[] = Level.values();
		int length = levels.length;
		int i = 0;
		while (!record.contains(levels[i].toString())) {
			i++;
		}
		return i > length ? null : levels[i];
	}

}