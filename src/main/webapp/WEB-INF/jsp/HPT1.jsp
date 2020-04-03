<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="forceEnable.jsp"%>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/cssClassess.css" />
<script src="/js/security.js"></script>
</head>
<body>
	<div class="content-container">
		<div class="banner-container">
			<header role="banner">
				<nav role="navigation">
					<ul class="top-bar">
						<li class="cta"><a class="ButtonDesign"
							href="/Tier1Dashboard">Home</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier1PendingTransactions">Approve/Decline Transaction</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier1CreateTransaction">Create Transaction</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier1IssueCheque">Issue Cashiers Cheque</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier1DepositCheque">Deposit Cashiers Cheque</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier1DepositMoney">Deposit Money</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier1WithdrawMoney">Withdraw Money</a></li>
						<li class="cta"><a class="ButtonDesign"
							href="/Tier1ViewAccounts">View Customer Accounts</li>
						<li class="ctd"><a class="ButtonDesign"
							href="/profile/change_password">Change Password</a></li>
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
