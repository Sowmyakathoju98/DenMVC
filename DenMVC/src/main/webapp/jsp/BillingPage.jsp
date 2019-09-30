<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="Header.jsp"%>
	<h1>Billing Information</h1>
	<div style="text-align: center;">
		<% String customerName=(String)session.getAttribute("name") ;
	 String productName=(String)session.getAttribute("productName");
	Object TotalAmount=session.getAttribute("productTotalPrice");
	Object quantity=session.getAttribute("quantity");
	Object productPrice=session.getAttribute("productPrice");
	
%>
		CustomerName=<%=customerName%><br> <br> ProductName=<%=productName%><br>
		<br> Cost of Product=<%=productPrice %><br> <br>
		Quantity Ordered=<%=quantity %><br> <br> TotalPrice=<%=TotalAmount %><br>
		<br>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>