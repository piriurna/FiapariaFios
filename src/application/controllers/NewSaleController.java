package application.controllers;

import application.models.Customer;
import application.models.Item;
import application.models.Sale;
import application.models.DAO.CustomersDAO;
import application.models.DAO.ItemsDAO;
import application.models.DAO.SalesDAO;
import javafx.collections.transformation.FilteredList;
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
		FilteredList<Customer> customersFiltered = new FilteredList<>(CustomersDAO.getAllCustomer(), p->true);
		customers.setItems(customersFiltered);
		products.setItems(ItemsDAO.getAllItems());
		customers.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            customersFiltered.setPredicate(customer -> {
                // If filter text is empty, display all persons.
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newVal.toLowerCase();
                
                if (customer.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
                });
            System.out.println("Numero de pessoas: " + customers.getItems().size());
            if(customers.getItems().size() > 0)
            	customers.show();
            else
            	customers.hide();
		});
	}
	
	@FXML
	private void registerSale() {
		Customer customer = null;
		if(customers.getItems().size() < 1) {
			customer = new Customer(999999, customers.getEditor().getText(), null, null, null, null, null);
			CustomersDAO.createNewCustomer(customer);
			customer = CustomersDAO.getCustomer(customer.getName());
		}else {
			customer = customers.getValue();
		}
		Item item = products.getValue();
		SalesDAO.registerNewSale(new Sale(0, saleDate.getValue(), customer, item));
		products.getScene().getWindow().hide();
	}
}
