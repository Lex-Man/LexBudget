<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Account</title>
</head>
<body>
<div id="header">
<h1>Add new account</h1>
</div>
<div id="main">
<form:form action="saveAccount" modelAttribute="account" method="POST">
	<form:hidden path="id" />
	<table>
		<tr>
			<td><label></label>Organisation</td>
			<td><form:input path="organisation" /></td>
		</tr>
		<tr>
			<td><label></label>Function</td>
			<td><form:input path="function" /></td>
		</tr>
		<tr>
			<td><label></label></td>
			<td><input type="submit" value="Save" class="save"></td>
		</tr>
	</table>

</form:form>

</div>
</body>
</html>