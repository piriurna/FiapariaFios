package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ScreenManager {
	
	private static Stage primaryStage;

	public static void createNewWindow(Stage primaryStage, String fxmlLocation, Object controller, String styleSheetPath) {
		try {
			setPrimaryStage(primaryStage);
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlLocation));
			loader.setController(controller);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Main.class.getResource(styleSheetPath).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createNewWindow(Stage primaryStage, String fxmlLocation, Object controller) {
		try {
			setPrimaryStage(primaryStage);
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlLocation));
			loader.setController(controller);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Main.class.getResource("/" + "application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createNewWindow(String fxmlLocation, Object controller) {
		try {
			Stage stage = new Stage();
			Parent root = loadFXML(fxmlLocation, controller);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Main.class.getResource("/" + "application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createNewWindow(String fxmlLocation, Object controller, String styleSheetPath) {
		try {
			Stage stage = new Stage();
			Pane root = loadFXML(fxmlLocation, controller);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Main.class.getResource("/" + styleSheetPath).toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Pane loadFXML(String fxmlLocation, Object controller) {
		Pane root = null;
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlLocation));
			loader.setController(controller);
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
	
	public static void createNewWindowModal(String fxmlLocation, Object controller, String stageName) {
		try {
			Stage stage = new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("/logo.png")));
			stage.setTitle(stageName);
			Pane root = loadFXML(fxmlLocation, controller);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Main.class.getResource("/" + "application.css").toExternalForm());
			stage.setScene(scene);
			stage.initOwner(primaryStage);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void setPrimaryStage(Stage stage) {
		primaryStage = stage;
	}
}
