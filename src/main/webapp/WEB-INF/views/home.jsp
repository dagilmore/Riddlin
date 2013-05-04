<%@ include file="/WEB-INF/views/partials/header.jsp" %>

<c:if test="${not empty latestBlog}">
    latestBlog.test
</c:if>
<c:if test="${not empty message}">
   ${message.text}
</c:if>

	
</body>

</html>
