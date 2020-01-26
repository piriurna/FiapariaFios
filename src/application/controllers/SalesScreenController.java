package application.controllers;

import application.ScreenManager;
import application.models.Sale;
import application.models.DAO.SalesDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class SalesScreenController {

	@FXML
	private TableView<Sale> sales;
	
	@FXML
	private TextField filterField;
	
	private ObservableList<Sale> saleList = SalesDAO.getAllSales();
	
	@FXML
	private TableColumn<Sale, String> saleId, customerId, customerName, productId, productName, saleDate;
	
	@FXML
	private void initialize() {
		FilteredList<Sale> salesFiltered = new FilteredList<>(saleList, p -> true);
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            salesFiltered.setPredicate(sale -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (sale.getCustomer().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if(sale.getCustomer().getCpf().toLowerCase().contains(lowerCaseFilter)) {
                	return true;
                }else if(sale.getCustomer().getCellphone().toLowerCase().contains(lowerCaseFilter)) {
                	return true;
                }else if(sale.getItem().toString().toLowerCase().contains(lowerCaseFilter)) {
                	return true;
                }
                return false;
                });
            });
		setValueFactories();
		sales.setItems(salesFiltered);
	}

	private void setValueFactories() {
		saleId.setCellValueFactory(new Callback<CellDataFeatures<Sale, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Sale, String> p) {
		         return new ReadOnlyObjectWrapper<String>(String.valueOf(p.getValue().getId()));
		     }
		  });
		customerId.setCellValueFactory(new Callback<CellDataFeatures<Sale, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Sale, String> p) {
		         return new ReadOnlyObjectWrapper<String>(String.valueOf(p.getValue().getCustomer().getId()));
		     }
		  });
		customerName.setCellValueFactory(new Callback<CellDataFeatures<Sale, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Sale, String> p) {
		         return new ReadOnlyObjectWrapper<String>(p.getValue().getCustomer().getName());
		     }
		  });
		productId.setCellValueFactory(new Callback<CellDataFeatures<Sale, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Sale, String> p) {
		         return new ReadOnlyObjectWrapper<String>(String.valueOf(p.getValue().getItem().getId()));
		     }
		  });
		productName.setCellValueFactory(new Callback<CellDataFeatures<Sale, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Sale, String> p) {
		         return new ReadOnlyObjectWrapper<String>(String.valueOf(p.getValue().getItem().toString()));
		     }
		  });
		saleDate.setCellValueFactory(new Callback<CellDataFeatures<Sale, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Sale, String> p) {
		         return new ReadOnlyObjectWrapper<String>(String.valueOf(p.getValue().getSaleDate()));
		     }
		  });
	}
	
	@FXML
	private void registerSale() {
		ScreenManager.createNewWindowModal("views/NewSale.fxml", new NewSaleController(), "Registrar Nova Venda");
		sales.refresh();
	}
}
