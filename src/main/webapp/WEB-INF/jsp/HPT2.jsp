<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
	<div class="content-container">
		<div class="banner-container">
			<header role="banner">
				<nav role="navigation">
					<ul class="top-bar">
						<li class="cta"><a class="ButtonDesign"
							href="/Tier2/Dashboard">Home</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier2PendingTransaction">Approve/Decline Transactions</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier2/UpdatePassword">Change Password</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier2/PendingAccounts">Account Approval</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier2/SearchAccount">Search Customer Account</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier2/DeleteAccount">Delete Customer Account</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/ViewAppointments">View Appointments of the day</a></li>
						<form method="post" action="/perform_logout" id="form-logout">
							<input type="submit" value="Logout" />
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</ul>
				</nav>
			</header>
		</div>
	</div>
</body>
</html>
