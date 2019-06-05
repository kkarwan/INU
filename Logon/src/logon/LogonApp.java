package logon;

import javafx.application.Application;
import javafx.stage.Stage;

public class LogonApp extends Application{

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LogonDialog logonDialog = new LogonDialog();
		
	}
}
