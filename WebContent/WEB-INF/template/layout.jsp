<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value="/resources/CSS/LexBudget.css" />" rel="stylesheet" />
	<title><tiles:insertAttribute name="title"/></title>
</head>
<body>
	<nav>
		<tiles:insertAttribute name="menu" />
	</nav>
	<main class="main">
		<tiles:insertAttribute name="header" />
		<article>
			<tiles:insertAttribute name="body"/>
		</article>
		<tiles:insertAttribute name="footer" />	
	</main>
</body>
</html>
