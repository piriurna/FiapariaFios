package application.models;

import java.time.LocalDate;

public class Sale {

	private int id;
	private LocalDate saleDate;
	private Customer customer;
	private Item item;
	
	public Sale(int id, LocalDate saleDate, Customer customer, Item item) {
		this.id = id;
		this.saleDate = saleDate;
		this.customer = customer;
		this.item = item;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
