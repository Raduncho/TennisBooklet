<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Show All Games</title>
	</head>
	<body>
		<table style="text-align: center" border="1px" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th width="150px">Player 1</th>
					<th width="150px">Player 2</th>
					<th width="150px">Score</th>
					<th width="150px">Date</th>
				</tr>
			</thead>
			<tbody>				
			<c:forEach items="${gamesList}" var="gameWrapper">
				<tr>
					<td><c:out value="${gameWrapper.player1.name}"/></td>
					<td><c:out value="${gameWrapper.player2.name}"/></td>
					<td>
						<c:choose>
							<c:when test="${fn:length(gameWrapper.sets) gt 4}">
								<c:forEach var="i" begin="0" end="2">
									<c:out value="${gameWrapper.sets[i].gamesWon}"></c:out>:<c:out value="${gameWrapper.sets[i+3].gamesWon}"></c:out>
								</c:forEach>						
							</c:when>
							<c:otherwise>
								<c:forEach var="i" begin="0" end="1">
									<c:out value="${gameWrapper.sets[i].gamesWon}"></c:out>:<c:out value="${gameWrapper.sets[i+2].gamesWon}"></c:out>
								</c:forEach>							
							</c:otherwise>						
						</c:choose>
						
					</td>
					<td>
					<fmt:formatDate value="${gameWrapper.date}" pattern="dd-MM-yyyy"/>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<p></p>
		<a href="${pageContext.request.contextPath}">Home</a>
	</body>
</html>