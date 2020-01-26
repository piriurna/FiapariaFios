package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import application.JDBC;
import application.models.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionDAO {

	public static ObservableList<Collection> getAllCollections(){
		ObservableList<Collection> collections = FXCollections.observableArrayList();
		Connection conn = JDBC.getConnection();
		String sql = "SELECT * FROM Collection";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				LocalDate releaseDate = LocalDate.parse(String.valueOf(rs.getDate(3)));
				collections.add(new Collection(id, name, releaseDate));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return collections;
	}
	
	public static Collection getCollection(String collectionName) {
		Connection conn = JDBC.getConnection();
		Collection collection = null;
		String sql = "SELECT * FROM Collection WHERE name = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, collectionName);
			try(ResultSet rs = stat.executeQuery()){
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				LocalDate releaseDate = LocalDate.parse(rs.getString(3));
				collection = new Collection(id, name, releaseDate);
			}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return collection;
	}
	
	public static void createNewCollection(Collection collection) {
		Connection conn = JDBC.getConnection();
		String sql = "INSERT INTO Collection(name, release_date) VALUES(?, ?)";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, collection.toString());
			stat.setString(2, String.valueOf(collection.getReleaseDate()));
			stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
