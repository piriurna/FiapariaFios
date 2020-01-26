package application.controllers;

import application.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainMenuController {

	@FXML
	private Tab products, customers, sales;
	
	@FXML
	private TabPane mainScreen;
	
	@FXML
	private void initialize() {
		products.setContent(ScreenManager.loadFXML("views/ProductsScreen.fxml", new ProductsScreenController()));
		customers.setContent(ScreenManager.loadFXML("views/CustomersScreen.fxml", new CustomersScreenController()));
		sales.setContent(ScreenManager.loadFXML("views/SalesScreen.fxml", new SalesScreenController()));
	}
}
