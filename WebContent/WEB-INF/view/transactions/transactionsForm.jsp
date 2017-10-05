<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Transaction</title>
</head>
<body>
<h1>Add Transaction</h1>
<form:form action="saveTransaction" modelAttribute="transaction" method="POST">
<form:hidden path="id" />
<form:hidden path="accountsId.id" />
<form:hidden path="accountsId.organisation" />
<form:hidden path="accountsId.function" />
<form:hidden path="accountsId.currentBalance" />
<table>
	<tr>
		<td>Payee</td>
		<td><form:input path="payee" /></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><form:input path="description" /></td>
	</tr>
	<tr>
		<td>Reference</td>
		<td><form:input path="reference" /></td>
	</tr>
	<tr>
		<td>Category</td>
		<td><form:input path="category" /></td>
	</tr>
	<tr>
		<td>Date</td>
		<td><form:input type="date" path="date" /></td>
	</tr>
	<tr>
		<td>Amount</td>
		<td><form:input path="amount" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="save" class="save"></td>
	</tr>
</table>
</form:form>
<a href="${pageContext.request.contextPath}/transactions/showAccount?accountId=${accId}">return</a>
</body>
</html>