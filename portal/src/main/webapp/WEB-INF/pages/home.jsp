<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="${homeContent.title}"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/script/css/font.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/script/css/public.css"/>"/>
</head>
<body>
<form:form>
    <div class="width-full left">
        <button type="submit" formmethod="post" formaction="<spring:url value="/security/logout.action"/>" class="btn btn-danger">
            <span><spring:message code="jsp.home.button.logout"/></span>
        </button>
    </div>
    <div>
        <jsp:include page="${homeContent.url}"/>
    </div>
</form:form>
<script type="text/javascript" src="<c:url value="/webjars/jquery/1.9.1/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"/>"></script>
</body>
</html>