<%@ page import="model.Connections" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connection Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Connection Management </h1>
<form id="formItem" name="formItem">
 Connection Name : 
 <input id="conName" name="conName" type="text" 
 class="form-control form-control-sm">
 <br> Connection Type : 
 <input id="conType" name="conType" type="text" 
 class="form-control form-control-sm">
 <br> Connection Description: 
 <input id="conDesc" name="conDesc" type="text" 
 class="form-control form-control-sm">

<br> Connection AdminId: 
 <input id="conAdminId" name="conAdminId" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Connections connectionsObj = new Connections(); 
	 out.print(connectionsObj.readConnections()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
