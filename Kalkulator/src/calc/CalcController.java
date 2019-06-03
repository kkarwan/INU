package calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalcController {
	private String lastOperation = "";
	private String lastAction = "";
	private Button button = new Button();
	private Double wynik = null;
	private Double arg1 = null;
	private Double arg2 = null;
	private String zero;

	private void defaultParameters() {
		lastOperation = "";
		lastAction = "";
		wynik = null;
		arg1 = null;
		arg2 = null;
	}

	private String getDivideByZero() {
		return zero;
	}

	private void setDivideByZero(String zero) {
		this.zero = zero;
		result.setText(zero);
	}

	@FXML
	void initialize() {
		setDivideByZero("Divide by 0");
		result.setText("0");
	}

	@FXML
	private Label result;

	@FXML
	private void digit(ActionEvent event) {

		String value = ((Button) event.getSource()).getText();

		if (result.getText().equals(getDivideByZero())) {
			return;
		}

		if (result.getText().equals("0")) {
			result.setText(value);
		} else if (lastAction.equals("") || lastAction.equals("operation")) {
			result.setText(result.getText() + value);
		} else if (lastAction.equals("rowna")) {
			wynik = null;
			arg1 = null;
			arg2 = null;
			lastOperation = "";
			result.setText(result.getText() + value);
		} else {
			result.setText(result.getText() + value);
		}
		lastAction = "digit";
	}

	@FXML
	private void dot(ActionEvent event) {
		button = (Button) event.getSource();

		if (result.getText().contains(".") || result.getText().equals(getDivideByZero())) {
			return;
		} else if (result.equals("") || result.equals("operation") || result.equals("equel")) {
			result.setText("0");
		}

		result.setText(result.getText() + button.getText());
		lastAction = "dot";

	}

	@FXML
	private void special(ActionEvent event) {
		System.out.println("special");
	}

	@FXML
	private void specialAC(ActionEvent event) {
		defaultParameters();
		result.setText("0");
	}

	@FXML
	private void specialNegative(ActionEvent event) {
		if (result.getText().equals(getDivideByZero()) || result.getText().equals("0")) {
			return;
		}
		Double negative = Double.parseDouble(result.getText()) * (-1);
		result.setText(negative.toString());
	}

}
