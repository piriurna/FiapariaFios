package application.controllers;

import application.models.Sale;
import application.models.DAO.SalesDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class SalesScreenController {

	@FXML
	private TableView<Sale> sales;
	
	@FXML
	private TableColumn<Sale, String> saleId, customerId, customerName, productId, productName, saleDate;
	
	@FXML
	private void initialize() {
		setValueFactories();
		sales.setItems(SalesDAO.getAllSales());
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
}
