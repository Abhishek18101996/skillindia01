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
<title>Admin Dashboard</title>
</head>
<body>
<c:if test="${!empty CandidateList}">
	<table class="tg" border="1">
	<tr>
		<th width="50">Candidate ID</th>
		<th width="50">Candidate Name</th>
		<th width="50">Qualification</th>
		<th width="50">Status Reply</th>
	</tr>
	<c:forEach items="${CandidateList}" var="candidate">
		<tr>
			<td>${candidate.userId}</td>
			<td>${candidate.candidateName}</td>
			<td>${candidate.educationalDetails}</td>
			<td><a href="
			<c:url value='/accept/${candidate.userId}' />" >Accept</a></td>
			<td><a href="
			<c:url value='/reject/${candidate.userId}'/>" >Reject</a>
			</td>									
		</tr>
		</c:forEach>
	</table>
</c:if>
<br>
<br>
<c:if test="${!empty EstablishmentList}">
	<table class="tg" border="1">
	<tr>
		<th width="50">Establishment ID</th>
		<th width="50">Establishment Name</th>
		<th width="50">Establishment Type</th>
		<th width="50">Request Status</th>
	</tr>
	<c:forEach items="${EstablishmentList}" var="establishment">
		<tr>
			<td>${establishment.estId}</td>
			<td>${establishment.estName}</td>
			<td>${establishment.estType}</td>
			<td><a href="
			<c:url value='/accepte/${establishment.estId}' />" >Accept</a></td>
			<td><a href="
			<c:url value='/rejecte/${establishment.estId}'/>" >Reject</a>
			</td>									
		</tr>
		</c:forEach>
	</table>
</c:if>
<br>
<br>
<a href="adminsignout">Signout</a>
</body>
</html>