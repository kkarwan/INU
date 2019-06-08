package logon;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class LogonDialog {

	private Dialog<Pair<Environment, String>> dialog;
	private ChoiceBox<Environment> cbxEnv;
	private ComboBox<String> cbxUsers;
	private PasswordField passField;
	private GridPane grid;
	private Label lblEnv = new Label("Środowisko:");
	private Label lblUsers = new Label("Użytkownik:");
	private Label lblPassField = new Label("Hasło:");
	private ButtonType btnOK = new ButtonType("Logon");
	private ButtonType btnCancel = new ButtonType("Anuluj");
	private final String naglowek = "Logowanie do systemu STYLEman";
	private UsersDatabase usersDatabase = new UsersDatabase();
	private Node btnOKNode;

	public LogonDialog() {

		dialog = new Dialog<>();
		dialog.setTitle("Logowanie");
		dialog.setHeaderText(naglowek);
		dialog.getDialogPane().getButtonTypes().addAll(btnOK, btnCancel);
		

		cbxEnv = new ChoiceBox<>(FXCollections.observableArrayList(Environment.values()));
		cbxEnv.valueProperty().addListener((observable, oldVal, newVal) -> cbxEnvChange(newVal));

		cbxUsers = new ComboBox<>();
		cbxUsers.valueProperty().addListener((observable, oldVal, newVal) -> cbxUsers(newVal));

		passField = new PasswordField();
		passField.setDisable(true);
		passField.textProperty()
				.addListener((observable, oldVal, newVal) -> btnOKNode.setDisable(newVal.trim().isEmpty()));

		btnOKNode = dialog.getDialogPane().lookupButton(btnOK);
		btnOKNode.setDisable(true);

		this.setIconHeader();
		this.setGridLayout();

		// ustawienie środowiska Produkcyjnego jako domyslne
		cbxEnv.setValue(Environment.Produkcyjne);

		Optional<Pair<Environment, String>> result = dialog.showAndWait();
		
		
		if (result.toString().contains("Logon")) {
			if (usersDatabase.checkPassword(cbxUsers.getValue().toString(), passField.getText())) {
				System.out.println("User: " + cbxUsers.getValue().toString() + " zalogowany");
			} else {
				System.out.println("Nieprawidłowe dane logowania");
			}
		}

	}
	// definicja sluchaczy
	private void cbxEnvChange(Environment value) {
		cbxUsers.getItems().clear();
		passField.clear();
		passField.setDisable(true);
		usersDatabase.defaultUsers(cbxEnv.getValue());
		for (String s : usersDatabase.getUsers().keySet()) {
			cbxUsers.getItems().add(s);
		}
	}

	private void cbxUsers(String value) {
		if (cbxUsers.getProperties().isEmpty()) {
			passField.setDisable(true);
		} else {
			passField.setDisable(false);
		}
	}

	private void setIconHeader() {
		dialog.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("img/Login_64x.png"))));
	}

	private void setGridLayout() {
		grid = new GridPane();
		cbxEnv.setPrefWidth(200);
		cbxUsers.setPrefWidth(200);
		cbxUsers.setEditable(true);
		passField.setPrefWidth(200);
		cbxUsers.setPromptText("Wybierz użytkownika ...");
		passField.setPromptText("Podaj hasło ...");
		grid.addRow(0, lblEnv, cbxEnv);
		grid.addRow(1, lblUsers, cbxUsers);
		grid.addRow(2, lblPassField, passField);
		grid.setHgap(30);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 50, 20, 50));
		dialog.getDialogPane().setContent(grid);
	}
}