<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	background-image: url("/resources/Images/bg4.jpeg");
	background-repeat: no-repeat;
	background-size: 100% 100%;
	width: 100%;
	height: 630px;
}

#registration {
	text-align: center;
	padding-top: 80px;
	font-family: cursive;
}
</style>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
</head>
<body>

	<div id="registration">
		<h1>Register Here</h1>
		<form:form action="RegistrationController" method="post"
			modelAttribute="customer">
			<table align="center">
				<tr>
					<td><form:label path="customerName">Enter Your Name:</form:label></td>
					<td><form:input path="customerName" /></td>
					<td><form:errors path="customerName" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td><form:label path="customerEmail">Enter Your Email:</form:label></td>
					<td><form:input path="customerEmail" /></td>
					<td><form:errors path="customerEmail" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td><form:label path="customerPassword">Choose Your Password:</form:label></td>
					<td><form:input path="customerPassword" /></td>
					<td><form:errors path="customerPassword" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td><form:label path="customerAddress">Enter Your Address:</form:label></td>
					<td><form:input path="customerAddress" /></td>
					<td><form:errors path="customerAddress" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td><form:label path="customerDOB">Enter Your DOB:</form:label></td>
					<td><form:input type="date" path="customerDOB" /></td>
					<td><form:errors path="customerDOB" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td><form:label path="customerContact">Enter Your Contact:</form:label></td>
					<td><form:input path="customerContact" /></td>
				
				</tr>

				<tr>
					<td><form:label path="customerCity">Enter Your City:</form:label></td>
					<td><form:input path="customerCity" /></td>
					<td><form:errors path="customerCity" cssStyle="color:#ff0000"/></td>
				</tr>
				<tr>
					<td><form:label path="customerState">Enter Your State:</form:label></td>
					<td><form:input path="customerState" /></td>
					<td><form:errors path="customerState" cssStyle="color:#ff0000"/></td>
				</tr>
			</table>

			<br>
			<input style="margin-left: 8%;" type="submit" value="Register">
			<br>
			<a href="LoginController" style="margin-left: 3%;">Already Having
				Account?Login In</a>
		</form:form>
	</div>
</body>
</html>