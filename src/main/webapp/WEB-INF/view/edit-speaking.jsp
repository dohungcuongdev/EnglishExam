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
	Edit Speaking Infor ID = ${id}
	<form:form method="post" commandName="speaking"
		action="${pageContext.request.contextPath}/updateSpeaking.html">
		id:<br>
		<input type="text" name="id"  value="${speaking.id}"> 
		<br> topic:<br>
		<input type="text" name="topic" value="${speaking.topic}">
		<br>
		<br>
		<input type="submit" value="Save">
	</form:form>
	<a href="${pageContext.request.contextPath}/speaking/${id}.html">Check data again</a>
</body>
</html>