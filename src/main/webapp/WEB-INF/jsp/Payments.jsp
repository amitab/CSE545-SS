<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer Funds</title>
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
	<%@include file="HeaderPage.jsp"%>
	<div class="content-container">
		Fund from Account# : ${accountid}
		<form action="/paymentaction" method="post">
			<div>
				<label for="Recipient" class="lbel">Name of Recipient </label> <input
					id="Recipient" name="Recipient" type="text" class="texter" required>
			</div>
			<div>
				<label for="AccountNumber" class="lbel">Recipient Account# </label>
				<input id="AccountNumber" name="AccountNumber" type="text"
					class="texter" required>
			</div>
			<div>
				<label for="Amount" class="lbel">Amount</label> <input id="Amount"
					name="Amount" type="text" class="texter" required>
			</div>
			<input type="submit" value="Confirm"> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />

		</form>
	</div>
</body>
</html>