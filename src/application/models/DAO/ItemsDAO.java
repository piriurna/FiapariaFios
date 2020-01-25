package application.models.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import application.JDBC;
import application.models.Collection;
import application.models.Item;
import application.models.ItemType;
import application.models.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class ItemsDAO {

	public static ObservableList<Item> getAllItems(){
		Connection conn = JDBC.getConnection();
		ObservableList<Item> itens = FXCollections.observableArrayList();
		String sql = "SELECT Item.id, Item.name, Item_type.id, Item_type.name, Item.price, Item.size_id, "
				+ "Size.initials, Item.color, Item.collection_id, Collection.name, Collection.release_date, Item.itens_in_stock FROM Item "
				+ "INNER JOIN Collection ON Collection.id = collection_id "
				+ "INNER JOIN Item_type ON Item_type.id = item_type_id "
				+ "INNER JOIN Size ON Size.id = size_id";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int typeId = rs.getInt(3);
				String typeName = rs.getString(4);
				ItemType itemType = new ItemType(typeId, typeName);
				double price = rs.getDouble(5); 
				int sizeId = rs.getInt(6);
				String initials = rs.getString(7);
				Size size = new Size(sizeId, initials);
				
				String colorString = rs.getString(8);
				System.out.println(colorString);
				Color color = Color.valueOf(colorString);
				int collectionId = rs.getInt(9);
				String collectionName = rs.getString(10);
				LocalDate releaseDate = LocalDate.parse(rs.getString(11));
				Collection collection = new Collection(collectionId, collectionName, releaseDate);
				int stock = rs.getInt(12);
				itens.add(new Item(id, name, itemType, price, size, color, collection, stock));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return itens;
	}
	
	public static void createNewItem(Item item) {
		Connection conn = JDBC.getConnection();
		String sql = "SELECT Item.id, Item.name, Item_type.id, Item_type.name, Item.price, Item.size_id, "
				+ "Size.initials, Item.color, Item.collection_id, Collection.name, Collection.release_date, Item.itens_in_stock FROM Item "
				+ "INNER JOIN Collection ON Collection.id = collection_id "
				+ "INNER JOIN Item_type ON Item_type.id = item_type_id "
				+ "INNER JOIN Size ON Size.id = size_id";
		try(Statement stat = conn.createStatement()){
			try(ResultSet rs = stat.executeQuery(sql)){}
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int typeId = rs.getInt(3);
				String typeName = rs.getString(4);
				ItemType itemType = new ItemType(typeId, typeName);
				double price = rs.getDouble(5); 
				int sizeId = rs.getInt(6);
				String initials = rs.getString(7);
				Size size = new Size(sizeId, initials);
				
				String colorString = rs.getString(8);
				System.out.println(colorString);
				Color color = Color.valueOf(colorString);
				int collectionId = rs.getInt(9);
				String collectionName = rs.getString(10);
				LocalDate releaseDate = LocalDate.parse(rs.getString(11));
				Collection collection = new Collection(collectionId, collectionName, releaseDate);
				int stock = rs.getInt(12);
				item = new Item(id, name, itemType, price, size, color, collection, stock);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return item;
	}
}
