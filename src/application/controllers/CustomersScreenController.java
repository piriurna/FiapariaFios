package application.controllers;

import java.util.List;

import com.jfoenix.controls.JFXButton;

import application.models.Customer;
import application.models.DAO.CustomersDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CustomersScreenController {

	@FXML
	private ListView<Customer> customers;
	
	@FXML
	private TextField name, cellphone, email, streetName, addressNumber, cityName, cpf, accountNumber;
	
	@FXML
	private VBox sendBox;
	
	@FXML
	private ComboBox<String> state;
	
	@FXML
	private JFXButton saveButton;
	
	private final int ADD_USER_ID = 99999;
	
	private ObservableList<Customer> sortedCustomers = CustomersDAO.getAllCustomer().sorted();
	
	@FXML
	private void initialize() {
		populateList();
		
		customers.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal == null) return;
			
			System.out.println(newVal.getId());
			if(newVal.getId() == ADD_USER_ID) {
				clearFields();
			}else
				fillFields(newVal);
		});
		state.setItems(FXCollections.observableArrayList("AC", "AL", "AM", "AP", "BA", "CE",
				"DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RS", "SC", "SE", "SP", "TO"));
	}

	private void populateList() {
		customers.getItems().add(new Customer(ADD_USER_ID, "+ Adicionar Novo Cliente", null, null, null, null, null));
		customers.getItems().addAll(sortedCustomers);
	}
	
	@FXML
	private void save() {
		Customer customer = customers.getSelectionModel().getSelectedItem();
		updateCustomerInfos(customer);
		if(customer.getId() == ADD_USER_ID) {
			if(CustomersDAO.createNewCustomer(customer) > 0)
				sendBox.getChildren().add(new Label("Cliente Adicionado com Sucesso"));
		}else {
			if(CustomersDAO.updateCustomer(customer) > 0)
				sendBox.getChildren().add(new Label("Cliente Atualizado com Sucesso"));
		}
		customers.getItems().clear();
		populateList();
	}
	
	private void clearFields() {
		name.clear();
		cellphone.clear();
		email.clear();
		state.setStyle("-fx-prompt-text-fill: rgba(39, 63, 98, 0.5)");
		state.setValue(null);
		state.setPromptText("Estado");
		streetName.clear();
		addressNumber.clear();
		cityName.clear();
		cpf.clear();
		accountNumber.clear();
	}
	
	private void updateCustomerInfos(Customer customer) {
		customer.setName(name.getText());
		customer.setCellphone(cellphone.getText());
		customer.setEmail(email.getText());
		customer.setAddress(streetName.getText() + ", " + addressNumber.getText() + ", " + cityName.getText() + ", " + state.getValue());
		customer.setCpf(cpf.getText());
		customer.setAccountNumber(accountNumber.getText());
	}

	private void fillFields(Customer customer) {
		String[] addressState = null;
		System.out.println(customer.getAddress());
		name.setText(customer.toString());
		if(customer.getCellphone() != null)
			cellphone.setText(customer.getCellphone());
		if(customer.getEmail() != null)
			email.setText(customer.getEmail());
		if(customer.getAddress() != null && !customer.getAddress().contains("null")) { 
			addressState = customer.getAddress().split(",");
			for(int i = 0; i< addressState.length; i++) {
				addressState[i] = addressState[i].trim();
			}
			if(addressState.length == 4) {
				streetName.setText(addressState[0]);
				addressNumber.setText(addressState[1]);
				cityName.setText(addressState[2]);
				state.setValue(addressState[3]);
			}
		}			
		if(customer.getCpf() != null)
			cpf.setText(customer.getCpf());
		if(customer.getAccountNumber() != null)
			accountNumber.setText(customer.getAccountNumber());
	}
}
