<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="HPM.jsp"%>

<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script src="js/EmpValidation.js"></script>
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

<div id="page-content" align="center" class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">New Password</h3>
		</div>
		<div class="panel-body">
			<form:form id="NewPassword" class="form-horizontal"
				action="/profile/change_password" method="post"
				modelAttribute="passwordForm">
				<fieldset>
					<div>
						<p>${message}</p>
					</div>
					<form:errors path="isValid" cssClass="error" />

					<div class="form-group">
						<label for="oldpassword" class="col-lg-2 control-label">Old
							Password</label>
						<div class="col-lg-5">
							<form:password cssClass="form-control" path="oldpassword"
								id="oldpassword" name="oldpassword" placeholder="Old Password" />
							<form:errors path="oldpassword" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label for="password" class="col-lg-2 control-label">New
							Password</label>
						<div class="col-lg-5">
							<form:password cssClass="form-control" path="password"
								id="password" name="password" placeholder="New Password"
								required="true" />
							<form:errors path="password" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label for="confirmpassword" class="col-lg-2 control-label">Confirm
							Password</label>
						<div class="col-lg-5">
							<form:password cssClass="form-control" path="confirmpassword"
								id="confirmpassword" name="confirmpassword"
								placeholder="Confirm Password" required="true" />
							<form:errors path="confirmpassword" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-7 col-lg-offset-2">
							<button id="new_password" name="action" value="reset_password">Change
								Password</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
					</div>

				</fieldset>
			</form:form>
		</div>
	</div>
</div>
</body>

</html>