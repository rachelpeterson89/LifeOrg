<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome <c:out value="${user.firstName} ${user.lastName}" /></title>
</head>
<body>
	<div>
		<h1>
			Welcome,
			<c:out value="${user.firstName} ${user.lastName}" />
		</h1>
		<p>
			<a href="/logout">Click Here To Log Out</a>
		</p>
	</div>
	<div>
		<p>
			Go to your To Do Lists-> <a href="/todo">Click Here</a>
		</p>
	</div>

</body>
</html>