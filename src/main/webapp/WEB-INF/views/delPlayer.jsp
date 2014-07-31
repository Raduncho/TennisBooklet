<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Delete Existing Player</title>
		<style>
			.error {
				color: red;
				font-weight: bold;
			}
		</style>
	</head>
	<body>
		<form:form method="POST" modelAttribute="player">
			<table>
				<tr>
					<td><form:label path="name">Name:</form:label></td>
					<td><form:input path="name" /></td>
					<td><form:errors path = "name" cssClass="error"/></td>
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