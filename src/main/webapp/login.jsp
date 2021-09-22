<%@include file="taglib.jspf" %>
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
    <title><fmt:message key="login"/></title>
</head>
<%@include file="header.jspf" %>
<body>
<div align="center">
    <br/>
    <h1><fmt:message key="login_form"/></h1>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <table>
            <tr>
                <td><fmt:message key="name"/></td>
                <td><input type="text" name="username" required/></td>
            </tr>
            <tr>
                <td><fmt:message key="password"/></td>
                <td><input type="password" name="password" required/></td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="Enter"/>"/>
    </form>
    <% if (session.getAttribute("userNotFound") == null) {
        session.setAttribute("userNotFound", "");
    }%>
    <%= session.getAttribute("userNotFound")%>
</div>
</body>
</html>