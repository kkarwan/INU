package LicznikKlikniec;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

	Button btnAdd, btnSub;
	Label lblCounter;
	private int intCounter;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		btnAdd = new Button("Add");
		btnSub = new Button("Sub");
		lblCounter = new Label("0");
		btnAdd.setOnAction(e -> btnAdd_Click());
		btnSub.setOnAction(e -> btnSub_Click());
		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL, 10, 10, btnAdd, btnSub, lblCounter);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setPadding(new Insets(20));
		Scene scene = new Scene(flowPane);	
		primaryStage.setScene(scene);
		primaryStage.setTitle("Klikacz");
		primaryStage.centerOnScreen();
		primaryStage.show();	
	}

	private void btnSub_Click() {
		lblCounter.setText(Integer.toString(--intCounter));
	}

	private void btnAdd_Click() {
		lblCounter.setText(Integer.toString(++intCounter));
	}
}