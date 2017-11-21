<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lex Budget - Accounts</title>
</head>
<body>
	<h1>Accounts</h1>
	<br/>
	<input type="button" value="Add Account"
		onclick="window.location.href='showFormForAdd'; return false;"
		class="add-button"
	/>
	
	<table>
		<tr>
			<th>Organisation</th>
			<th>Function</th>
			<th>Current Balance</th>
		</tr>

		<c:forEach var="tempAccount" items="${accounts}">	
			
			<c:url var="updateLink" value="/accounts/showFormForUpdate">
				<c:param name="accountId" value="${tempAccount.id}" />
			</c:url>
			
			<c:url var="deleteLink" value="/accounts/delete">
				<c:param name="accountId" value="${tempAccount.id}" />
			</c:url>
			
			<c:url var="transactionLink" value="/transactions/showAccount">
				<c:param name="accountId" value="${tempAccount.id}" />
			</c:url>
			
			<tr>
				<td>
					<a href="${transactionLink}">${tempAccount.organisation}</a>
				</td>
				<td>${tempAccount.function}</td>
				<td>${tempAccount.currentBalance}</td>
				<td>
					<a href="${updateLink}">Update</a>
					|
					<a href="${deleteLink}"
						onclick="if (!(confirm('Are you sure you want to delete this account?')))  return false">delete</a>
				</td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>