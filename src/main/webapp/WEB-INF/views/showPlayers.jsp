<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Show All Players</title>
	</head>
	<body>
		<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th width="75px">Card Id</th>
					<th width="150px">Name</th>
					<th width="75px">Rank</th>
					<th width="75px">Age</th>
					<th width="150px">Country</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="player" items="${playersList}">
					<tr>
						<td>${player.cardId}</td>
						<td>${player.name}</td>
						<td>${player.rank}</td>
						<td>${player.age}</td>
						<td>${player.country}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p></p>
		<a href="${pageContext.request.contextPath}">Home</a>
	</body>
</html>