package calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalcController2 {
	private Button button = new Button();
	private CalcModel model = new CalcModel();
	private Double number1 = null;
	private String operator = "";
	private boolean start = true;

	@FXML
	void initialize() {
		output.setText("0");
	}

	@FXML
	private Label output;

	@FXML
	private void digit(ActionEvent event) {

		if (start) {
			start = false;
		}
		String value = ((Button) event.getSource()).getText();
		if (output.getText().equals("0")) {
			output.setText(value);
		} else {
			output.setText(output.getText() + value);
		}
	}

	@FXML
	private void operators(ActionEvent event) {
		String button = ((Button) event.getSource()).getText();
		if (!"=".equals(button)) {
			if (!operator.isEmpty()) {
				operator=button;
				return;
			}
			operator = button;
			number1 = Double.parseDouble(output.getText());
			output.setText("");

		} else {
			if (operator.isEmpty())
				return;
			if (output.getText().equals("")) output.setText(number1.toString());			
			String wynik = String.valueOf(model.calculate(number1, Double.parseDouble(output.getText()), operator));
			float w = Float.parseFloat(wynik);
			output.setText(String.valueOf(w));
			operator = "";
			start = true;
		}
	}

	@FXML
	private void dot(ActionEvent event) {
		button = (Button) event.getSource();

		if (output.getText().contains(".") || output.getText().equals("0.")) {
			return;
		} else if (output.getText().equals("")) {
			output.setText("0.");
		}else {
			output.setText(output.getText() + button.getText());
		}
	}

	@FXML
	private void specialAC(ActionEvent event) {
		defaultParameters();
	}

	@FXML
	private void specialNegative(ActionEvent event) {
		if (output.getText().equals("0")) {
			return;
		}
		output.setText(String.valueOf(model.negative(Double.parseDouble(output.getText()))));
	}

	private void defaultParameters() {
		number1 = null;
		output.setText("0");
	}

}
