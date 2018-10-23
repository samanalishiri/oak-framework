<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="app.title"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/script/css/font.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/script/css/public.css"/>"/>

</head>
<body>
<div style="width: 300px; margin: auto; text-align: center; padding: 20px; border: 1px black solid">
    <div>
        <strong>
            <h2>
                <span><spring:message code="jsp.login.title"/></span>
            </h2>
        </strong>
    </div>
    <form:form>
        <div class="form-group">
            <input id="username" name="username" type="text" class="form-control" placeholder="<spring:message code="jsp.login.input.place-holder.username"/>">
        </div>
        <div class="form-group">
            <input id="password" name="password" type="password" class="form-control" placeholder="<spring:message code="jsp.login.input.place-holder.password"/>">
        </div>
        <div class="form-group">
            <button type="submit" formmethod="post" formaction="<spring:url value="/security/login.action"/>"
                    class="btn btn-primary btn-block">
                <span><spring:message code="jsp.login.button"/></span>
            </button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>
</div>
</body>
</html>
<script type="text/javascript" src="<c:url value="/webjars/jquery/1.9.1/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"/>"></script>
