<!DOCTYPE html>

<html lang="en">
<head>

<%@ include file="/WEB-INF/views/partials/include.jsp" %>

<title>Riddlin</title>
<meta charset="utf-8" />

</head>

<body>

<a href="<c:url value="/"/>">Home Anchor</a>
<div>
	<ul >
		<li><a href="<c:url value="/blog"/>">News</a></li>
		<li><a href="<c:url value="/about"/>">About</a></li>
		<li><a href="<c:url value="/sponsorHome"/>">My Riddles</a></li>
		<li><a href="<c:url value="/signin"/>">Sponsor!</a></li>
	</ul>
</div>