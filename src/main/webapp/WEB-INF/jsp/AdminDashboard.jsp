<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="forceEnable.jsp"%>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
<script src="/js/security.js"></script>
</head>
<body>
	<%@include file="HPT3.jsp"%>
	<form id="Tier3home" method="post">
		<table align="center">
			<tr>
				<td>
					<h2>Admin Dashboard</h2> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
				</td>
			</tr>
			<tr>
				<td>${message}</td>
			</tr>
		</table>
	</form>
</body>
</html>
