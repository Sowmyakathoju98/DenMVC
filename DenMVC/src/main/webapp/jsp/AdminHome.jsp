<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<nav>

			<a href="AdminController">Home</a> <a href="LogOutControllerAdmin">LogOut</a>

		</nav>
	</div>
	<table align="center">
		<tr>
			<td colspan="5" style="padding: 20px;"><button>
					<a href="AddCategoryController" style="padding: 40px;font-size: 20px;">Add Category</a>
				</button></td>
		</tr>
		<tr>
			<td colspan="5" style="padding: 20px;"><button>
					<a href="AddProductController" style="padding: 40px;font-size: 20px;">Add Product</a>
				</button></td>
		</tr>
		<tr>
			<td colspan="5" style="padding: 20px;"><button>
					<a href="DeleteCategoryController" style="padding: 40px;font-size: 20px;">Delete Category</a>
				</button></td>
		</tr>
		<tr>
			<td colspan="5" style="padding: 20px;"><button>
					<a href="DeleteProductController" style="padding: 40px;font-size: 20px;">Delete Product</a>
				</button></td>
		</tr>

	</table>
</body>
</html>