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
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.0/css/jquery.dataTables.min.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.0/js/jquery.dataTables.min.js"></script>
    <title><fmt:message key="cabinet"/></title>
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
</head>
<%@include file="../header.jspf" %>
<body>
<h3><fmt:message key="schedule.table"/></h3>
<br>
<c:choose>
    <c:when test="${appointments2 eq null}">
        <fmt:message key="schedule.no_appointments"/>
    </c:when>
    <c:otherwise>
        <table class="table table-striped table-bordered" id="example">
            <thead>
            <tr>
                <td><fmt:message key="schedule.date_and_time"/></td>
                <td><fmt:message key="schedule.comment"/></td>
                <td><fmt:message key="schedule.write_comment"/></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${appointments2}" var="appointments2">
                <tr>
                    <td><fmt:parseDate value="${appointments2.timestamp}"
                                       pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                        <fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${ parsedDateTime }"/></td>
                    <td> ${appointments2.comment} </td>
                    <c:choose>
                        <c:when test="${not empty appointments2.comment}">
                            <td><fmt:message key="schedule.thanks"/></td>
                        </c:when>
                        <c:when test="${appointments2.paymentStatus eq 'payed'}">
                            <td>
                                <a href="${pageContext.request.contextPath}/client/update_comment.jsp?appid=${appointments2.appId}">
                                    <fmt:message key="schedule.write_comment"/></a></td>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:message key="schedule.no_write_comment"/></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
