<%@ page session="false" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:template>
    <jsp:body>

<c:choose>  
    <c:when test="${not empty create}">  
		<%@ include file="/WEB-INF/views/partials/tasks/newTask.jsp" %>
    </c:when>  
    
    <c:otherwise>  
	    <br /> ${task}
		<br /> ${answer} 
    </c:otherwise>  
</c:choose>

    </jsp:body>
</tags:template>