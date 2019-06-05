package calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalcController2 {
	private String lastOperation = "";
	private String lastAction = "";
	private Button button = new Button();
	private Double wynik = null;
	private Double number2 = null;
	private String zero;
	private CalcModel model = new CalcModel();

	private Double number1 = 0.0;
	private String operator = "";
	private boolean start = true;

	@FXML
	void initialize() {
		setDivideByZero("Divide by 0");
		output.setText("0");
	}

	@FXML
	private Label output;

	@FXML
	private void operators(ActionEvent event) {
		String value = ((Button) event.getSource()).getText();
		if (!"=".equals(value)) {
			if (!operator.isEmpty())
				return;
			operator = value;
			number1 = Double.parseDouble(output.getText());
			output.setText("");

		} else {
			if (operator.isEmpty())
				return;
			output.setText(String.valueOf(model.calculate(number1, Double.parseDouble(output.getText()), operator)));
			operator = "";
			start = true;
		}
	}

	@FXML
	private void digit(ActionEvent event) {

		if (start) {
			output.setText("");
			start = false;
		}
		String value = ((Button) event.getSource()).getText();
		if (output.getText().equals("0")) {
			output.setText(value);
		} else {
			output.setText(output.getText() + value);
		}

//		if (output.getText().equals(getDivideByZero())) {
//			return;
//		}
//
//		if (output.getText().equals("0")) {
//			output.setText(value);
//		} else if (lastAction.equals("") || lastAction.equals("operation")) {
//			output.setText(output.getText() + value);
//		} else if (lastAction.equals("rowna")) {
//			defaultParameters();
//			output.setText(output.getText() + value);
//		} else {
//			output.setText(output.getText() + value);
//		}
//		lastAction = "digit";
	}

	@FXML
	private void dot(ActionEvent event) {
		button = (Button) event.getSource();

		if (output.getText().contains(".") || output.getText().equals(getDivideByZero())) {
			return;
		} else if (output.equals("") || output.equals("operation") || output.equals("equel")) {
			output.setText("0");
		}

		output.setText(output.getText() + button.getText());
		lastAction = "dot";

	}

	@FXML
	private void specialModulo(ActionEvent event) {
		output.setText("Modulo");
	}

	@FXML
	private void specialAC(ActionEvent event) {
		defaultParameters();
		output.setText("0");
	}

	@FXML
	private void specialNegative(ActionEvent event) {
		if (output.getText().equals(getDivideByZero()) || output.getText().equals("0")) {
			return;
		}

		output.setText(String.valueOf(model.negative(Double.parseDouble(output.getText()))));
//		Double negative = Double.parseDouble(output.getText()) * (-1);
//		output.setText(negative.toString());
	}

	private void defaultParameters() {
		lastOperation = "";
		lastAction = "";
		wynik = null;
		number1 = null;
		number2 = null;
	}

	private String getDivideByZero() {
		return zero;
	}

	private void setDivideByZero(String zero) {
		this.zero = zero;
		output.setText(zero);
	}

}
