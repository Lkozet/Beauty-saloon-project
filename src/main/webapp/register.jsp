<%@include file="taglib.jspf"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <title><fmt:message key="BeautySalon"/></title>
</head>
<%@include file="header.jspf"%>
<body>
<div align="center">
    <h1><fmt:message key="registration"/></h1>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="registerUser"/>
        <table style="with: 80%">
            <tr>
                <td><fmt:message key="name"/></td>
                <td><input type="text" name="login" required placeholder="<fmt:message key="placeholder.login"/>"/></td>
            </tr>
            <tr>
                <td><fmt:message key="password"/></td>
                <td><input type="password" name="password" required placeholder="<fmt:message key="placeholder.pass"/>"/></td>
            </tr>
            <tr>
                <td><fmt:message key="email"/></td>
                <td><input type="email" name="email" required placeholder="<fmt:message key="placeholder.email"/>"/></td>
            </tr>
            <tr>
                <td><fmt:message key="phone"/></td>
                <td><input type="tel" name="phone" required placeholder="<fmt:message key="placeholder.phone"/>"/></td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="register"/>" />
    </form>
    <% if (session.getAttribute("userAlreadyExists") == null) {
        session.setAttribute("userAlreadyExists", "");
    }%>
    <%= session.getAttribute("userAlreadyExists")%>
</div>
</body>
</html>