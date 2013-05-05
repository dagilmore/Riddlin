
<form action="#" th:action="@{/contact}" th:object="${contactForm}" method="post">
	<label class="control-label">Your Name *</label>
	<input type="text" th:field="*{name}" class="input-xxlarge" required="required"/>
	<label class="control-label">Email Address *</label>
	<input type="email" th:field="*{email}" class="input-xxlarge" required="required"/>
	<label class="control-label">Message *</label>
	<textarea th:field="*{message}" class="input-xxlarge"  rows="5" style="resize: none;"></textarea>
	<br />
	<input type="submit" name="submit" value="Submit" class="btn btn-primary" />
</form>
