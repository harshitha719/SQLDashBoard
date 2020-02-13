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
</script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css" rel="stylesheet"></link>
<link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet"></link>
<title>SQL - Dashboard</title>
</head>
<body>
<nav class="navbar navbar-dark bg-primary">
    SQL - Dashboard
    </nav>
    <form action="${pageContext.request.contextPath}/SqlController" method="post" name='form' onsubmit="return validate()">
<div class="form-group">
  <label for="exampleFormControlTextarea2">Enter the SQL Command</label>
  <textarea class="form-control rounded-0" id="exampleFormControlTextarea2"name="query" required="required rows="6"></textarea>
</div>
<input type="submit" class="btn btn-primary"></input>
</form>
<div class="card-header" >
            <i class="fas fa-table"></i>
            Results Table</div>
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