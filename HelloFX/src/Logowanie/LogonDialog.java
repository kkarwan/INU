package Logowanie;

import java.util.Optional;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class LogonDialog extends Application {

	private Dialog<Pair<Environment, String>> dialog;
	private ChoiceBox<Environment> cbxEnv;
	private ComboBox<User> cbxUsers;
	private PasswordField passField;
	private GridPane grid;
	private Label lblEnv, lblUsers, lblPassField;


	// koniec definicji

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		dialog = new Dialog<>();
		grid = new GridPane();

		UsersManager managerUsers = new UsersManager();

		ButtonType btnOK = new ButtonType("Logon", ButtonData.OK_DONE);
		ButtonType btnCN = new ButtonType("Anuluj", ButtonData.CANCEL_CLOSE);

		lblEnv = new Label("Środowisko:");
		lblUsers = new Label("Użytkownik:");
		lblPassField = new Label("Hasło:");

		cbxEnv = new ChoiceBox<Environment>(FXCollections.observableArrayList(Environment.values()));
		cbxUsers = new ComboBox<User>(FXCollections.observableArrayList(managerUsers.getArrayusersNull()));
		passField = new PasswordField();

		cbxUsers.setPromptText("Wybierz użytkownika ...");
		passField.setPromptText("Podaj hasło ...");

		cbxUsers.setDisable(true);
		passField.setDisable(true);

		dialog.getDialogPane().setContent(grid);
		dialog.getDialogPane().getButtonTypes().addAll(btnOK, btnCN);
		dialog.setGraphic(new ImageView(this.getClass().getResource("Login_64x.png").toString()));
		dialog.setTitle("Logowanie");
		dialog.setHeaderText("Logowanie do systemu STYLEman");

		Node btnOKNode = dialog.getDialogPane().lookupButton(btnOK);
		btnOKNode.setDisable(true);

		// obserwatorzy
		cbxEnv.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

				// wyczyszczenie pola User przy zmianie srodowiska
				cbxUsers.setValue(null);
				Integer intWartosc = null;

				// zmiana listy kont w zaleznosci od wybory srodowiska
				if ((Integer) arg2 == 0) {
					cbxUsers.setDisable(false);
					intWartosc = 0;
					cbxUsers.getItems().clear();
					cbxUsers.getItems().addAll(managerUsers.getArrayUsersProd());
				} else if ((Integer) arg2 == 1) {
					cbxUsers.setDisable(false);
					intWartosc = 1;
					cbxUsers.getItems().clear();
					cbxUsers.getItems().addAll(managerUsers.getArrayUsersTest());
				} else if ((Integer) arg2 == 2) {
					cbxUsers.setDisable(false);
					intWartosc = 2;
					cbxUsers.getItems().clear();
					cbxUsers.getItems().addAll(managerUsers.getArrayUsersDeve());
				}
			}
		});


		cbxUsers.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub

				String user = cbxUsers.getSelectionModel().getSelectedItem().toString();
					
				if (!user.isEmpty()) {
					passField.setDisable(false);
				} else {
					passField.setDisable(true);
				}
			}
		});
		
		passField.textProperty().addListener((observable, oldValue, newValue) -> {
			btnOKNode.setDisable(newValue.trim().isEmpty());
		});

		// ustawienie szerokosci
		cbxEnv.setPrefWidth(200);
		cbxUsers.setPrefWidth(200);
		cbxUsers.setEditable(true);
		passField.setPrefWidth(200);

		grid.addRow(0, lblEnv, cbxEnv);
		grid.addRow(1, lblUsers, cbxUsers);
		grid.addRow(2, lblPassField, passField);
		grid.setHgap(30);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 50, 20, 50));


		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == btnOK) {
				return new Pair<>(cbxEnv.getValue(), passField.getText());
			}
			return null;
		});

		
			Optional<Pair<Environment, String>> result = dialog.showAndWait();
			result.ifPresent(passField -> {
				System.out.println("Użytkownik = " + cbxUsers.getValue() + ", Hasło = " + passField.getValue());
			});
	}

	@Override
	public void stop() throws Exception {

	}

}