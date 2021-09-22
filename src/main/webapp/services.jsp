<%@include file="taglib.jspf"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="services"/></title>
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
<h3><fmt:message key="service.table"/></h3>
<br>
<table class="table table-striped table-bordered" id="example">
    <thead>
    <tr>
        <td><fmt:message key="service.name"/></td>
        <td><fmt:message key="service.description"/></td>
        <td><fmt:message key="service.price"/></td>
    </tr>
    </thead>
    <thead>
    <tr>
        <th><fmt:message key="service.name"/></th>
        <th><fmt:message key="service.description"/></th>
        <th><fmt:message key="service.price"/></th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${language eq 'en'}">
            <c:forEach items="${col}" var="col">
                <tr>
                    <td> ${col.nameEn} </td>
                    <td> ${col.descriptionEn} </td>
                    <td> ${col.price} ₴ </td>
                </tr>
            </c:forEach>
        </c:when>

        <c:when test="${language eq 'ru'}">
            <c:forEach items="${col}" var="col">
                <tr>
                    <td> ${col.nameRu} </td>
                    <td> ${col.descriptionRu} </td>
                    <td> ${col.price} ₴ </td>
                </tr>
            </c:forEach>
        </c:when>

        <c:when test="${language eq null}">
            <c:forEach items="${col}" var="col">
                <tr>
                    <td> ${col.nameRu} </td>
                    <td> ${col.descriptionRu} </td>
                    <td> ${col.price} ₴ </td>
                </tr>
            </c:forEach>
        </c:when>
    </c:choose>
    </tbody>
</table>
</body>
</html>
