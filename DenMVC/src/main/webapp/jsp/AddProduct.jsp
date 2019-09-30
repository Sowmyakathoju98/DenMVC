<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
<link href="<c:url value="/resources/css/Den.css" />" rel="stylesheet">
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
		<nav> <a href="AdminController">Home</a> <a
			href="LogOutControllerAdmin">LogOut</a> </nav>
	</div>
<div align="center">
	<form:form action="AddProductController" method="post"
		modelAttribute="productDetail">
		<table>
			<tr>
				<td><form:label path="productName">Enter Product Name:</form:label></td>
				<td><form:input path="productName" /></td>
			</tr>
			<tr>
				<td><form:label path="productImage">Choose Product Name:</form:label></td>
				<td><form:input path="productImage" type="file" /></td>
			
			</tr>
			<tr>
				<td>Choose the Category</td>
				<td><form:select path="categoryId">
						<form:options items="${cat}"></form:options>
					</form:select>
				<td>
			</tr>
			<tr>
				<td><form:label path="productPrice">Enter Product Price:</form:label></td>
				<td><form:input path="productPrice" type="text" /></td>
				
			</tr>
			<tr>
				<td><form:label path="productStock">Enter Product Quantity:</form:label></td>
				<td><form:input path="productStock" type="number" /></td>
				
			</tr>
			<tr>
				<td><form:label path="productDescription">Enter Product Description:</form:label></td>
				<td><form:input path="productDescription" type="text" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Product"></td>
				
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>