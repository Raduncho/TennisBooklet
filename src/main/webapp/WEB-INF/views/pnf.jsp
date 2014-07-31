<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Error</title>
	</head>
	<body>
		<p>Player ${player.name} cannot be found in the database!</p>
		<p></p>
		<p><a href="${pageContext.servletContext.contextPath}">Home</a>
	</body>
</html>