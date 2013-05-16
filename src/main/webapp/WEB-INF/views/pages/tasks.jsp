<%@ page session="false" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:template>
    <jsp:body>

    This is the tasks page

    <ul>
		<c:forEach var="task" items="${tasks}">
	      <li><a href="<c:url value="tasks/${task.id}"/>">${task.task}</a></li>
		</c:forEach>
    </ul>

    </jsp:body>
</tags:template>