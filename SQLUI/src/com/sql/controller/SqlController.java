package com.sql.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sql.bean.ResponseBean;
import com.sql.dao.SQLExecutor;

public class SqlController extends HttpServlet{

private static final long serialVersionUID = 1L;
public	ArrayList<ArrayList<String>> allData=new ArrayList<ArrayList<String>>();
public	ArrayList<String> allColumnNames = new ArrayList<String>();
String updateresult;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher dispatcher = request.getRequestDispatcher("SQLHome.jsp");
	try {
		updateresult = null;
		String query = request.getParameter("query");		
		String columnName = request.getParameter("columnName");
		String columnValue = request.getParameter("columnValue");
		
		if (query!= null && query!= "") {
			Boolean genericQueryFlag = true;
			ResponseBean resp = SQLExecutor.getSQLResults(query, genericQueryFlag);
			allData = resp.getTableData();
			allColumnNames = resp.getTableColumns();
			updateresult = resp.getUpdateStatus();
			request.setAttribute("colNames", allColumnNames);
			request.setAttribute("rows", allData);
			request.setAttribute("status", updateresult);		
			dispatcher.forward(request, response);
		}  
		else if (checkNullOrEmpty(columnName) && checkNullOrEmpty(columnValue)) {
			Boolean genericQueryFlag = false;
			String newQuery;
			String [] columnInfo = columnName.split("\\.");
			if(columnInfo[2].equalsIgnoreCase("integer") || columnInfo[2].equalsIgnoreCase("decimal")) {
				query = "Select * from " + columnInfo[0] + " where "+ columnInfo[1] +" = "+ columnValue ;
			} else if (columnInfo[2].equalsIgnoreCase("date")) {				
				query = "Select * from " + columnInfo[0] + " where "+ columnInfo[1] +" = CAST('"+ columnValue +"' AS DATE)"; 
			} else if (columnInfo[2].equalsIgnoreCase("string")) {
				query = "Select * from " + columnInfo[0] + " where "+ columnInfo[1] +" = '"+ columnValue +"'";
			}
			
			ResponseBean resp = SQLExecutor.getSQLResults(query, genericQueryFlag);
			allData = resp.getTableData();
			allColumnNames = resp.getTableColumns();
			updateresult = resp.getUpdateStatus();
			request.setAttribute("colNames", allColumnNames);
			request.setAttribute("rows", allData);
			request.setAttribute("status", updateresult);		
			dispatcher.forward(request, response);
		}
		else {				
			request.setAttribute("colNames", allColumnNames);
			request.setAttribute("rows", allData);
			request.setAttribute("status", updateresult);		
			dispatcher.forward(request, response);
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block		
		e.printStackTrace();
		updateresult = e.getMessage();
		request.setAttribute("status", updateresult);
		dispatcher.forward(request, response);
	}
}

public Boolean checkNullOrEmpty(String value) {
	if (value!=null && value!= "") {
		return true;
	} else {
		return false;
	}
}



public ArrayList<ArrayList<String>> getAllData() {
	return allData;
}
public void setAllData(ArrayList<ArrayList<String>> allData) {
	this.allData = allData;
}
public ArrayList<String> getAllColumnNames() {
	return allColumnNames;
}
public void setAllColumnNames(ArrayList<String> allColumnNames) {
	this.allColumnNames = allColumnNames;
}
public String getUpdateresult() {
	return updateresult;
}
public void setUpdateresult(String updateresult) {
	this.updateresult = updateresult;
}
	
}
