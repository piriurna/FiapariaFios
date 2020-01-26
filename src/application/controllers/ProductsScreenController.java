package application.controllers;

import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;

import application.models.Collection;
import application.models.Item;
import application.models.ItemType;
import application.models.Size;
import application.models.DAO.CollectionDAO;
import application.models.DAO.ItemTypesDAO;
import application.models.DAO.ItemsDAO;
import application.models.DAO.SizeDAO;
import javafx.collections.transformation.FilteredList;
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
	
	private Item selectedItem;

	@FXML
	private void initialize() {
		populateListenAndFilterLists();
	}

	private void populateList() {
		products.getItems().clear();
		products.getItems().add(
				new Item(ADD_USER_ID, "+ Adicionar novo Produto", null, ADD_USER_ID, null, null, null, ADD_USER_ID));
		products.getItems().addAll(ItemsDAO.getAllItems().sorted());
	}

	@FXML
	private void save() {
		updateItemInfos(selectedItem);
		if (selectedItem.getId() == ADD_USER_ID) {
			if (ItemsDAO.createNewItem(selectedItem) > 0)
				sendBox.getChildren().add(new Label("Produto Adicionado com Sucesso"));
		} else {
			ItemsDAO.updateItem(selectedItem);
			sendBox.getChildren().add(new Label("Produto Atualizado com Sucesso"));
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
		size.hide();
		itemType.setValue(null);
		itemType.setEditable(true);
		itemType.setPromptText("Selecione o Tipo de Produto");
		itemType.hide();
		color.setValue(null);
		collection.setValue(null);
		collection.setEditable(true);
		collection.setPromptText("Selecione a Coleção");
		collection.hide();
	}

	private void populateListenAndFilterLists() {
		populateAndListenProducts();
		populateAndLitenSize();
		populateAndListenItemType();
		populateAndListenCollection();
	}

	private void populateAndListenProducts() {
		populateList();
		products.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal == null)	return;
			selectedItem = newVal;
			if (newVal.getId() == ADD_USER_ID) {
				clearFields();
			} else
				fillFields(newVal);
		});
	}

	private void populateAndListenCollection() {
		FilteredList<Collection> filteredCollections = new FilteredList<>(CollectionDAO.getAllCollections());
		collection.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
			filteredCollections.setPredicate(collection -> {
				// If filter text is empty, display all sizes.
				if (newVal == null || newVal.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newVal.toLowerCase();

				if (collection.toString().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
			if (collection.getItems().size() > 0 && !products.isFocused())
				collection.show();
			else
				collection.hide();
		});
		collection.setItems(filteredCollections);
	}

	private void populateAndListenItemType() {
		FilteredList<ItemType> filteredItemTypes = new FilteredList<>(ItemTypesDAO.getAllItemTypes());
		itemType.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
			filteredItemTypes.setPredicate(itemType -> {
				// If filter text is empty, display all sizes.
				if (newVal == null || newVal.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newVal.toLowerCase();

				if (itemType.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
			if (itemType.getItems().size() > 0 && !products.isFocused())
				itemType.show();
			else
				itemType.hide();
		});
		itemType.setItems(filteredItemTypes);
	}

	private void populateAndLitenSize() {
		FilteredList<Size> filteredSizes = new FilteredList<>(SizeDAO.getAllSizes());
		size.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
			filteredSizes.setPredicate(size -> {
				// If filter text is empty, display all sizes.
				if (newVal == null || newVal.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newVal.toLowerCase();

				if (size.getInitials().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
			if (size.getItems().size() > 0 && !products.isFocused())
				size.show();
			else
				size.hide();
		});
		size.setItems(filteredSizes);
	}

	private void updateItemInfos(Item item) {
		System.out.println("SIZE: " + this.size.getEditor().getText());
		Size size = SizeDAO.getSize(this.size.getEditor().getText());
		if (size == null) {
			SizeDAO.createNewSize(new Size(9999, this.size.getEditor().getText()));
			size = SizeDAO.getSize(this.size.getEditor().getText());
		}

		System.out.println("Item Type: " + this.itemType.getEditor().getText());
		ItemType itemType = ItemTypesDAO.getItemType(this.itemType.getEditor().getText());
		if (itemType == null) {
			ItemTypesDAO.createNewItemType(new ItemType(9999, this.itemType.getEditor().getText()));
			itemType = ItemTypesDAO.getItemType(this.itemType.getEditor().getText());
		}

		System.out.println("Collection: " + this.collection.getEditor().getText());
		Collection collection = CollectionDAO.getCollection(this.collection.getEditor().getText());
		System.out.println(collection);
		if (collection == null) {
			CollectionDAO.createNewCollection(new Collection(9999, this.collection.getEditor().getText(), LocalDate.now()));
			collection = CollectionDAO.getCollection(this.collection.getEditor().getText());
		}

		item.setName(name.getText());
		item.setPrice(Double.parseDouble(price.getText()));
		item.setSize(size);
		item.setItemType(itemType);
		item.setColection(collection);
		item.setColor(color.getValue());

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
