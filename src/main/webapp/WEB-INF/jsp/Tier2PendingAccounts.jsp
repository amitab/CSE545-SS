
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Approve/Decline Account Request</title>
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
	<%@include file="HPT2.jsp"%>
	<div class="content-container">

		<table>
			<thead>
				<tr>

					<th>Account ID</th>
					<th>Balance</th>
					<th>Status</th>
				</tr>
			</thead>



			<tbody>
				<tr>
					<td>
						<p>${message}</p>
					</td>
				</tr>

				<c:forEach items="${searchForm.searchs}" var="search"
					varStatus="status">

					<tr>

						<td>${search.accountNumber}</td>
						<td>${search.balance}</td>
						<td>Pending</td>
						<td>
							<form method="post" action="/Tier2/AuthAcc" id="authorize">
								<input type="hidden" name="accountnumber" id="accountnumber"
									value="${search.accountNumber}"> <input type="hidden"
									name="${_csrf.parameterName}" value="${_csrf.token}" /> <input
									type="submit" value="Authorize">
							</form>
						</td>

						<td>
							<form method="post" action="/Tier2/DecAcc" id="decline">
								<input type="hidden" name="accountnumber" id="accountnumber"
									value="${search.accountNumber}"> <input type="hidden"
									name="${_csrf.parameterName}" value="${_csrf.token}" /> <input
									type="submit" value="Decline">
							</form>
						</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>

	</div>
</body>
</html>