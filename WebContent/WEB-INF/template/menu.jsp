<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<strong>LOGO</strong>
<div class="navbar">
	<ul>
		<li><div class="colour1"><a href="${pageContext.request.contextPath}/">HOME</a></div></li>
		<li><div class="colour2"><a href="${pageContext.request.contextPath}/accounts/main">ACCOUNTS</a></div></li>
		<li><div class="colour3"><a href="">PAYMENT</a></div></li>
		<li><div class="colour4"><a href="">Test</a><div class="dropdown-content">THIS IS A TEST</div></div></li>
		<li><div class="colour5"><a href="">Test</a></div></li>
		<li><div class="colour6"><a href="">Test</a></div></li>
		<li><div class="colour7">
			<security:authorize access="isAuthenticated()">
				<form:form id="logout" action="${pageContext.request.contextPath}/login/logout" method="post">
					<input type="submit" value="Logout" />
				</form:form>
			</security:authorize>
			<security:authorize access="!isAuthenticated()">
				<a href="${pageContext.request.contextPath}/login/signup">Sign Up</a>
			</security:authorize>
		<!--  <a href="${pageContext.request.contextPath}/login/logout">Logout</a>-->
		</div></li>
	</ul>
	
</div>