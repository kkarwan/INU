package Strumienie;

import java.util.stream.Stream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application{

	TextField txtField;
	Button btnOK;
	Label lblSilnia, lblWynik;
	Stage primaryStage;
	String wynik;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		btnOK = new Button("Oblicz");
		txtField = new TextField();
		lblWynik = new Label("");
		lblSilnia = new Label("Wylicz Silnię! Podaj wartość");
		
		
		btnOK.setOnAction(e -> btnOK_Click());
		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL,10,10,lblSilnia, txtField,btnOK, lblWynik);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setPadding(new Insets(20));
		Scene scene = new Scene(flowPane);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	private void btnOK_Click() {
		
		wynik = Stream.iterate(1, x -> x+1).limit(Integer.parseInt(txtField.getText())).reduce(1, (x,y) -> x*y).toString();
		lblWynik.setText(wynik);
	}

}
