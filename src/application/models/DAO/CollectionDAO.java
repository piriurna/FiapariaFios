package application.models.DAO;

import java.sql.Connection;
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
}
