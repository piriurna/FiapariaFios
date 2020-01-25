package application.controllers;

import application.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainMenuController {

	@FXML
	private Tab products, customers;
	
	@FXML
	private TabPane mainScreen;
	
	@FXML
	private void initialize() {
		products.setContent(ScreenManager.loadFXML("views/ProductsScreen.fxml", new ProductsScreenController()));
	}
}
