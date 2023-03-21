package telran.net;

import java.io.*;
import java.net.*;

public class TcpClient implements NetworkClient {
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public TcpClient(String hostname, int port) throws Exception {
		socket = new Socket(hostname, port);
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void close() throws IOException {
		socket.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T send(String requestType, Serializable requestData) {
		Request request = new Request(requestType, requestData);
		try {
			output.writeObject(request);
			Response response = (Response) input.readObject();
			if (response.code == ResponseCode.OK) {
				return (T) response.data;
			}
			throw new Exception(response.data.toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

}
