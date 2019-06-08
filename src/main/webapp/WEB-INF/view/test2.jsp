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
	<div id="app">
		<form method="post" action="${pageContext.request.contextPath}/computeScore.html">
			Question 1 <input type="text" name="Q1" path="Q1"/>
			<br> Question 2 <select path="Q2" name="Q2">
				<option value="A">A</option>
				<option value="B">B</option>
				<option value="C">C</option>
				<option value="D">D</option>
			</select>
			<br>
			Question 3 <input path="Q3" type="radio" name="Q3" value="A"/> A<br> 
				<input path="Q3" type="radio" name="Q3" value="B"/>
			B<br> <input path="Q3" type="radio" name="Q3" value="C"/> C
			<br>
			Question 4
			<input type="text" name="Q4" path="Q4"/>
			<br>
			Question 5
			<input path="Q5" name="Q5" type="radio" value="True"/>True<br> 
			<input path="Q5" name="Q5" type="radio" value="False"/>False
			<br>
			Question 6
			<br><select path="Q6" name="Q6">
				<option value="A">A</option>
				<option value="B">B</option>
				<option value="C">C</option>
				<option value="D">D</option>
			</select>
			<br>
			
			<button action="submit">Submit</button>
		</form>
		${studentResult}
	</div>
</body>
</html>