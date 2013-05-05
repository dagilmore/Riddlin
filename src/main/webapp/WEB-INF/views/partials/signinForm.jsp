<div id="socialLogin">
	<form action="<c:url value="/signin/google" />" method="POST">
		<input type="hidden" name="_spring_security_remember_me" value="true" />
		<button type="submit">  Sign in with Google</button>
	</form>
	
	<form action="<c:url value="/signin/twitter" />" method="POST">
		<button type="submit">  Sign in with Twitter</button>
	</form>
	<form action="<c:url value="/signin/facebook" />" method="POST">
		<button type="submit">  Sign in with Facebook</button>
	</form>
</div>
