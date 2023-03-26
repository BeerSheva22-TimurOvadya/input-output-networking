package telran.employees.test;

import telran.employees.Company;
import telran.net.application.company.CompanyNetworkImpl;

public class CompanyTcpTest extends CompanyNetworkTest {

	@Override
	protected Company getCompany() {
		return new CompanyNetworkImpl(HOST, PORT, "tcp");
	}

}