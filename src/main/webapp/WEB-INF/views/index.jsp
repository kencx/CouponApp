<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<div>
	<h1>Welcome to Online Coupon Redemption.</h1>
	</div>

	<div>
		<h3>Please enter your User ID to begin.</h3>
		
		<c:set value='${message}' var="message" />
		<c:if test='${message != null}'>
			<p style="color: red;">
				<c:out value='${message}' />
			</p>
		</c:if>
		
		<form action="index" method="post">
			<input type="number" placeholder="User ID" name="userId" required autocomplete="off" />
			<button type="submit">Submit</button>
		</form>
	</div>

</body>
</html>