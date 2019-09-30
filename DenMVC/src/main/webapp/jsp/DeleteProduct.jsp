<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/Den.css" />" rel="stylesheet">
<script>
	function mycart() {
		alert("Deleted Successfully");
	}
</script>
</head>
<body>
	<div class="header" id="top">
		<h1>Shoppers Den</h1>
		<h3>Find it,Love it,Buy it</h3>
		<!-- <img src="/ShoppersDen/Images/b1.jpg">-->
		<%
			Object cid = session.getAttribute("cid");
			if (cid != null) {
				String name = (String) session.getAttribute("name");
		%>
		<h4>
			Hello!!<%=cid%>
		</h4>

		<%
			}
		%>
	</div>
	<div class="subHeader">
		<nav> <a href="http://localhost:8089/DenMVC/Admin/AdminController">Home</a> <a
			href="http://localhost:8089/DenMVC/Admin/LogOutControllerAdmin">LogOut</a> </nav>
	</div>
<div id="deleteProductContainer" style="align-content: center;">
	<h1>List Of Categories</h1>

<div  align="center"  style="padding: 20px;">
	<form action="DeleteProductController" method="post" >
		Choose Category : <select name="productCategory">
			<c:forEach var="l" items="${requestScope.CategoryDetail}">
				<option value="${l.categoryId}">${l.categoryName }</option>
			</c:forEach>
		</select> <input type="submit" value="Show Products">
	</form>
	</div>
	<table border="2" align="center">
		<tr>
			<td>ProductName</td>
			<td>Product Image</td>
			<td>Action</td>
		</tr>

		<c:forEach items="${ProductDetail}" var="product">
			<tr>
				<td>${product.productName}</td>
				<td><img src="/DenMVC/resources/Images/${product.productImage}"></td>
				<td><a
					href="DeleteProductController/${product.productId}/${product.categoryId}">Delete
						Product</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>