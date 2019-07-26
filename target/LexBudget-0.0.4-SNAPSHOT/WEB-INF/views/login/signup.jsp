<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Sign Up</h1>
<form:form action="${pageContext.request.contextPath}/login/signup" modelAttribute="signup" method="POST" >

	<table>
		<tbody>
			<tr>
				<th class="login">User name: </th>
				<th class="login"><form:input type="text" path="username" name="username" /></th>
			</tr>
			<tr>
				<th class="login">Password: </th>
				<th class="login"><form:input type="password" path="password" name="password" /></th>
			</tr>
			<tr>
				<th class="login"><input type="submit" value="submit" /></th>
			</tr>
		</tbody>
	</table>

</form:form>