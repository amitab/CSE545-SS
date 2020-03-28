
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="ISO-8859-1">
<title>Tier2 HomePage</title>
</head>
<body>
	<%@include file="HPT2.jsp"%>
	<form id="Tier3home" method="post">
		<table align="center">
			<tr>
				<td>
					<h2>TIER-2 HOME</h2> <input type="hidden"
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
