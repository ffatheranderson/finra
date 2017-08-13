<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><tiles:getAsString name="title"/></title>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <script src="/static/DataTables/datatables.min.js"></script>
    <%--<link rel="stylesheet" type="text/css" href="/static/DataTables/datatables.min.css"/>--%>
    <link rel="stylesheet" type="text/css" href="/static/DataTables/DataTables-1.10.15/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" href="/static/css/main.css"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script src="/static/js/jquery.mask.js"></script>
    <script src="/static/js/main.js"></script>
</head>

<body>
<script>
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
</script>

<div class="container">
    <%--<header id="header">--%>
    <%--<tiles:insertAttribute name="header"/>--%>
    <%--</header>--%>

    <section id="site-content">
        <tiles:insertAttribute name="body"/>
    </section>

    <footer id="footer">
        <tiles:insertAttribute name="footer"/>
    </footer>
</div>
</body>
</html>