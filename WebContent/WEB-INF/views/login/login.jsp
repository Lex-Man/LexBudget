<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<h1>Login Page</h1>

	<form:form action="${pageContext.request.contextPath}/login/authenticate" method="POST">
		
		<!--  Check for login error -->
		<c:if test="${param.error != null}">
		
			<i class="error"> Sorry! You entered a invalid username/password.</i>
		</c:if>
		<table>
		<tbody>
			<tr>
				<th class="login">
					User name: 
				</th>
				<th class="login">
					<input type="text" name="username" />
				</th>
			</tr>
			<tr>
				<th class="login">
					Password: 
				</th>
				<th class="login">
					<input type="password" name="password" />
				</th>
			</tr>
			<tr>
				<th class="login">
					<input type="submit" value="Login" />
				</th>
			</tr>
		</tbody>
		</table>
	</form:form>