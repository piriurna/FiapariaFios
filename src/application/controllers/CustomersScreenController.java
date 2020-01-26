package application.controllers;

import com.jfoenix.controls.JFXButton;

import application.models.Customer;
import application.models.DAO.CustomersDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CustomersScreenController {

	@FXML
	private ListView<Customer> customers;
	
	@FXML
	private TextField name, cellphone, email, address, cpf, accountNumber;
	
	@FXML
	private HBox sendBox;
	
	@FXML
	private ComboBox<String> state;
	
	@FXML
	private JFXButton saveButton;
	
	private final int ADD_USER_ID = 99999;
	
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
		customers.getItems().addAll(CustomersDAO.getAllCustomer());
	}
	
	@FXML
	private void save() {
		Customer customer = customers.getSelectionModel().getSelectedItem();
		updateCustomerInfos(customer);
		if(customer.getId() == ADD_USER_ID) {
			if(CustomersDAO.createNewCustomer(customer) > 0)
				sendBox.getChildren().add(new Label("Produto Adicionado com Sucesso"));
		}else {
			CustomersDAO.updateCustomer(customer);
		}
		customers.getItems().clear();
		populateList();
	}
	
	private void clearFields() {
		name.clear();
		cellphone.clear();
		email.clear();
		state.setStyle("-fx-prompt-text-fill: rgba(39, 63, 98, 0.5)");
		state.setPromptText("Estado");
		address.clear();
		cpf.clear();
		accountNumber.clear();
	}
	
	private void updateCustomerInfos(Customer customer) {
		customer.setName(name.getText());
		customer.setCellphone(cellphone.getText());
		customer.setEmail(email.getText());
		customer.setAddress(address.getText() + "," + state.getValue());
		customer.setCpf(cpf.getText());
		customer.setAccountNumber(accountNumber.getText());
	}

	private void fillFields(Customer customer) {
		name.setText(customer.toString());
		cellphone.setText(customer.getCellphone());
		email.setText(customer.getEmail());
		String[] addressState = customer.getAddress().split(",");
		address.setText(addressState[0] + "," + addressState[1]);
		state.setStyle("-fx-prompt-text-fill: rgba(39, 63, 98, 1)");
		state.setPromptText(addressState[2]);
		cpf.setText(customer.getCpf());
		accountNumber.setText(customer.getAccountNumber());
	}
}
