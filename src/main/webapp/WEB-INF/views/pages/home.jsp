<%@ include file="/WEB-INF/views/partials/header.jsp" %>

<%@ include file="/WEB-INF/views/partials/signinForm.jsp" %>

<c:if test="${not empty latestBlog}">
    ${latestBlog.title}
</c:if>
<c:if test="${not empty blogMessage}">
   ${blogMessage.text}
</c:if>

<c:if test="${not empty riddles}">
    ${riddles}
</c:if>
<c:if test="${not empty riddleMessage}">
   ${riddleMessage.text}
</c:if>



<%@ include file="/WEB-INF/views/partials/footer.jsp" %>