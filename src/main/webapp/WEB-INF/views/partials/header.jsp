<!DOCTYPE html>

<html lang="en">
<head>

<%@ include file="/WEB-INF/views/partials/include.jsp" %>

<title>Riddler</title>
<meta charset="utf-8" />

</head>

<body>

<a href="<c:url value="/"/>">Home Anchor</a>
<div>
	<ul >
		<li><a href="<c:url value="/blogs"/>">Blog</a></li>
		<li><a href="<c:url value="/about"/>">About</a></li>
		<li><a href="<c:url value="/contact"/>">Contact</a></li>
		<li><a href="<c:url value="/signin"/>">Sign In</a></li>
	</ul>
</div>