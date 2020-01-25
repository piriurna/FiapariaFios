package application.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import application.models.Collection;
import application.models.Item;
import application.models.ItemType;
import application.models.Size;
import application.models.DAO.ItemsDAO;
import application.models.DAO.SizeDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ProductsScreenController {

	@FXML
	private ListView<Item> products;
	
	@FXML
	private TextField name, price;
	
	@FXML
	private JFXComboBox<Size> size;
	
	@FXML
	private JFXComboBox<ItemType> itemType;
	
	@FXML
	private ColorPicker color;
	
	@FXML
	private HBox sendBox;
	
	@FXML
	private ComboBox<Collection> collection;
	
	@FXML
	private JFXButton saveButton;
	
	private final int ADD_USER_ID = 99999;
	
	@FXML
	private void initialize() {
		products.getItems().add(new Item(ADD_USER_ID, "+ Adicionar novo Produto", null, ADD_USER_ID, null, null, null, ADD_USER_ID));
		products.getItems().addAll(ItemsDAO.getAllItems());
		size.setItems(SizeDAO.getAllSizes());
		products.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal.getId() == ADD_USER_ID) {
				clearFields();
			}else
				fillFields(newVal);
		});
	}
	
	@FXML
	private void save() {
		Item item = products.getSelectionModel().getSelectedItem();
		updateItemInfos(item);
		if(item.getId() == ADD_USER_ID) {
			if(ItemsDAO.createNewItem(item) > 0)
				sendBox.getChildren().add(new Label("Produto Adicionado com Sucesso"));
		}else
			ItemsDAO.updateItem(item);
	}
	
	private void clearFields() {
		name.clear();
		price.clear();
		size.setValue(null);
		itemType.setValue(null);
		color.setValue(null);
		collection.setValue(null);
	}
	
	private void updateItemInfos(Item item) {
		item.setName(name.getText());
		item.setPrice(Double.parseDouble(price.getText()));
		item.setSize(SizeDAO.getSize(size.getValue().toString()));
		item.setItemType(itemType.getValue());
		item.setColor(color.getValue());
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
