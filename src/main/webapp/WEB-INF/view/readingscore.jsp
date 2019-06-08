<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
List<String> studentAnswers = (List<String>) request.getAttribute("studentAnswers");
List<String> correctAnswsers = (List<String>) request.getAttribute("correctAnswsers");
List<String> results = (List<String>) request.getAttribute("results");
System.out.println(studentAnswers);
System.out.println(correctAnswsers);
for(int i = 0; i < correctAnswsers.size(); i++) {
	out.println("Your Answer: " + studentAnswers.get(i) + ", Correct Answer: " + correctAnswsers.get(i) + " - " + results.get(i));
	out.println("<br>");
}
%>

Total Score: ${studentResult}
</body>
</html>