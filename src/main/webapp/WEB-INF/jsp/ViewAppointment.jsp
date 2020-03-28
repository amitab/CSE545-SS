
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointments of the day</title>
<link rel="stylesheet" href="css/cssClassess.css" />
<script>
	function ieClicked() {
		if (document.all) {
			return false;
		}
	}
	function firefoxClicked(e) {
		if (document.layers || (document.getElementById && !document.all)) {
			if (e.which == 2 || e.which == 3) {
				return false;
			}
		}
	}
	if (document.layers) {
		document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown = firefoxClicked;
	} else {
		document.onmouseup = firefoxClicked;
		document.oncontextmenu = ieClicked;
	}
	document.oncontextmenu = new Function("return false")
	document.oncopy = new Function("return false")
	document.oncut = new Function("return false")
	document.onpaste = new Function("return false")
	document.onselectstart = new Function("return false")

	history.forward();
</script>
</head>
<body>
	<div class="content-wrapper">
		<c:choose>
			<c:when test="${role eq 'tier1'}"><%@include file="HPT1.jsp"%></c:when>
			<c:otherwise><%@include file="HPT2.jsp"%></c:otherwise>
		</c:choose>
	</div>
	<div class="content-container">

		<table>
			<thead>
				<tr>

					<th>Customer Name</th>
					<th>Issue</th>
				</tr>
			</thead>



			<tbody>
				<tr>
					<td>
						<p>${message}</p>
					</td>
				</tr>

				<c:forEach items="${appSearchForm.appSearchs}" var="search"
					varStatus="status">
					<tr>

						<td>${search.username}</td>
						<td>${search.status}</td>
				</c:forEach>

			</tbody>
		</table>

	</div>
</body>
</html>