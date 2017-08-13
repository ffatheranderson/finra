<%--
  Created by IntelliJ IDEA.
  User: fa
  Date: 04.06.2017
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<%--<c:forEach var="kn" items="${requestScope.entrySet()}">--%>
<%--${kn.key} = ${kn.value}<br />--%>
<%--</c:forEach>--%>
<h3>Some error happened while your last request.</h3>
<div>Message: ${empty userMessage ? message : userMessage }</div>
<div>Status: ${empty userStatus? status : userStatus}</div>
<div>Error: ${empty userError ? error : userError}</div>
<div>You better try to go to main page <a href="/">Go to main page</a></div>

</body>
</html>
