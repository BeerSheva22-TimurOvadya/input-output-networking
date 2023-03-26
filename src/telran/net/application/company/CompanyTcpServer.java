package telran.net.application.company;

import telran.net.TcpServer;

public class CompanyTcpServer {
	public static void main(String[] args) throws Exception {
		TcpServer server = new TcpServer(new CompanyProtocol(), 4000);
		server.run();
	}
}