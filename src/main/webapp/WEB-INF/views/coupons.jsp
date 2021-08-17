<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<style>

table {
	margin: 20px;
	justify-content: center;
	text-align: center;
	justify-self: center;
	background-color: white;
	}

table th {
	padding: 8px;
}

table tr:hover {
	background-color: aliceblue;
}

table tr {
	padding-top: 12px;
	padding-bottom: 12px;
}

</style>
<head>
	<meta charset="ISO-8859-1">
	<title>Coupons Page</title>
</head>
<body>

	<div>
		<a href="index">Back to homepage</a> 
	</div>

	<div>
		<h1>Welcome!</h1>
		<h3>Please enter the coupon ID to redeem a coupon.</h3>
	</div>

	<div>
		<c:set value='${sessionScope.message}' var="message" />
		<c:if test='${message != null}'>
			<p>
				<c:out value='${message}' />
			</p>
		</c:if>
	</div>

	<div>
		<form action="coupons" method="post">
			<input type="number" placeholder="Coupon ID" name="couponId" autocomplete="off" />
			<button type="submit">Submit</button>
		</form>
	</div>

	<div>
		<h3>List Of Your Coupons</h3>
		<form action="coupons" method="post">
		<table class="table">
			<tr>
				<th>Coupon Id</th>
				<th>Value</th>
				<th>Redemptions Left</th>
				<th>Redemption</th>
			</tr>
			<c:forEach items="${listOfCoupons}" var="coupon">
				<tr>
					<td>${coupon.couponId}</td>
					<td><fmt:formatNumber maxFractionDigits="5"
							minFractionDigits="2" value="${coupon.value}" /></td>
					<td>${coupon.numOfRedemptions}</td>
					<td><button type="submit" name="couponId" value="${coupon.couponId}">Redeem</button></td>
				</tr>
			</c:forEach>
		</table>
		</form>
	</div>

</body>
</html>