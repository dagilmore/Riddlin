<%@ page session="false" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:template>
    <jsp:body>

    <ul>
		<c:forEach var="link" items="${links}">
	      <li><a href="<c:url value="${link}"/>">${link}</a></li>
		</c:forEach>
    </ul>
    
    </jsp:body>
</tags:template>