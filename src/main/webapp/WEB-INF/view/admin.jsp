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
	<form:form method="post" commandName="newReading"
		action="${pageContext.request.contextPath}/addNewReading.html">
		id:<br>
		<input type="text" name="id">
		<br> title:<br>
		<input type="text" name="topic">
		<br>
		<br>
		<input type="submit" value="Submit">
	</form:form>
	<form:form method="post" commandName="newSpeaking"
		action="${pageContext.request.contextPath}/addNewSpeaking.html">
		id:<br>
		<input type="text" name="id">
		<br> title:<br>
		<input type="text" name="topic">
		<br>
		<br>
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>