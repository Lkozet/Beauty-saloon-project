<%@include file="../taglib.jspf"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.0/css/jquery.dataTables.min.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.0/js/jquery.dataTables.min.js"></script>
    <c:choose>
        <c:when test="${language eq 'en'}">
            <script src="${pageContext.request.contextPath}/js/FilterAndSort.js"></script>
        </c:when>
        <c:when test="${language eq 'ru'}">
            <script src="${pageContext.request.contextPath}/js/FilterAndSortRu.js"></script>
        </c:when>
        <c:otherwise>
            <script src="${pageContext.request.contextPath}/js/FilterAndSortRu.js"></script>
        </c:otherwise>
    </c:choose>
    <title><fmt:message key="cabinet"/></title>
</head>
<%@include file="../header.jspf"%>
<body>
<br/>
<h3><fmt:message key="schedule.table"/></h3>

<c:choose>
    <c:when test="${appointments eq null}">
        <fmt:message key="schedule.no_appointments"/>
    </c:when>
    <c:otherwise>
<table class="table table-striped table-bordered" id="example">
    <thead>
    <tr>
        <td><fmt:message key="schedule.date_and_time"/></td>
        <td><fmt:message key="schedule.comment"/></td>
        <td>Change status</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${appointments}" var="appointment">
        <c:if test="${appointment.status eq 'waiting'}">
            <tr>
                <td> <fmt:parseDate value="${appointment.timestamp}"
                                    pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${ parsedDateTime }"/> </td>
                <td> ${appointment.comment} </td>
                <td> ${appointment.status}
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="updateStatus"/>
                        <input type="hidden" name="id" value="${appointment.appId}"/>
                        <input type="hidden" name="appStatus" value="done"/>
                        <button type="submit"><fmt:message key="edit"/></button>
                    </form></td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
    </c:otherwise>
</c:choose>
</body>
</html>
