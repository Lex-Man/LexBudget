<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Add Account</h1>
<form:form action="saveAccount" modelAttribute="account" method="POST">
	<form:hidden path="id" />
	<table>
		<tr>
			<td><label></label>Organisation</td>
			<td><form:input path="organisation" /></td>
			<td class="hBorder"><form:errors path="organisation" cssClass="error" /></td>
		</tr>
		<tr>
			<td><label></label>Function</td>
			<td><form:input path="function" /></td>
			<td class="hBorder"><form:errors path="function" cssClass="error" /></td>
		</tr>
		<tr>
			<td class="hBorder"><label></label></td>
			<td class="hBorder"><input type="submit" value="Save" class="save"></td>
		</tr>
	</table>

</form:form>

