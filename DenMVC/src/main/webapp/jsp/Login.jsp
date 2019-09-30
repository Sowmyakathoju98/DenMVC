 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	background-image: url("/webapp/Images/bg4.jpeg");
	background-repeat: no-repeat;
	background-size: 100% 100%;
	width: 100%;
	height: 630px;
}

.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login {
	text-align: center;
	font-family: cursive;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="Header.jsp" %>
	<div id="login">
		<form:form name='loginForm' action="LoginController" method="post" modelAttribute="login">
			<h1>Login with Username and Password</h1>

			<c:if test="${msg}">
				<div class="error">${msg}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<table align="center">
				<tr>
					<td><form:label path="username">Enter Your Email:</form:label></td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td><form:label path="password">Choose Your Password:</form:label></td>
					<td><form:input path="password" type="password" /></td>
				</tr>
			</table>
			<br>
			<input type="submit" name="submit" value="Login">
			<br>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			</td>

			<!-- <input type="_csrf" type="hidden" value="b1b37704-987e-439c-9004-89b47321863e" /> -->
			<br>
			<a href="RegistrationController">New User?Sign UP</a>
		</form:form>
	</div>
</body>
</html>
<!-- minlength="8" required="required" placeholder="min 8 characters"
		pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" --> --%>