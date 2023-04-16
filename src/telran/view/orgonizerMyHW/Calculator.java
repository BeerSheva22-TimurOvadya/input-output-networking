package telran.view.orgonizerMyHW;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class Calculator {
	public static Item buildCalculatorMenu(String name) {
		return Item.of(name, io -> {
			Item[] items = { 
					Item.of("Sum of numbers", Calculator::SumOfNumbers),
					Item.of("Subtract of numbers", Calculator::SubtractOfNumbers),
					Item.of("Multiply of numbers", Calculator::MultiplyOfNumbers), 
					Item.of("Divide 0f numbers", Calculator::DivideOfNumbers),					
					Item.exit() };
			Menu menu = new Menu(name, items);
			menu.perform(io);
		});

	}
	static void SumOfNumbers(InputOutput io) {
		int[] numbers = enteringNumbers(io);
		io.writeLine(numbers[0] + numbers[1]);
		
	}
	
	static int[] enteringNumbers(InputOutput io) {
		int firstNumber = io.readInt("Please, enter first number", "Not a number");
		int secondNumber = io.readInt("Please, enter second number", "Not a number");
		return new int[] { firstNumber, secondNumber };
	}

	static void SubtractOfNumbers(InputOutput io) {
		int[] numbers = enteringNumbers(io);
		io.writeLine(numbers[0] - numbers[1]);
	}

	static void MultiplyOfNumbers(InputOutput io) {
		int[] numbers = enteringNumbers(io);
		io.writeLine(numbers[0] * numbers[1]);
	}
	
	static void DivideOfNumbers(InputOutput io) {
		int[] numbers = enteringNumbers(io);
		io.writeLine(numbers[0] / numbers[1]);
	}	
}