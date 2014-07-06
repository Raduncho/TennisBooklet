<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Delete Existing Player</title>
	</head>
	<body>
		<form:form method="POST">
			<table>
				<tr>
					<td><form:label path="cardId">Card Id:</form:label></td>
					<td><form:input path="cardId" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Delete" /></td>
					<td><input type="reset" value="Reset" /></td>
				</tr>
			</table>
		</form:form>
		<p></p>
		<p><a href="${pageContext.servletContext.contextPath}">Home</a>
	</body>
</html>