package telran.employees.test;

import telran.employees.*;

public class CompanyImplTest extends CompanyNetworkTest {

	@Override
	protected Company getCompany() {
		return new CompanyImpl();
	}

}