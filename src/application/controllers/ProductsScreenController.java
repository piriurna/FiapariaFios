package application.controllers;

import com.jfoenix.controls.JFXButton;

import application.models.Collection;
import application.models.Item;
import application.models.ItemType;
import application.models.Size;
import application.models.DAO.CollectionDAO;
import application.models.DAO.ItemTypesDAO;
import application.models.DAO.ItemsDAO;
import application.models.DAO.SizeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProductsScreenController {

	@FXML
	private ListView<Item> products;
	
	@FXML
	private TextField name, price;
	
	@FXML
	private ComboBox<Size> size;
	
	@FXML
	private ComboBox<ItemType> itemType;
	
	@FXML
	private ColorPicker color;
	
	@FXML
	private VBox sendBox;
	
	@FXML
	private ComboBox<Collection> collection;
	
	@FXML
	private JFXButton saveButton;
	
	private final int ADD_USER_ID = 99999;
	
	@FXML
	private void initialize() {
		
		populateList();
		size.setItems(SizeDAO.getAllSizes());
		collection.setItems(CollectionDAO.getAllCollections());
		itemType.setItems(ItemTypesDAO.getAllItemTypes());
		products.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal == null) return;
			if(newVal.getId() == ADD_USER_ID) {
				clearFields();
			}else
				fillFields(newVal);
		});
	}

	private void populateList() {
		products.getItems().clear();
		products.getItems().add(new Item(ADD_USER_ID, "+ Adicionar novo Produto", null, ADD_USER_ID, null, null, null, ADD_USER_ID));
		products.getItems().addAll(ItemsDAO.getAllItems());
	}
	
	@FXML
	private void save() {
		Item item = products.getSelectionModel().getSelectedItem();
		updateItemInfos(item);
		if(item.getId() == ADD_USER_ID) {
			if(ItemsDAO.createNewItem(item) > 0)
				sendBox.getChildren().add(new Label("Produto Adicionado com Sucesso"));
		}else {
			ItemsDAO.updateItem(item);
		}
		populateList();
		clearFields();
	}
	
	private void clearFields() {
		name.clear();
		price.clear();
		size.setValue(null);
		size.setEditable(true);
		size.setPromptText("Selecione o Tamanho");
		itemType.setValue(null);
		itemType.setEditable(true);
		itemType.setPromptText("Selecione o Tipo de Produto");
		color.setValue(null);
		collection.setValue(null);
		collection.setEditable(true);
		collection.setPromptText("Selecione a Coleção");
	}
	
	private void updateItemInfos(Item item) {
		item.setName(name.getText());
		item.setPrice(Double.parseDouble(price.getText()));
		size.setEditable(false);
		size.setValue(products.getSelectionModel().getSelectedItem().getSize());
		item.setSize(SizeDAO.getSize(size.getValue().toString()));
		itemType.setEditable(false);
		itemType.setValue(products.getSelectionModel().getSelectedItem().getItemType());
		item.setItemType(itemType.getValue());
		item.setColor(color.getValue());
		collection.setEditable(false);
		collection.setValue(products.getSelectionModel().getSelectedItem().getColection());
		item.setColection(collection.getValue());
	}

	private void fillFields(Item item) {
		name.setText(item.toString());
		price.setText(String.valueOf(item.getPrice()));
		size.setValue(item.getSize());
		itemType.setValue(item.getItemType());
		color.setValue(item.getColor());
		collection.setValue(item.getColection());
	}
}
