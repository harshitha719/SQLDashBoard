<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.sql.bean.ResponseBean"%>
<%@page import="com.sql.controller.SqlController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#dataTable').DataTable();
} );
$(document).ready(function() {
	  $('#fields').change(function() {
	    selection = $(this).val();
	    switch (selection) {
	      case 'invoiceItem.invoiceDate.date':
	        $('#value').show();
	        $('#value').attr("placeholder", "YYMMDD");
	        break;
	      case 'invoiceItem.itemNumber.integer':
		    $('#value').show();
		    $('#value').attr("placeholder", "0");
		    break;
	      case 'invoiceItem.quantity.integer':
			    $('#value').show();
			    $('#value').attr("placeholder", "0");
			    break;
	      case 'invoiceItem.price.decimal':
			    $('#value').show();
			    $('#value').attr("placeholder", "0.00");
			    break;
	      case 'item.itemNumber.integer':
			    $('#value').show();
			    $('#value').attr("placeholder", "0");
			    break;
	      case 'item.price.decimal':
			    $('#value').show();
			    $('#value').attr("placeholder", "0.00");
			    break;
		    
	      default:
	        $('#value').show();
	        break;
	    }
	  });
	});
	
function populateFields(value){
	$("#fields").empty();
	$("#fields").append("<option>Select</option>");
	var customerList = [
        {"Id": "customer.lastName.string", "Name": "lastName"}, 
        {"Id": "customer.firstName.string", "Name": "firstName"},
        {"Id": "customer.emailAddress.string", "Name": "emailAddress"}, 
        {"Id": "customer.password.string", "Name": "password"}
        ];
	var invoiceItemList = [
        {"Id": "invoiceItem.invoiceNumber", "Name": "invoiceNumber"}, 
        {"Id": "invoiceItem.itemNumber.integer", "Name": "itemNumber"},
        {"Id": "invoiceItem.invoiceDate.date", "Name": "invoiceDate"},
        {"Id": "invoiceItem.customerEmail.string", "Name": "customerEmail"},         
        {"Id": "invoiceItem.quantity.integer", "Name": "quantity"}, 
        {"Id": "invoiceItem.price.decimal", "Name": "price"}
        ];
	var itemList = [
		{"Id": "item.itemNumber.integer", "Name": "itemNumber"},
		{"Id": "item.price.decimal", "Name": "price"}
        ];
	if(value==1){
		for (var i = 0; i <= customerList.length; i++) {
				$('#fields').append('<option value="' + customerList[i].Id + '">' + customerList[i].Name + '</option>');
		}
	   }
		if(value==2){
		for (var i = 0; i <= invoiceItemList.length; i++) {
				$('#fields').append('<option value="' + invoiceItemList[i].Id + '">' + invoiceItemList[i].Name + '</option>');
			}
		}
		if(value==3){
		for (var i = 0; i <= itemList.length; i++) {
				$('#fields').append('<option value="' + itemList[i].Id + '">' + itemList[i].Name + '</option>');
			}
	    }
}
</script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css" rel="stylesheet"></link>
<link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet"></link>
<title>SQL - Dashboard</title>
</head>
<body>
<nav class="navbar navbar-dark bg-primary">
    SQL - Dashboard
    </nav>
    <form action="${pageContext.request.contextPath}/SqlController" method="post" name='form'>
 <div class="form-group">
 <table>
 <tr>
 <td>
 <label for="tables">Choose a table name:</label>
 <select id="my-select" name="tableName" onchange="populateFields(this.value);">
 <option value=0>Select</option>
<option value=1>Customer</option>
<option value=2>InvoiceItem</option>
<option value=3>Item</option>
</select>
</td>
</tr>
<tr>
<td>
<label for="feilds">Choose a field name:</label>
<select name="columnName" id="fields" >
    <option value="" disabled selected>Select</option>
</select>
<input type="text" name="columnValue" id="value" style="display:none;"><br><br>
</td>
</tr>
</table>
<button type="submit" class="btn btn-primary" >Run Selection</button>
 </div>
<div class="form-group">
  <label for="exampleFormControlTextarea2">Enter the User-Defined Query</label>
  <textarea class="form-control rounded-0" id="exampleFormControlTextarea2"name="query" ></textarea>
</div>
<button type="submit" class="btn btn-primary" >U-D Query</button>
</form>
<c:choose>
    <c:when test="${empty status}">
        <div class="card-header" >
            <i class="fas fa-table"></i>
            Results Table </div>
        
    </c:when>
    <c:otherwise>
        <div class="card-header" >
            <i class="fas fa-table"></i>
            Results - <%= request.getAttribute("status") %></div>        
    </c:otherwise>
</c:choose>
<table id="dataTable" class="table table-striped table-bordered" style="width:100%" >
        <thead>
            <c:forEach var="col" items="${colNames}">        
       		 <td><c:out value="${col}" /></td>
          </c:forEach>
        </thead>
        <tbody>
            <c:forEach var="list" items="${rows}"> 
             <tr>
            <c:forEach var="data" items="${list}">       
       		<td><c:out value="${data}" /></td>
          </c:forEach>
          </tr>
           </c:forEach>
        </tbody>
        </table>
</body>
</html>