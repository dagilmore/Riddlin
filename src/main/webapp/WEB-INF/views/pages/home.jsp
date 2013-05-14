<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">  
    <%@ include file="/WEB-INF/views/pages/riddles.jsp" %> 
</sec:authorize>
<sec:authorize access="!isAuthenticated()"> 
    <%@ include file="/WEB-INF/views/pages/signin.jsp" %> 
</sec:authorize>

