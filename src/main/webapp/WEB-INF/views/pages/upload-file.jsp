<%--
  Created by IntelliJ IDEA.
  User: fa
  Date: 30.06.2017
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
    .label {
        color: black;
        font-size: 19px;
    }
</style>

<div>
    <div style="height: 30px;" class="row padding"></div>
    <div class="row">
        <div class="col-sm-12">
            <h3 class="textOnGalaxy">&nbsp; File upload form.</h3>
        </div>
    </div>

    <form method="POST" action="/files/" enctype="multipart/form-data">
        <div class="row">
            <div class="col-sm-12">
                <div class="delimiter"></div>
                <div class="row item">
                    <div class="col-sm-3 label">
                        Name
                    </div>
                    <div class="col-sm-3">
                        <input name="name" class="name reloadTableOnEnter" type="text"/>
                    </div>
                    <div class="col-sm-3 label">
                        Some Property
                    </div>
                    <div class="col-sm-3">
                        <input name="someProperty" type="text"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3 label">
                        File to upload
                    </div>
                    <div class="col-sm-3">
                        <input name="file" type="file"/>
                    </div>
                </div>
                <c:if test="${not empty error}">
                    <div id="errorMessage" class="row red-text">
                        <div class="col-sm-12" style="font-size: 20px;">
                                ${error}
                        </div>
                    </div>
                </c:if>
                <div class="delimiter"></div>
                <div class="row buttons-bar">
                    <div class="col-sm-6">
                    </div>
                    <div class="col-sm-3"></div>
                    <div class="col-sm-3">
                        <input style="width: 170px;" type="submit" value="Upload"/>
                    </div>
                </div>
            </div>
        </div>
        <sec:csrfInput/>
    </form>

</div>