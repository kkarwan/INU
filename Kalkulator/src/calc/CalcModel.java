package calc;

public class CalcModel {

	public double calculate(double number1, double number2, String operator) {

		switch (operator) {
		case "+":
			return number1 + number2;
		case "-":
			return number1 - number2;
		case "*":
			return number1 * number2;
		case "/": {
			if (number2 == 0)
				return 0;
			return number1 / number2;
		}
		case "%": return number1 % number2;
		}
		System.out.println("Nieznany operator: " + operator);
		return 0;
	}

	public double negative(double value) {
		return value * (-1);
	}

}