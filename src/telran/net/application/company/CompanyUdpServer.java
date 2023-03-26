package telran.net.application.company;

import telran.net.UdpServer;

public class CompanyUdpServer {
	public static void main(String[] args) throws Exception {
		UdpServer server = new UdpServer(4000, new CompanyProtocol());
		server.run();
	}
}