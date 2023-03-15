package telran.employees;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	long id;
	String name;
	LocalDate birthDate;
	String department;
	int salary;

	public Employee(long id, String name, LocalDate birthDate, String department, int salary) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.department = department;
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public int getSalary() {
		return salary;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		
		return Objects.equals(department, other.department) && salary == other.salary && id == other.id && Objects.equals(name, other.name)
				 && birthDate == other.birthDate;
	}

}