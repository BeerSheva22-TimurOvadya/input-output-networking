package telran.view.orgonizerMyHW;

import telran.view.*;

public class ViewOrganizer {

	public static void main(String[] args) {
		InputOutput io = new StandardInputOutput();
		Menu menu = new Menu("ORGANIZER \n"+ " ".repeat(15) + "ver.1.0", buildMainMenu());
		menu.perform(io);
	}

	private static Item[] buildMainMenu() {
		Item items[] = { 
				Calculator.buildCalculatorMenu("Calculator Operations"),
				Calendar.buildCalendarMenu("Calendar Operations"), 
				Item.exit() };
		return items;
	}

}