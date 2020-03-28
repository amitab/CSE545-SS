<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/virtual-keyboard/1.30.1/js/jquery.keyboard.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-mousewheel/3.1.13/jquery.mousewheel.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/virtual-keyboard/1.30.1/js/jquery.keyboard.extension-typing.min.js">
	
</script>

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

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/virtual-keyboard/1.30.1/css/keyboard.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
</head>
<body>
	<div id="page-content" class="col-md-12" align="center">
		<div>

			<div>
				<h3>
					<b>Forgot Password</b>
				</h3>
			</div>
			<div class="form-group">
				<p>${message}</p>
			</div>

			<div>

				<form id="ForgotPassword" action="/forgot_password" method="post">
					<fieldset>
						<div>
							<label for="username" class="col-lg-2 control-label">User's
								name</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" id="username"
									name="username" placeholder="User's Name" required>
							</div>
						</div>

						<div>
							<label for="email">Email</label><br>
							<div>
								<input type="radio" id="email" name="mode" value="1">
							</div>
						</div>
						<div>
							<label for="sms">SMS</label><br>
							<div>
								<input type="radio" id="sms" name="mode" value="0">
							</div>
						</div>
			</div>

			<div>
				<div class="col-lg-7 col-lg-offset-2">
					<button id="forgot_password" name="action" value="forgot_password">Request
						OTP</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</div>
			</div>
			</fieldset>
			</form>
		</div>

		<div class="panel-body">
			<form id="ResetPassword" action="/reset_password" method="post">
				<fieldset>

					<div>
						<label for="otp" class="col-lg-2 control-label">OTP</label>
						<div class="col-lg-5">
							<input type="number" class="form-control input-lg form-control"
								id="token" name="token" placeholder="OTP" step="1" max="1000000"
								autofocus="true" required>
						</div>
					</div>

					<div>
						<div class="col-lg-7 col-lg-offset-2">
							<button id="reset_password" name="action" value="reset_password"
								class="btn btn-lg btn-primary btn-block">Change
								Password</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
					</div>

				</fieldset>
			</form>
		</div>

	</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#reset_password").click(function(event) {
				event.preventDefault();
				var data = 'token =' + $("#token").val();
				$.ajax({
					url : "/reset_password",
					type : "GET",
					dataType : 'text',
					data : data,
					timeout : 600000,
					cache : false,
					success : function(response) {
						//alert(response);
						alert("If OTP is valid, password will be changed.")
						$("#ResetPassword").submit();
					},
					error : function(xhr, status, error) {
						//alert(xhr.responseText);
						alert("If OTP is valid, password will be changed.")
						return false;
					}
				});
			});
		});
		$('#token').keyboard({
			layout : 'num',
			preventPaste : true,
			restrictInput : true,
			autoAccept : true
		}).addTyping();
	</script>

</body>
</html>
