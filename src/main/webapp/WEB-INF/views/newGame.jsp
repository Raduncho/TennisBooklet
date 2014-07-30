<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Add New Match</title>
	</head>
	<body>
		<form:form modelAttribute="gameWrapper" method="POST">
			<table style="text-align:left">
				<thead>
					<tr>
						<th></th>
						<th></th>
						<th width="50px">Set 1</th>
						<th width="50px">Set 2</th>
						<th>Set 3</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<td><form:label path="player1.cardId">Player 1 Id:</form:label></td>
					<td><form:input size="4" path="player1.cardId"/></td>
					<c:forEach var="i" begin="0" end="2">
						<td><form:input path = "sets[${i}].gamesWon" size="1"/></td>
					</c:forEach>
				</tr>
				<tr>
					<td><form:label path="player2.cardId">Player 2 Id:</form:label></td>
					<td><form:input size="4" path="player2.cardId"/></td>
					<c:forEach var="i" begin="3" end="5">
						<td><form:input path = "sets[${i}].gamesWon" size="1"/></td>
					</c:forEach>
				</tr>
				<tr>
					<td><form:label path="date">Date:</form:label></td>
					<td colspan="2"><form:input path="date" size="10"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" /></td>
					<td><input type="reset" value="Reset" /></td>
				</tr>
				</tbody>
			</table>
		</form:form>
		<p></p>
		<p><a href="${pageContext.servletContext.contextPath}">Home</a>
	</body>
</html>