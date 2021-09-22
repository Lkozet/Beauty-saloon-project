<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
<%@include file="taglib.jspf"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <ex:TagLib/>

    <title><fmt:message key="home"/></title>
</head>
<%@include file="header.jspf"%>
<body>
<% if (session.getAttribute("userNotFound") != null) {
    session.setAttribute("userNotFound", "");
}%>

<br/>
<div align="center">
    <br>
    <h4 ${hidden}><fmt:message key="greeting"/></h4>
    <br>
</div>
</body>
</html>