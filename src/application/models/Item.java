package application.models;

import javafx.scene.paint.Color;

public class Item {
	
	private int id;
	private String name;
	private ItemType itemType;
	private double price;
	private Size size;
	private Color color;
	private Collection colection;
	private int stock;
	
	public Item(int id, String name, ItemType itemType, double price, Size size, Color color, Collection colection, int stock) {
		this.id = id;
		this.name = name;
		this.itemType = itemType;
		this.price = price;
		this.size = size;
		this.color = color;
		this.colection = colection;
		this.stock = stock;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Collection getColection() {
		return colection;
	}
	public void setColection(Collection colection) {
		this.colection = colection;
	}

	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
