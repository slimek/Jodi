<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h2>Pages</h2>
<ul>
	<li>
		<a href="hello">Hello</a>
	</li>
</ul>
<h2>Welcome Image</h2>
<img src="resources/coffee-book.jpg" width="600px" />
</body>
</html>
