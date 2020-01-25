package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.JDBC;
import application.models.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SizeDAO {

	public static ObservableList<Size> getAllSizes(){
		Connection conn = JDBC.getConnection();
		ObservableList<Size> sizes = FXCollections.observableArrayList();
		String sql = "SELECT * FROM Size";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				sizes.add(new Size(id, name));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return sizes;
	}
	
	public static Size getSize(String initials) {
		Connection conn = JDBC.getConnection();
		Size size = null;
		String sql = "SELECT * FROM Size WHERE initials = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, initials);
			try(ResultSet rs = stat.executeQuery()){
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				size = new Size(id, name);
			}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return size;
	}
}
