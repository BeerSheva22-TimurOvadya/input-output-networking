package telran.employees;

import java.io.*;
import java.util.*;

public class CompanyImpl implements Company {
	private static final long serialVersionUID = 5675567612109845783L;

	private final Map<Long, Employee> emploId = new HashMap<>();
	private final Map<Integer, List<Employee>> emploBirth = new HashMap<>();
	private final Map<String, List<Employee>> emploDepart = new HashMap<>();
	private final Map<Integer, List<Employee>> emploSal = new TreeMap<>();

	@Override
	public Iterator<Employee> iterator() {
		return emploId.values().iterator();
	}

	@Override
	public Employee getEmployee(long id) {
		return emploId.get(id);
	}

	@Override
	public boolean addEmployee(Employee emplo) {
		boolean res = emploId.put(emplo.getId(), emplo) == null;
		emploBirth.computeIfAbsent(emplo.getBirthDate().getMonthValue(), x -> new ArrayList<>()).add(emplo);
		emploDepart.computeIfAbsent(emplo.getDepartment(), x -> new ArrayList<>()).add(emplo);
		emploSal.computeIfAbsent(emplo.getSalary(), x -> new ArrayList<>()).add(emplo);
		return res;
	}

	@Override
	public Employee removeEmployee(long id) {
		Employee employee = emploId.remove(id);
		if (employee != null) {
			removeFromIndex(emploBirth, employee.getBirthDate().getMonthValue(), employee);
			removeFromIndex(emploDepart, employee.getDepartment(), employee);
			removeFromIndex(emploSal, employee.getSalary(), employee);
		}

		return employee;
	}

	private <T> void removeFromIndex(Map<T, List<Employee>> index, T obj, Employee employee) {
		List<Employee> set = index.get(obj);
		set.remove(employee);
		if (set.isEmpty()) {
			index.put(obj, set);
		}

	}

	@Override
	public List<Employee> getAllEmployees() {
		return emploId.values().stream().toList();
	}

	@Override
	public List<Employee> getEmployeesByMonthBirth(int month) {
		return emploBirth.get(month);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		ArrayList<Employee> res = new ArrayList<>();
		((TreeMap<Integer, List<Employee>>) emploSal).subMap(salaryFrom, salaryTo).values().forEach(x -> res.addAll(x));
		return res;
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		return emploDepart.get(department);
	}

	@Override
	public void save(String pathName) throws Exception {
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathName))) {
			output.writeObject(getAllEmployees());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restore(String pathName) throws Exception {
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(pathName))) {
			List<Employee> emplo = (List<Employee>) input.readObject();
			emplo.forEach(this::addEmployee);
		}
	}
}