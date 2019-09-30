<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			href="../LogOutController">LogOut</a> </nav>
	</div>
	</div>


	<form action="AddCategoryController" method="post">
		<h1>List Of Categories</h1>
		<ul>
			<c:forEach items="${CategoryDetail}" var="CategoryDetail">
				<li>${CategoryDetail.categoryName}</li>
			</c:forEach>
		</ul>
		<table>
			<tr>
				<td>Enter Category Name:</td>
				<td><input type="text" name="categoryName" required="required"></td>
			</tr>
		</table>
		<input type="submit" value="Add Category">
	</form>
</body>
</html>