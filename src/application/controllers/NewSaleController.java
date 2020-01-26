package application.controllers;

import application.models.Customer;
import application.models.Item;
import application.models.Sale;
import application.models.DAO.CustomersDAO;
import application.models.DAO.ItemsDAO;
import application.models.DAO.SalesDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class NewSaleController {

	@FXML
	private ComboBox<Customer> customers;
	
	@FXML
	private ComboBox<Item> products;
	
	@FXML
	private DatePicker saleDate;
	
	@FXML
	private void initialize() {
		customers.setItems(CustomersDAO.getAllCustomer());
		products.setItems(ItemsDAO.getAllItems());
	}
	
	@FXML
	private void registerSale() {
		Customer customer = customers.getValue();
		Item item = products.getValue();
		SalesDAO.registerNewSale(new Sale(0, saleDate.getValue(), customer, item));
		products.getScene().getWindow().hide();
	}
}
