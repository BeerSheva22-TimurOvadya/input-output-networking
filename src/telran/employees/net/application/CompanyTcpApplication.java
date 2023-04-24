package telran.employees.net.application;

import java.time.LocalDateTime;
import java.util.Scanner;

import telran.employees.Company;
import telran.employees.CompanyImpl;
import telran.employees.net.CompanyProtocol;
import telran.net.Protocol;
import telran.net.TcpServer;

public class CompanyTcpApplication {
	private static final int PORT = 4000;
	private static final String FILE_NAME = "database.data";

	public static void main(String[] args) throws Exception {
		Company company = new CompanyImpl();
		try {
			company.restore(FILE_NAME);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		Protocol serverProtocol = new CompanyProtocol(company);
		TcpServer tcpServer = new TcpServer(serverProtocol, PORT);
		Thread serverThread = new Thread(tcpServer);
		serverThread.start();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while(running) {
			System.out.println("For shutdown the server enter command 'shutdown'");
			if (scanner.nextLine().equals("shutdown")) {
				tcpServer.shutdown();
				serverThread.join();
				company.save(FILE_NAME);
				System.out.println(LocalDateTime.now().toString() + ": Company is saved");
				running = false;
			}
		}
		System.out.println(LocalDateTime.now().toString() + ": Programm was shutted down");
	}

}
