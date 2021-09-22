<%@include file="taglib.jspf"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="masters"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.0/css/jquery.dataTables.min.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.0/js/jquery.dataTables.min.js"></script>
    <c:choose>
        <c:when test="${language eq 'en'}">
            <script src="js/FilterAndSort.js"></script>
        </c:when>
        <c:when test="${language eq 'ru'}">
            <script src="js/FilterAndSortRu.js"></script>
        </c:when>
        <c:otherwise>
            <script src="js/FilterAndSortRu.js"></script>
        </c:otherwise>
    </c:choose>
</head>
<%@include file="header.jspf"%>
<body>
<h3><fmt:message key="master.table"/></h3>
<br>
<table class="table table-striped table-bordered" id="example">
    <thead>
    <tr>
        <td><fmt:message key="master.name"/></td>
        <td><fmt:message key="master.description"/></td>
        <td><fmt:message key="master.rating"/></td>
    </tr>
    </thead>
    <thead>
    <tr>
        <th><fmt:message key="master.name"/></th>
        <th><fmt:message key="master.description"/></th>
        <th><fmt:message key="master.rating"/></th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${language eq 'en'}">
            <c:forEach items="${col2}" var="col2">
                <tr>
                    <td> ${col2.nameEn} </td>
                    <td> ${col2.informationEn} </td>
                    <td> ${col2.rating} </td>
                </tr>
            </c:forEach>
        </c:when>

        <c:when test="${language eq null}">
            <c:forEach items="${col2}" var="col2">
                <tr>
                    <td> ${col2.nameRu} </td>
                    <td> ${col2.informationRu} </td>
                    <td> ${col2.rating} </td>
                </tr>
            </c:forEach>
        </c:when>

        <c:when test="${language eq 'ru'}">
            <c:forEach items="${col2}" var="col2">
                <tr>
                    <td> ${col2.nameRu} </td>
                    <td> ${col2.informationRu} </td>
                    <td> ${col2.rating} </td>
                </tr>
            </c:forEach>
        </c:when>
    </c:choose>

    </tbody>
</table>
</body>
</html>
