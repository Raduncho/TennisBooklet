<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Success</title>
	</head>
	<body>
		<p>Match between ${gameWrapper.player1.name} and ${gameWrapper.player2.name} added to the database!</p>
		<p></p>
		<p><a href="${pageContext.servletContext.contextPath}">Home</a>
	</body>
</html>