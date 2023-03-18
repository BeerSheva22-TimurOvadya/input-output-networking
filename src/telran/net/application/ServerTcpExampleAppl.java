package telran.net.application;

import java.io.*;
import java.net.*;
import java.util.HashMap;

import telran.net.ServerLogAppl;




public class ServerTcpExampleAppl {

	private static final int PORT = 4000;
	private static HashMap<String, Integer> counters = new HashMap<>();

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("server listening on port " + PORT);
		while (true) {
			Socket socket = serverSocket.accept();
			try {
				runServerClient(socket);
			} catch (IOException e) {
				System.out.println("abnormal closing connection");
			}
		}

	}

	private static void runServerClient(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream writer = new PrintStream(socket.getOutputStream());
		while (true) {
			String request = reader.readLine();
			if (request == null) {
				break;
			}
			String response = getResponse(request);
			writer.println(response);
		}
		System.out.println("client closed connection");

	}

	private static String getResponse(String request) {
		String res = "Wrong Request";
		String tokens[] = request.split("#");
		if (tokens.length == 2) {
			res = switch (tokens[0]) {
			case "reverse" -> new StringBuilder(tokens[1]).reverse().toString();
			case "length" -> tokens[1].length() + "";
			case "counter" -> "" + counters.getOrDefault(tokens[1].toUpperCase(), 0);
			case "log" -> ServerLogAppl.processLogRequest(tokens[1], counters);
			default -> "Wrong type " + tokens[0];
			};
		}
		return res;
	}

}