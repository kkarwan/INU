package Brawo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	Button btnOK;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		btnOK = new Button();
		btnOK.setText("Kliknij!");
		btnOK.setOnAction(e -> btnOK_Click());
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(btnOK);
		Scene scene = new Scene(borderPane,400,300);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Klikacz");
		primaryStage.centerOnScreen();
		primaryStage.show();
		
		
	}

	private void btnOK_Click() {
		if (btnOK.getText().equals("Kliknij!"))
			btnOK.setText("Brawo!");
		else
			btnOK.setText("Kliknij!");
	}

}
