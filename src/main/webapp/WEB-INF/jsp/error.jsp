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
</head>
<body>
	<h1>Something went wrong!</h1>
	<h2>Our Engineers are on it</h2>
	<a href="/login">Go Home</a>
</body>
</html>