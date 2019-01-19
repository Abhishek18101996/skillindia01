<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" 
prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" 
prefix="form" %>
<%@ page session="true" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course List</title>
</head>
<body>
<h3>Available Course</h3>
<c:if test="${!empty CourseList}">
	<table class="tg" border="1">
	<tr>
		<th width="50">Course ID</th>
		<th width="50">Course Description</th>
		<th width="50">Course Name</th>
		<th width="50">Course Duration</th>
		<th width="50">Establishment Id</th>
		<th width="50">Select the course</th>
	</tr>
	<c:forEach items="${CourseList}" var="course">
		<tr>
			<td>${course.courseId}</td>
			<td>${course.courseName}</td>
			<td>${course.courseDescription}</td>
			<td>${course.courseDuration}</td>		
			<td>${course.establishmentId}</td>	
			<td><a href="
			<c:url value='/applyCourse/${course.courseId}' />" >Apply</a></td>							
		</tr>
	</c:forEach>
	</table>
</c:if>

</body>
</html>