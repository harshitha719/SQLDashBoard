package com.sql.bean;
import java.util.ArrayList;

public class ResponseBean {
	
	private ArrayList<ArrayList<String>> tableData;
	
	private ArrayList<String> tableColumns;
	
	private String updateStatus;
	
	private Boolean recordsFound;
	
	private Integer rowCount;

	public ArrayList<ArrayList<String>> getTableData() {
		return tableData;
	}

	public void setTableData(ArrayList<ArrayList<String>> tableData) {
		this.tableData = tableData;
	}

	public ArrayList<String> getTableColumns() {
		return tableColumns;
	}

	public void setTableColumns(ArrayList<String> tableColumns) {
		this.tableColumns = tableColumns;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}

	public Boolean getRecordsFound() {
		return recordsFound;
	}

	public void setRecordsFound(Boolean recordsFound) {
		this.recordsFound = recordsFound;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

}
