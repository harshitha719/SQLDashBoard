package com.sql.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	//connection instance
	static Connection connection = null;
	 
	public static Connection getConnection() throws ClassNotFoundException, SQLException{		
		if (connection!= null) {
			
		return connection;
		} 
		else {
			String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			
			String CONNECTION_URL = "jdbc:sqlserver://database-1.cfbdhnm640in.us-east-2.rds.amazonaws.com:1433;database=invoicedb";
			
			String USERNAME = "admin";
			
			String PASSWORD = "admin123";
			
			// Step 1: Loading or
			// registering Access JDBC driver class
			Class.forName(DRIVER);
			
			// Step 2.A: Create and
			// get connection using DriverManager class
			connection=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
			return connection;
		}
	}

}