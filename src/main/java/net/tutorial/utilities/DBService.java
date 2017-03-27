package net.tutorial.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class DBService {

	private static DBService instance = new DBService();
	Connection dbConnection = null;
	private PreparedStatement ps = null;

	private DBService() {}

	public static DBService getInstance() {
		return instance;
	}
	
	public void cleanUp() {
		try {
			if (ps != null) {
				ps.close();
			}
			if (this.dbConnection != null) {
				this.dbConnection.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection getConnection() {
		Connection dbConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			System.out.println(e.getMessage());
			return null;
		}

		try {
			
			EnvVariables envVar = new EnvVariables();
			Map<String, String> creds = envVar.getCredentials("cleardb");
			dbConnection = DriverManager.getConnection(creds.get("jdbcUrl"));
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

		return dbConnection;
	}
}
