<%@page import="org.springframework.beans.factory.annotation.Required"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="DoBuyNowController/${product.productId}"
		method="post">
		<h2>The Following product is the one you Ordered</h2>
		<input type="hidden" value="${product.productId }"><br>
		<table border="2" align="center">
			<tr>
				<td>ProductImage</td>
				<td>ProductName</td>
				<td>ProductPrice</td>
				<td>ProductDescription</td>
				<td>Quantity</td>
				<td>Buy Now</td>
			</tr>
			<tr>
				<td><img
					src="/DenMVC/resources/Images/${product.productImage }"></td>
				<td><input type="hidden" value="${product.productName }"
					name="productName">${product.productName }</td>
				<td><input type="hidden"
					value="${requestScope.product.productPrice }" name="productPrice">${product.productPrice }</td>
				<td><input type="hidden" value="${product.productPrice }"
					name="productDescription">${product.productDescription }</td>
				<td>Please Enter The Quantity:<br> <input type="text" 
					name="quantity" required="required"></td>
				<td><input type="submit" name="BuyNow"
					value="Proceed To Payment"></td>
			</tr>
		</table>

	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>