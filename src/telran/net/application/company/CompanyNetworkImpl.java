package telran.net.application.company;

import java.io.Serializable;
import java.util.*;
import telran.employees.*;
import telran.employees.test.Utils;
import telran.net.*;


public class CompanyNetworkImpl implements Company {
	private static final long serialVersionUID = 1L;
	private final String hostname;
	private final int port;
	private final String connectionType;

	public CompanyNetworkImpl(String hostname, int port, String connectionType) {
		this.hostname = hostname;
		this.port = port;
		this.connectionType = connectionType;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		return send("addEmployee", employee);
	}

	@Override
	public Employee removeEmployee(long id) {
		return send("removeEmployee", id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return send("getAllEmployees", null);
	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		return send("getEmployeesByMonthBirth", month);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		return send("getEmployeesBySalary", new int[] { salaryFrom, salaryTo });
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return send("getEmployeesByDepartment", department);
	}

	@Override
	public Employee getEmployee(long id) {
		return send("getEmployee", id);
	}

	@Override
	public void save(String pathName) {
		send("save", pathName);

	}

	@Override
	public void restore(String pathName) {
		send("restore", pathName);
	}

	private <T> T send(String type, Serializable data) {
		try (NetworkClient client = switch (connectionType) {
		case "udp" -> new UdpClient(hostname, port);
		case "tcp" -> new TcpClient(hostname, port);
		default -> throw new IllegalArgumentException("Unknown connection type");
		}) {
			T res = client.send(type, data);
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e + "");
		}
	}

	@Override
	public Iterator<Employee> iterator() {
		return getAllEmployees().iterator();
	}
}