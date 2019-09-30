<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/Den.css" />" rel="stylesheet">
<%-- <script src="<c:url value="/resources/css/bootstrap.min.js" />"></script> --%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<script>
	function mycart() {
		alert("Added Successfully");
	}
</script>
</head>
<body style="background-color: wheat;">
	<div class="Container">
		<div class="header" id="top">
			<h1>Shoppers Den</h1>
			<h3>Find it,Love it,Buy it</h3>
			<!-- <img src="/DenFirst/Images/b1.jpg">-->
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
		<div class="subHeader" style="padding: 10px;">
			<nav>
				<a id="aa" href="HomePageController">Home</a>
				<%
					if (cid != null) {
				%>
				<a href="LogOutController">LogOut</a> <a href="MyCartController">My
					Cart</a>
				<%
					} else {
				%>
				<a href="LoginController">Login</a> <a href="RegistrationController">SignUp</a>
				<%
					}
				%>
				<a href="FAQ">FAQ</a> <a href="Help">Help</a>
			</nav>
		</div>
		<div>
			<form action="SearchController" method="post"
				style="margin-top: 10px; padding: 10px; text-align: center;">
				<input type="search" placeholder="Search Product here"
					name="searchText" class="searchtext" required="required"> <input
					type="submit" value="Search" name="search" class="searchButton">
			</form>
		</div>

		<div id="products" style="padding-top: 20px">
			<%
				Object list = request.getAttribute("list");
				if (list == null) {
			%>
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<img src='<c:url value="/resources/Images/front2.jpg" />'
							alt="Los Angeles" style="width: 100%;">
					</div>

					<div class="item">
						<img src='<c:url value="/resources/Images/s12.jfif" />'
							alt="Chicago" style="width: 100%;">
					</div>

					<div class="item">
						<img src='<c:url value="/resources/Images/front8.jpg" />'
							alt="New york" style="width: 100%;">
					</div>
				</div>

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
			<h1 style="padding: 10px;">PRODUCT CATALOG</h1>
			<c:forEach items="${ pList }" var="product">
				<form action="BuyNowController/${product.productId }" method="post">
					<div class="col-md-4" style="padding: 1px;">
						<div
							style="display: inline-block; background-color: white; border: solid 1px #808080; padding: 70px; margin-left: 50px;">
							<div style="margin-left: 40px;">
								<input type="hidden" value="${product.productImage}"
									name="productImage"> <img class="img-responsive"
									alt="eCommerce Product List"
									src='<c:url value="/resources/Images/${product.productImage}" />' />
								<br />
								<h2 class="pull-right">
									<input type="hidden" value="${product.productPrice}"
										name=productPrice>${product.productPrice}</h2>
								<h2>
									<input type="hidden" value="${product.productName}"
										name=productName>${product.productName}
								</h2>
								<br />
								<p class="text-justify">
									<input type="hidden" value="${product.productDescription}"
										name="productDescription">${product.productDescription}</p>
							</div>
							<br />
							<div class="btn-ground text-center" style="padding-bottom: 30px">
								<a href="AddToCartController/${product.productId}"
									class="btn btn-primary"> Add to Cart </a> <input type="submit"
									class="btn btn-primary" data-toggle="modal"
									data-target="#productmodal1" value="Buy Now" />
							</div>
						</div>
					</div>
				</form>
			</c:forEach>
			<%
				} else {
			%>
			<h1 style="padding: 10px;">SEARCHED CATEGORY</h1>
			<c:forEach items="${list }" var="product">
				<form action="BuyNowController/${product.productId }" method="post">
					<div class="col-md-4" style="padding: 50px;">
						<div
							style="display: inline-block; border: solid 1px #808080; padding: 100px">
							<div>
								<input style="padding: 10px; width: 100px;" type="hidden"
									value="${product.productImage}" name="productImage"> <img
									class="img-responsive" alt="eCommerce Product List"
									src='<c:url value="/resources/Images/${product.productImage}" />' />
								<br />
								<h2 class="pull-right">
									<input type="hidden" value="${product.productPrice}"
										name=productPrice>${product.productPrice}</h2>
								<h2>
									<input type="hidden" value="${product.productName}"
										name=productName>${product.productName}
								</h2>
								<br />
								<p class="text-justify">
									<input type="hidden" value="${product.productDescription}"
										name="productDescription">${product.productDescription}</p>
							</div>
							<br />
							<div class="btn-ground text-center" style="padding-bottom: 30px">
								<a href="user/AddToCartController/${product.productId}"
									onclick="return myCart()" class="btn btn-primary"> Add to
									Cart </a> <input type="submit" class="btn btn-primary"
									data-toggle="modal" data-target="#productmodal1"
									value="Buy Now" />
							</div>
						</div>
					</div>
				</form>
			</c:forEach>
			<%
				}
			%>
			</table>
		</div>
	</div>
	<!-- <div id="subFooterContainer" >
	<div id="subFooter">
		<a href="#top">Back to Top</a>
	</div>
	<div id="footer">
		<p>copyrights@ShoppersDen.com
		<p>
			<a href="AboutUs">About Us</a>
		<p>Contact Us:9133278823</p>
		mail us:<a href="" style="padding-left: 0%;">virtusa2k19@gmail.com</a>
	</div>
	</div> -->
</body>
</html>