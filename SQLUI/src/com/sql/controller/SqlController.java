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
		String query = request.getParameter("query");
		ResponseBean resp = SQLExecutor.getSQLResults(query);
		allData = resp.getTableData();
		allColumnNames = resp.getTableColumns();
		updateresult = resp.getUpdateStatus();
		request.setAttribute("colNames", allColumnNames);
		request.setAttribute("rows", allData);
		request.setAttribute("status", updateresult);		
		dispatcher.forward(request, response);
	} catch (Exception e) {
		// TODO Auto-generated catch block		
		e.printStackTrace();
		updateresult = e.getMessage();
		request.setAttribute("status", updateresult);
		dispatcher.forward(request, response);
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
