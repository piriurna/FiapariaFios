package application;
	
import application.controllers.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		ScreenManager.createNewWindow(primaryStage, "views/MainMenu.fxml", new MainMenuController());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
