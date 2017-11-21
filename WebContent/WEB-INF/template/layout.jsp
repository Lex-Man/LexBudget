<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/CSS/styles.css" />" rel="stylesheet" />
<title><tiles:insertAttribute name="title"/></title>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="menu" />
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer" />
</body>
</html>
