package telran.view.orgonizerMyHW;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import telran.view.*;

public class Calendar {
	static String format = "yyyy-MM-dd";

	public static Item buildCalendarMenu(String name) {
		return Item.of(name, io -> {
			Item items[] = { 
					Item.of("Date AFTER the specified number of days", Calendar::addDays),
					Item.of("Date BEFORE the specified number of days", Calendar::subtractDays), 
					Item.exit() };
			Menu menu = new Menu(name, new ArrayList<Item>(Arrays.asList(items)));
			menu.perform(io);
		});
	}
	
	static void addDays(InputOutput io) {
		dateOperations(io, false);
	}

	static void subtractDays(InputOutput io) {
		dateOperations(io, true);
	}
	
	static private void dateOperations(InputOutput io, boolean subtract) {
		LocalDate date = io.readDateISO("Please, enter date in format " + format, 
				"Wrong Date in format " + format);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		int days = io.readInt("Please, enter number of days", "Not a number");
		if (subtract) {
			io.writeLine(date.minusDays(days).format(dtf));
		} else {
			io.writeLine(date.plusDays(days).format(dtf));
		}
	}	
	
}