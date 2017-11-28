<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:url var="transactionLink" value="showFormForAdd">
	<c:param name="accountId" value="${account.id}" />
</c:url>
<h1>${account.organisation} - ${account.function} Account</h1>
<input type="button" value="Add Transaction"
	onclick="window.location.href='${transactionLink}'; return false;"
	class="add-button" />
<table>
	<tr>
		<th>date</th>
		<th>payee</th>
		<th>description</th>
		<th>reference</th>
		<th>category</th>
		<th>amount</th>
		<th>balance</th>
	</tr>
	<c:forEach var="tempTransaction" items="${transaction}">
	
	<c:url var="updateLink" value="/transactions/showFormForUpdate">
		<c:param name="transactionId" value="${tempTransaction.id}" />
		<c:param name="accountId" value="${account.id}"/>
	</c:url>
	
	<c:url var="deleteLink" value="/transactions/delete">
		<c:param name="transactionId" value="${tempTransaction.id}" />
		<c:param name="accountId" value="${account.id}"/>
	</c:url>
	
	<tr>
		<td>${tempTransaction.date}</td>
		<td>${tempTransaction.payee}</td>
		<td>${tempTransaction.description}</td>
		<td>${tempTransaction.reference}</td>
		<td>${tempTransaction.category}</td>
		<td>${tempTransaction.amount}</td>
		<td>${tempTransaction.balance}</td>
		<td>
			<a href="${updateLink}">Update</a>
			|
			<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this transaction?')))  return false">Delete</a>
		</td>
	</tr>	
	</c:forEach>
</table>
<a href="${pageContext.request.contextPath}/accounts/main">return</a>