<%@include file="../taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <title><fmt:message key="cabinet"/></title>
</head>
<%@include file="../header.jspf" %>
<body>
<div align="center">
    <br>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="updateComment"/>
        <input type="hidden" name="id" value="<%=request.getParameter("appid")%>"/>
        <label>
            <textarea rows="10" cols="45" type="text" name="comment" placeholder="Enter your comment here"></textarea>
        </label>
        <br>
        <button type="submit"><fmt:message key="edit"/></button>
    </form>
</div>
</body>
</html>
