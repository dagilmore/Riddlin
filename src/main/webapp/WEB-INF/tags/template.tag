<!-- Tag Libraries -->
<%@taglib prefix="tags" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Riddlin</title>


<!-- Include Stlyes-->
<link rel='stylesheet' type='text/css' media='screen' href='<c:url value="/resources/css/styles.css"/>'>
<link rel='stylesheet' type='text/css' media='screen' href='<c:url value="/resources/css/zocial.css"/>'>

<!-- Include Google Web Fonts -->
<link href='http://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>

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
			<li><a href="<c:url value="/prizes"/>">Prizes</a></li>
			<li><a href="<c:url value="/sponsor"/>">Sponsor</a></li>
			<li><a href="<c:url value="/about"/>">About</a></li>

		</ul>
	</div>
    

</div>  
 
<sec:authorize access="isAuthenticated()"> 
  Welcome, 
  <a href="<c:url value="/signout"/>">Logout</a>
</sec:authorize>
	<div id="content">
	
		<jsp:doBody/>
	
	</div>
	<div id="footer"></div>
</body>
</html>


