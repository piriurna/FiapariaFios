package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JDBC {
	private static final String URL = "jdbc:mysql://remotemysql.com:3306/QZxc8hjyph";

	private static Connection con;
	private static final String USER = "QZxc8hjyph";
	private static final String PASS = "YWIPGonUNq";

	private JDBC() {
	}

	/**
	 * Cria(quando preciso) e retorna o objeto de ligacao a base de dados
	 * @return objeto Connection.
	 */
	public static Connection getConnection() {
		try {
			if (con == null)
				con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
	}
		return con;
	}
}
