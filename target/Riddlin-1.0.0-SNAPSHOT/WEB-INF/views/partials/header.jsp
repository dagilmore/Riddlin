<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%@ include file="/WEB-INF/views/partials/include.jsp" %>

<title>Riddlin</title>
</head>
<body>

<div id="header">
	
    
    <h1>
    	<a href="<c:url value="/"/>"><img src="<c:url value="/resources/images/riddlin.png"/>"></img></a>
    	<a href="<c:url value="/"/>">Riddl.in</a>
    </h1>
    
	<div class="centerDiv">
		<ul class="centerUL">
			<li><a href="<c:url value="/riddles"/>">Riddles</a></li>
			<li><a href="<c:url value="/about"/>">About</a></li>
			<li><a href="<c:url value="/signup"/>">Advertise</a></li>
		</ul>
	</div>
    

</div>  

<c:choose>
  <c:when test="${userAccount != null}">
  	Welcome, ${userAccount.displayName}
  </c:when>
  <c:otherwise>
  	<%@ include file="/WEB-INF/views/partials/signinForm.jsp" %> 
  </c:otherwise>
</c:choose>
