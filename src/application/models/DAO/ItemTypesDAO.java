package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.JDBC;
import application.models.ItemType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemTypesDAO {

	public static ObservableList<ItemType> getAllItemTypes(){
		ObservableList<ItemType> itemTypes = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM Item_type";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				itemTypes.add(new ItemType(id, name));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return itemTypes;
	}
	
	public static ItemType getItemType(String itemTypeName) {
		Connection conn = JDBC.getConnection();
		ItemType itemType = null;
		String sql = "SELECT * FROM Item_type WHERE name = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, itemTypeName);
			try(ResultSet rs = stat.executeQuery()){
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				itemType = new ItemType(id, name);
			}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return itemType;
	}
	
	public static void createNewItemType(ItemType itemType) {
		Connection conn = JDBC.getConnection();
		String sql = "INSERT INTO Item_type(name) VALUES(?)";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, itemType.getName());
			stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
