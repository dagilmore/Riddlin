<div id="soialWrap">
	Login...
<div id="socialLogin">

	<form name="googleForm" action="<c:url value="/signin/google" />" method="POST">
		<input type="hidden" name="_spring_security_remember_me" value="true" />
	</form>
	
	<a href="javascript:document.googleForm.submit()" class="zocial icon googleplus">Sign in with Google</a>

	<form name="twitterForm" action="<c:url value="/signin/twitter" />" method="POST">
		<input type="hidden" name="_spring_security_remember_me" value="true" />
	</form>

	<a href="javascript:document.twitterForm.submit()" class="zocial icon twitter">Sign in with Twitter</a>
	
	<form name="facebookForm" action="<c:url value="/signin/facebook" />" method="POST">
		<input type="hidden" name="_spring_security_remember_me" value="true" />
	</form>

	<a href="javascript:document.facebookForm.submit()" class="zocial icon facebook">Sign in with Facebook</a>
	
</div>
</div>
