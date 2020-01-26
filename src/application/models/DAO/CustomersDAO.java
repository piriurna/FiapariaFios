package application.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.JDBC;
import application.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomersDAO {

	public static ObservableList<Customer> getAllCustomer(){
		Connection conn = JDBC.getConnection();
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		String sql = "SELECT * FROM Customer";
		try(Statement stat = conn.createStatement(); ResultSet rs = stat.executeQuery(sql)){
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String cellphone = rs.getString(3);
				String email = rs.getString(4); 
				String address = rs.getString(5); 
				String cpf = rs.getString(6);
				String account_number = rs.getString(7);
				customers.add(new Customer(id, name, cellphone, email, address, cpf, account_number));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}
	
	public static int createNewCustomer(Customer customer) {
		Connection conn = JDBC.getConnection();
		int rowsUpdated = 0;
		String sql = "INSERT INTO Customer(name, cellphone, email, address, CPF, account_number) VALUES(?,?,?,?,?,?)";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, customer.toString());
			stat.setString(2, customer.getCellphone());
			stat.setString(3, customer.getEmail());
			stat.setString(4, customer.getAddress());
			stat.setString(5, customer.getCpf());
			stat.setString(6, customer.getAccountNumber());
			rowsUpdated = stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;
	}
	
	public static int updateCustomer(Customer customer) {
		Connection conn = JDBC.getConnection();
		int rowsUpdated = 0;
		String sql = "UPDATE Customer SET name = ?, cellphone = ?, email = ?, address = ?, CPF = ?, account_number = ?"
				+ " WHERE Customer.id = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, customer.toString());
			stat.setString(2, customer.getCellphone());
			stat.setString(3, customer.getEmail());
			stat.setString(4, customer.getAddress());
			stat.setString(5, customer.getCpf());
			stat.setString(6, customer.getAccountNumber());
			stat.setInt(7, customer.getId());
			rowsUpdated = stat.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;
	}
	
	public static Customer getCustomer(String name) {
		Connection conn = JDBC.getConnection();
		Customer customer = null;
		String sql = "SELECT * FROM Customer WHERE name = ?";
		try(PreparedStatement stat = conn.prepareStatement(sql)){
			stat.setString(1, name);
			try(ResultSet rs = stat.executeQuery()){
				if(rs.next()) {
					int id = rs.getInt(1);
					String customerName = rs.getString(2);
					String cellphone = rs.getString(3);
					String email = rs.getString(4); 
					String address = rs.getString(5); 
					String cpf = rs.getString(6);
					String account_number = rs.getString(7);
					customer = new Customer(id, customerName, cellphone, email, address, cpf, account_number);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
}
