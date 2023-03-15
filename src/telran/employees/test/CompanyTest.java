package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.*;

import telran.employees.*;

class CompanyTest {

	String path = "Company.txt";
	Company company = new CompanyImpl();	

	Employee Vasya = new Employee(1, "Vasya", LocalDate.of(1990, 12, 26), "Financial department", 6700);
	Employee Nikolay = new Employee(2, "Nikolay", LocalDate.of(1975, 2, 20), "Financial department", 12500);
	Employee Sergey = new Employee(3, "Sergey", LocalDate.of(1495, 1, 18), "Security department", 10000);
	Employee Masha = new Employee(4, "Masha", LocalDate.of(1986, 03, 25), "Security department", 8000);
	Employee Alina = new Employee(5, "Alina", LocalDate.of(1994, 03, 23), "Sales department", 12500);
	Employee Vova = new Employee(6, "Vova", LocalDate.of(1853, 1, 1), "Sales department", 10000);

	@BeforeEach
	void setUp()  {
		company.addEmployee(Vasya);
		company.addEmployee(Nikolay);
		company.addEmployee(Sergey);
		company.addEmployee(Masha);
		company.addEmployee(Alina);
		company.addEmployee(Vova);
	}

	@Test
	void getDepartmentTest() {
		Employee[] expected1 = { Vasya, Nikolay };
		assertArrayEquals(expected1, arrayDepartment("Financial department"));

		Employee[] expected2 = {Sergey, Masha };
		assertArrayEquals(expected2, arrayDepartment("Security department"));

		Employee[] expected3 = { Alina, Vova };
		assertArrayEquals(expected3, arrayDepartment("Sales department"));
	}

	private Object[] arrayDepartment(String departament) {
		return company.getEmployeesByDepartment(departament).stream().sorted(Comparator.comparingLong(Employee::getId))
				.toArray(Employee[]::new);
	}
	

	@Test
	void getSalaryTest() {

		Employee[] expected1 = { Nikolay, Alina };
		assertArrayEquals(expected1, arraySalary(12000, 50000));

		Employee[] expected2 = { Vasya, Nikolay, Sergey, Masha, Alina, Vova, };
		assertArrayEquals(expected2, arraySalary(0, 20000));

		Employee[] expected3 = { Nikolay, Sergey, Alina, Vova };
		assertArrayEquals(expected3, arraySalary(9900, 12501));
	}

	private Object[] arraySalary(int from, int to) {
		return company.getEmployeesBySalary(from, to).stream().sorted(Comparator.comparingLong(Employee::getId))
				.toArray(Employee[]::new);
	}

	@Test
	void getEmployeesByMonthBirthTest() {
		Employee[] expected1 = { Masha, Alina };
		assertArrayEquals(expected1, arraymonthBirth(3));

		Employee[] expected2 = { Sergey, Vova };
		assertArrayEquals(expected2, arraymonthBirth(1));

		Employee[] expected3 = { Nikolay };
		assertArrayEquals(expected3, arraymonthBirth(2));		
	}

	private Employee[] arraymonthBirth(int month) {
		return company.getEmployeesByMonthBirth(month).stream().sorted(Comparator.comparingLong(Employee::getId))
				.toArray(Employee[]::new);
	}

	@Test
	void removeTest() {
		Employee employee = company.removeEmployee(3);
		assertEquals("Sergey", employee.getName());
		assertNull(company.getEmployee(3));		
	}

	@Test
	void saveTest() throws Exception {
		company.save(path);
	}
	
	@Test
	 void restoreCompany() throws Exception {		
		company = new CompanyImpl();
		company.restore(path);
	}
	
	@Test
	void restorePrintingTest() throws Exception {
		Company companyRestored = new CompanyImpl();
		companyRestored.restore(path);
		companyRestored.getAllEmployees()
				.forEach(e -> System.out.printf("ID: %d, NAME: %s, BIRTH DAY: %s, DEPARTMENT: %s, SALARY: %d.\n",
						e.getId(), e.getName(), e.getBirthDate().toString(), e.getDepartment(), e.getSalary()));
	}
}