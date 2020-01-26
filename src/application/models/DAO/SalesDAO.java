package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import application.JDBC;
import application.models.Collection;
import application.models.Customer;
import application.models.Item;
import application.models.ItemType;
import application.models.Sale;
import application.models.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class SalesDAO {

	public static ObservableList<Sale> getAllSales(){
		Connection conn = JDBC.getConnection();
		ObservableList<Sale> sales = FXCollections.observableArrayList();
		String sql = "SELECT id_Sale, Customer_id, Customer.name, cellphone, email, "
				+ "address, CPF, account_number, Item_id, Item.name, Item.price, Item.item_type_id, "
				+ "Item_type.name, size_id, Size.initials, collection_id, Collection.name, Collection.release_date, "
				+ "Color, sale_date FROM Item_sold "
				+ "INNER JOIN Item ON Item_id = Item.id "
				+ "INNER JOIN Customer ON Customer_id = Customer.id "
				+ "INNER JOIN Collection ON Collection.id = collection_id "
				+ "INNER JOIN Item_type ON Item_type.id = item_type_id "
				+ "INNER JOIN Size ON Size.id = size_id";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int id = rs.getInt(1);
				int customerId = rs.getInt(2);
				String customerName = rs.getString(3);
				String customerCellphone = rs.getString(4);
				String email = rs.getString(5);
				String address = rs.getString(6);
				String cpf = rs.getString(7);
				String accountNumber = rs.getString(8);
				Customer customer = new Customer(customerId, customerName, customerCellphone, email, address, cpf, accountNumber);
				int itemId = rs.getInt(9);
				String itemName = rs.getString(10);
				double price = rs.getDouble(11);
				int itemTypeId = rs.getInt(12);
				String itemTypeName = rs.getString(13);
				ItemType itemType = new ItemType(itemTypeId, itemTypeName);
				int sizeId = rs.getInt(14);
				String sizeInitials = rs.getString(15);
				Size size= new Size(sizeId, sizeInitials);
				int collectionId = rs.getInt(16);
				String collectionName = rs.getString(17);
				LocalDate releaseDate = LocalDate.parse(rs.getString(18));
				Collection collection = new Collection(collectionId, collectionName, releaseDate);
				Color color = Color.valueOf(rs.getString(19));
				Item item = new Item(itemId, itemName, itemType, price, size, color, collection, 0);
				LocalDate saleDate = LocalDate.parse(rs.getString(20)); 
				sales.add(new Sale(id, saleDate, customer, item));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return sales;
	}
	
	public static void registerNewSale(Sale sale) {
		Connection conn = JDBC.getConnection();
		String sql = "INSERT INTO Item_sold(Customer_id, Item_id, sale_date) VALUES(?,?,?)";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setInt(1, sale.getCustomer().getId());
			stat.setInt(2, sale.getItem().getId());
			stat.setString(3, String.valueOf(sale.getSaleDate()));
			stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
