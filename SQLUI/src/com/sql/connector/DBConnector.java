package com.sql.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	static Connection connection = null;
	static {
		try {
			
			String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			
			String CONNECTION_URL = "jdbc:sqlserver://salesdatanew.ctsql1ybbrv6.us-east-2.rds.amazonaws.com:1433;database=salesdb";
			
			String USERNAME = "admin";
			
			String PASSWORD = "admin123";
			
			// Step 1: Loading or
			// registering Access JDBC driver class
			Class.forName(DRIVER);
			
			// Step 2.A: Create and
			// get connection using DriverManager class
			connection=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD); 

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public static Connection getConnection() {		
		return connection;
	}

}