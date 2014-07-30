<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Enter Id of Player</title>
	</head>
	<body>
		<form:form modelAttribute="player" method="POST">
			<table>
				<tr>
					<td><form:label path="name">Player Name: </form:label></td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" /></td>
					<td><input type="reset" value="Reset" /></td>
				</tr>
			</table>
		</form:form>
		<p></p>
		<p><a href="${pageContext.servletContext.contextPath}">Home</a>
	</body>
</html>