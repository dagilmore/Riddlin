<%@ page session="false" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:template>
    <jsp:body>

		<c:if test="${not empty riddle}">
			<%@ include file="/WEB-INF/views/partials/riddles/${riddle.type}.jsp" %>
		</c:if>
		<c:if test="${not empty riddleMessage}">
		   ${riddleMessage.text}
		</c:if>

		<c:if test="${not empty task}">
			Do this to get a clue
		</c:if>
		<c:if test="${not empty taskMessage}">
		   ${riddleMessage.text}
		</c:if>

    </jsp:body>
</tags:template>