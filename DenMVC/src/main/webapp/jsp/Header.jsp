<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
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
			Hello!!<%=name%>
		</h4>

		<%
			} else {
		%>
		<h4>Hello User!!Please Login</h4>
		<%
			}
		%>
	</div>
	<div class="subHeader">
		<nav>
			<a href="HomePageController">Home</a>
			<%
				if (cid != null) {
			%>
			<a href="LogOutController">LogOut</a>
			<%
				} else {
			%>
			<a href="LoginController">Login</a> <a href="RegistrationController">SignUp</a>
			<%
				}
			%>
			<a href="FAQ">FAQ</a><a href="Help">Help</a>
		</nav>
	</div>

</body>
</html>