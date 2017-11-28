<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form action="saveAccount" modelAttribute="account" method="POST">
	<form:hidden path="id" />
	<table>
		<tr>
			<td><label></label>Organisation</td>
			<td><form:input path="organisation" /></td>
			<td><form:errors path="organisation" cssClass="error" /></td>
		</tr>
		<tr>
			<td><label></label>Function</td>
			<td><form:input path="function" /></td>
			<td><form:errors path="function" cssClass="error" /></td>
		</tr>
		<tr>
			<td><label></label></td>
			<td><input type="submit" value="Save" class="save"></td>
		</tr>
	</table>

</form:form>

