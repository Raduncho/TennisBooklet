<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Success</title>
	</head>
	<body>
		<p>Player ${player.name} has been added to the database with card id ${player.cardId}!</p>
		<p></p>
		<p><a href="${pageContext.servletContext.contextPath}">Home</a>
	</body>
</html>