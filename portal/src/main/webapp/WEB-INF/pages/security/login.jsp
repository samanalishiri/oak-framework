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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/font.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/public.css"/>"/>

</head>
<body>
<div class="container center col-lg-12">
    <form:form>
        <table>
            <caption><spring:message code="jsp.login.title"/></caption>
            <tbody>
            <tr>
                <td><label><spring:message code="jsp.login.label.username"/></label></td>
                <td><input type="text" name="username" id="username" size="20" class="input-lg"></td>
            </tr>
            <tr>
                <td><label><spring:message code="jsp.login.label.password"/>:</label></td>
                <td><input type="password" name="password" id="password" size="20" class="input-lg"></td>
            </tr>
            <tr>
                <td colspan="2" class="center">
                    <button type="submit" formmethod="post" formaction="<spring:url value="/security/login.action"/>" class="btn btn-default">
                        <span><spring:message code="jsp.login.button"/></span>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>
</div>
<script type="text/javascript" src="<c:url value="/webjars/jquery/1.9.1/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"/>"></script>
</body>
</html>
