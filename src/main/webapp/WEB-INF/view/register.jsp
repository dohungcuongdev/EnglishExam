<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form method="post" commandName="newUser"
		action="${pageContext.request.contextPath}/newRegister.html">
		Username:<br>
		<input type="text" name="userName">
		<br> Password:<br>
		<input type="text" name="password">
		<br>
		<br>
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>