<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		<td class="hBorder"><form:errors path="payee" cssClass="error" /></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><form:input path="description" /></td>
		<td class="hBorder"><form:errors path="description" cssClass="error" /></td>
	</tr>
	<tr>
		<td>Reference</td>
		<td><form:input path="reference" /></td>
		<td class="hBorder"><form:errors path="reference" cssClass="error" /></td>
	</tr>
	<tr>
		<td>Category</td>
		<td><form:input path="category" /></td>
		<td class="hBorder"><form:errors path="category" cssClass="error" /></td>
	</tr>
	<tr>
		<td>Date</td>
		<td><form:input type="date" path="dateAsString" /></td>
		<td class="hBorder"><form:errors path="date" cssClass="error" /></td>
	</tr>
	<tr>
		<td>Amount</td>
		<td><form:input path="amount" /></td>
		<td class="hBorder"><form:errors path="amount" cssClass="error" /></td>
	</tr>
	<tr>
		<td class="hBorder"></td>
		<td class="hBorder"><input type="submit" value="save" class="save"></td>
		<td class="hBorder"></td>
	</tr>
</table>
</form:form>
<a href="${pageContext.request.contextPath}/transactions/showAccount?accountId=${accId}">return</a>
