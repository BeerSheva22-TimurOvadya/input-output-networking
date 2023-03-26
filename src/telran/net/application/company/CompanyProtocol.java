package telran.net.application.company;

import java.io.Serializable;
import java.util.ArrayList;
import telran.employees.*;
import telran.net.*;


public class CompanyProtocol implements Protocol {
	private final Company company = new CompanyImpl();
	ResponseCode ok = ResponseCode.OK;

	@Override
	public Response getResponse(Request request) {
		Serializable data = request.data;
		return switch (request.type) {		
			case "getEmployee" -> new Response(ok, company.getEmployee((long) data));
			case "addEmployee" -> new Response(ok, company.addEmployee((Employee) data));
			case "removeEmployee" -> new Response(ok, company.removeEmployee((long) data));
			
			case "getAllEmployees" -> new Response(ok, new ArrayList<>(company.getAllEmployees()));
			case "getEmployeesByMonthBirth" -> new Response(ok, new ArrayList<>(company.getEmployeesByMonthBirth((int) data)));			
			case "getEmployeesByDepartment" -> new Response(ok, new ArrayList<>(company.getEmployeesByDepartment(data.toString())));
			
			case "getEmployeesBySalary" -> getEmployeesBySalary(data);
			case "save" -> save(data);
			case "restore" -> restore(data);
			default -> new Response(ResponseCode.WRONG_REQUEST, request.type + " wrong request");
		};
	}

	private Response restore(Serializable data) {
		company.restore(data.toString());
		return new Response(ok, null);
	}

	private Response save(Serializable data) {
		company.save(data.toString());
		return new Response(ok, null);
	}	

	private Response getEmployeesBySalary(Serializable data) {
		int[] salaries = (int[]) data;
		return new Response(ok, new ArrayList<>(company.getEmployeesBySalary(salaries[0], salaries[1])));
	}
	
}