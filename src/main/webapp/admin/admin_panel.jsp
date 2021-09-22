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
    <title><fmt:message key="admin_panel"/></title>
</head>
<%@include file="../header.jspf" %>
<body>
<br/>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="deleteApp"/>
    <label>
        <input type="text" name="id" placeholder="id = "/>
    </label>
    <button type="submit"><fmt:message key="delete"/></button>
</form>
<br/>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="editAppTimestamp"/>
    <label>
        <input type="text" name="id" placeholder="id = "/>
    </label>
    <label>
        <input style="width: 310px;" type="text" name="newTimeStamp"
               placeholder="Enter timeStamp like '2021-09-03 11:00:00'"/>
    </label>
    <button type="submit"><fmt:message key="edit"/></button>
</form>
<br/>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="changePaymentStatus"/>
    <label>
        <input type="text" name="id" placeholder="id = "/>
    </label>
    <label>
        <input type="text" name="status" placeholder="status = "/>
    </label>
    <button type="submit"><fmt:message key="edit"/></button>
</form>
<br/>
<fmt:message key="admin_panel"/>
<c:choose>
    <c:when test="${allAppointments eq null}">
        <fmt:message key="schedule.no_appointments"/>
    </c:when>
    <c:otherwise>
        <table class="table table-striped table-bordered" id="example">
            <thead>
            <tr>
                <td>Id</td>
                <td>UserId</td>
                <td>MasterId</td>
                <td>ServiceId</td>
                <td><fmt:message key="schedule.date_and_time"/></td>
                <td><fmt:message key="schedule.comment"/></td>
                <td>PaymentStatus</td>
                <td>Status</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allAppointments}" var="appointment">
                <tr>
                    <td> ${appointment.appId} </td>
                    <td> ${appointment.userId} </td>
                    <td> ${appointment.masterId} </td>
                    <td> ${appointment.serviceId} </td>
                    <td> <fmt:parseDate value="${appointment.timestamp}"
                                        pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                        <fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${ parsedDateTime }" /> </td>
                    <td> ${appointment.comment} </td>
                    <td> ${appointment.paymentStatus} </td>
                    <td> ${appointment.status} </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
