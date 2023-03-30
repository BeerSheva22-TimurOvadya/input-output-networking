package telran.view;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;

class InputOutputTest {
	InputOutput io = new StandardInputOutput();

	@Test
	@Disabled
	void readObjectTest() {
		Integer[] array = io.readObject("Enter numbers separated by space", "no number ", s -> {

			String strNumbers[] = s.split(" ");
			return Arrays.stream(strNumbers).map(str -> Integer.parseInt(str)).toArray(Integer[]::new);

		});
		io.writeLine(Arrays.stream(array).collect(Collectors.summarizingInt(x -> x)));

	}

	@Test
	@Disabled
	void readIntMinMax() {
		Integer res = io.readInt("Enter any number in range [1, 40]", "no number ", 1, 40);
		io.writeLine(res);
	}

	@Test
	@Disabled
	void readOptionTest() {
		Set<String> departments = new HashSet<String>();
		departments.add("QA");
		departments.add("Management");
		departments.add("Development");
		
		String department = io.readStringOptions("Enter department from " + departments, "Wrong department", departments);
		assertTrue(departments.contains(department));
	}
	@Test
	@Disabled
	void readPredicateTest() {
		String ipAddress = io.readStringPredicate("Enter IP address", "Wrong IP addres", s -> 
		s.matches(ipV4Regex()));
		assertTrue(ipAddress.matches(ipV4Regex()));
	}
	

	 String ipOctetRegex() {		
		return "\\d\\d?|[0,1]\\d\\d|2[0-4]\\d|25[0-5]";
	}

	 String ipV4Regex() {

		return String.format("((%1$s)\\.){3}(%1$s)", ipOctetRegex());
	}
	 @Test
	 @Disabled
	 void readDateISOTest() {
		 LocalDate dateAs = io.readDateISO("Enter any date YYYY-MM-DD",
				 "no date in ISO format");
		 io.writeLine(dateAs + " has been entered");
		 
		 
	 }
	 @Test
	 @Disabled
	 void readDateTest() {
		 LocalDate date = io.readDateISO("Input date", "Cannot be parsed as date in ISO format (yyyy-mm-dd)");
			io.writeLine(date);
			LocalDate d1 = LocalDate.of(1999, 1, 1);
			LocalDate d2 = LocalDate.now();
			LocalDate date1 = io.readDate("Input date in range " +d1+" .. "+ d2 +" in format d-MM-yyyy", "Cannot be parsed as date or not in scope", "d-MM-yyyy", d1, d2 );
			io.writeLine(date1);		 
	 }
	 
	 @Test
//		@Disabled
		void testReadLong() {
			long l2 = Long.MAX_VALUE;
			long l1 = 0;
			long i = io.readLong("Input long in range "+ l1+ " .. "+ l2, "Cannot be parsed as long or not in the scope", l1, l2);
			io.writeLine(i);
		}

}