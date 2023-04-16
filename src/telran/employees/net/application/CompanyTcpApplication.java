package telran.employees.net.application;

import telran.employees.*;
import telran.employees.net.CompanyProtocol;
import telran.net.*;


public class CompanyTcpApplication {
	public static void main(String[] args) throws Exception {
		Company company = new CompanyImpl();
		company.restore("company.data");
		Protocol protocol = new CompanyProtocol(company);
		TcpServer server = new TcpServer(protocol, 4000);
		server.run();
	}
}
