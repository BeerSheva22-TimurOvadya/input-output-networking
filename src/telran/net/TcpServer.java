package telran.net;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer implements Runnable {
private Protocol protocol;
private int port;
private ExecutorService executor;
private ServerSocket serverSocket;

private boolean running;

	@Override
	public void run() {
		System.out.println("Server listening on port " + this.port);
		while(running) {
			try {
				Socket socket = serverSocket.accept();
				TcpServerClient serverClient = new TcpServerClient(socket, protocol);
				executor.execute(serverClient);
			} catch (SocketTimeoutException e) {
				// Ignore socket timeouts
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

	}

	public TcpServer(Protocol protocol, int port) throws Exception{
		this.protocol = protocol;
		this.port = port;
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(1000);
		int nThreads = Runtime.getRuntime().availableProcessors();
		System.out.println("Number threads in Threads Pool is: " + nThreads);
		executor = Executors.newFixedThreadPool(nThreads);
		running = true;
		
	}
	
	public void shutdown() throws IOException  {
		running = false;
		serverSocket.close();
		executor.shutdown();
	}

}