package common.db;

import java.sql.*;

public class DataBase {

	private String url = "jdbc:mysql://localhost:3306/test1";
	private String username = "root";
	private String password = "root";

	private Connection conn;

	public DataBase() {

	}

	public Connection getconnection() throws SQLException {

		conn = DriverManager.getConnection(url, username, password);
		return conn;

	}

	public boolean checkconnection() {

		try {
			conn = DriverManager.getConnection(url, username, password);

			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

}
