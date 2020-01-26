package application.models.DAO;

import java.sql.Connection;
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
}
