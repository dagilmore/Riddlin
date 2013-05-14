<%@ page session="false" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tags:template>
    <jsp:body>

	<%@ include file="/WEB-INF/views/partials/signinForm.jsp" %>

    </jsp:body>
</tags:template>