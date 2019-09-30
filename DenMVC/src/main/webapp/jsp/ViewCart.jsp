<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<%@ include file="Header.jsp"%>
	<table align="center" style="text-align: center" cellpadding="5"
		cellspacing="5" border="1">
		<tr>
			<td>PRODUCTIMAGE</td>
			<td>PRODUCT NAME</td>
			<td>PRODUCT PRICE</td>
			<td>CART DATE</td>
			<td>DELETE ITEM</td>
			<td>BUY NOW</td>
		</tr>
		<c:forEach items="${ sessionScope.CartList }" var="cart">
			<form action="BuyNowController" method="post">
			<tr>
				<input type="hidden" value="${cart.product.productId}"
					name="productId">
				<input type="hidden" value="${cart.product.productDescription}"
					name="productDescription">
				<td><input type="hidden" value="${cart.product.productImage}"
					name="productImage"><img
					src="/DenMVC/resources/Images/${cart.product.productImage }"></td>
				<td><input type="hidden" value="${cart.product.productName}"
					name="productName">${cart.product.productName }</td>
				<td><input type="hidden" value="${cart.product.productPrice}"
					name="productPrice">${cart.product.productPrice }</td>
				<td>${cart.cartDate}</td>
				<td><a href="DeleteCartItemController/${cart.cartId}"
					onclick="return MyCart()">Delete Item</a></td>
				<td><input type="submit" name="buynow" value="Buy now"></td>
			</tr>
			</form>
		</c:forEach>
		
	</table>
	<%@ include file="Footer.jsp"%>
</body>
</html>