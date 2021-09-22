<%@include file="taglib.jspf"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <title><fmt:message key="logout_success"/></title>
</head>
<%@include file="header.jspf"%>
<body>
<div align="center">
    <br>
        <h2>
            <a href="index.jsp" class="nav-link"><fmt:message key="home"/></a>
        </h2>
    <br>
</div>
</body>
</html>
