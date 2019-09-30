<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
		if (cid != null) {
	%>
	<div id="payment">
		<h1>Payment Page</h1>
		<form:form action="PaymentController" method="post" 
			modelAttribute="cardDetail">
			<table align="center">
				<tr>
					<td><form:label path="cardNumber">Enter Your cardNumber:</form:label></td>
					<td><form:input path="cardNumber" /></td>
					<td><form:errors path="cardNumber" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td><form:label path="cardName">Enter the Card Holder Name:</form:label></td>
					<td><form:input path="cardName" /></td>
					<td><form:errors path="cardName" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td>Choose your card type:</td>
					<td><form:select path="cardType">
							<form:option value="creditCard"> Credit Card</form:option>
							<form:option value="debitCard"> Debit Card</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="expiryDate">Enter Card Expiry date:</form:label></td>
					<td><form:input type="date" path="expiryDate" /></td>
					<td><form:errors path="expiryDate" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td><form:label path="CVV">Enter CVV Number:</form:label></td>
					<td><form:input path="CVV" /></td>
					<td><form:errors path="CVV" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Pay"></td>
					<td><input type="reset" name="Reset"></td>
				</tr>
			</table>
		</form:form>
		<%
			} else {
		%>
		Please Login before going to payment

		<%
			}
		%>

	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>