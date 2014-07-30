<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Add New Player</title>
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
					<c:if test="${not empty player.name}">
						<td><font color="ff0000" style="font-weight: bold" >Player with name "${player.name}" already exists!</font></td>
					</c:if>
					<td><form:errors path = "name" cssClass="error"/></td>
				</tr>
				<tr>
					<td><form:label path="rank">Rank:</form:label></td>
					<td><form:input path="rank" /></td>
					<td><form:errors path = "rank" cssClass="error"/></td>			
				</tr>
				<tr>
					<td><form:label path="age">Age:</form:label></td>
					<td><form:input path="age" /></td>
					<td><form:errors path = "age" cssClass="error"/></td>
				</tr>
				<tr>
					<td><form:label path="country">Country:</form:label></td>
					<td><form:input path="country" /></td>
					<td><form:errors path = "country" cssClass="error"/></td>
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