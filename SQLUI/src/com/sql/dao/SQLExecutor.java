package com.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sql.bean.ResponseBean;
import com.sql.connector.DBConnector;


public class SQLExecutor {

	public static Connection con;	
	public static ResponseBean getSQLResults(String query) throws SQLException, ClassNotFoundException {
		
		con = DBConnector.getConnection();
		ResponseBean response = new ResponseBean(); 
		PreparedStatement statement = con.prepareStatement(query); 		
		Boolean result = statement.execute();
		Boolean columnIterationFlag = true;
		Boolean zeroresultsFlag = true;
		
		
		if (result) {
			Integer rowCount = 0;
			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			ArrayList<String> columns = new ArrayList<String>();
			ResultSet rs = statement.getResultSet();
			Integer columncount = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				zeroresultsFlag = false;
				rowCount++;
				ArrayList<String> rowData = new ArrayList<String>();
				if(columnIterationFlag) {					
					for (int i=1; i<=columncount; i++) {						
						columns.add(rs.getMetaData().getColumnName(i));						
					}
					columnIterationFlag = false;
				}				
				for (int j=1; j<=columncount; j++) {					
					rowData.add(rs.getString(j));
				}
				data.add(rowData);				
			}
			if (zeroresultsFlag) {				
				response.setRecordsFound(zeroresultsFlag);
			} else {
				response.setTableColumns(columns);
				response.setTableData(data);
			}
			response.setRowCount(rowCount);
			return response;
			
		} else {
			Integer count = statement.getUpdateCount();
			response.setUpdateStatus("Update successful. "+ String.valueOf(count) + " Rows updated");
			return response;
		}
		
	}
}