<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br />

${riddle}

<br />

<form name="nextClue" action="<c:url value="americanIdioms" />" method="POST">
   <input type="text" name="character"/>
   <input type="submit" value="OK" />
</form>

<br />