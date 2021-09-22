<%@include file="taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

    <!-- jQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <!-- Moment -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js"></script>
    <!-- Bootstrap DateTimePicker -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <!-- Font Awesome CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Bootstrap DateTimePicker CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet">
    <!-- Инициализация Bootstrap DateTimePicker -->
    <c:choose>
        <c:when test="${language eq 'en'}">
            <script src="js/DateTimePickerEn.js"></script>
        </c:when>
        <c:when test="${language eq 'ru'}">
            <script src="js/DateTimePickerRu.js"></script>
        </c:when>
        <c:otherwise>
            <script src="js/DateTimePickerRu.js"></script>
        </c:otherwise>
    </c:choose>


    <title><fmt:message key="schedule.reserve"/></title>
</head>
<%@include file="header.jspf" %>
<body>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="registerApp"/>
    <table>
        <tr>
            <td><label for="services">
                <fmt:message key="schedule.choose_service"/>
            </label></td>
            <td><select id="services" name="serviceId">
                <option><fmt:message key="schedule.choose_service"/></option>
            </select></td>
        </tr>
        <tr>
            <td><label for="masters">
                <fmt:message key="schedule.choose_master"/>
            </label></td>
            <td><select id="masters" name="masterId">
                <option><fmt:message key="schedule.choose_master"/></option>
            </select></td>
        </tr>
        <tr>
            <td><label>
                <fmt:message key="schedule.timestamp"/>
            </label></td>
            <td><div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <div class="input-group" id="datetimepicker2">
                                <input type="text" class="form-control"/>
                                <input type="hidden" name="timeStamp" id="timeStamp"/>
                                <span class="input-group-addon">
              <i class="glyphicon glyphicon-calendar"></i>
            </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div></td>
        </tr>
    </table>

    <button type="submit"><fmt:message key="Enter"/></button>
</form>
<br/>
<c:choose>
    <c:when test="${language eq 'en'}">
        <script src="js/DependentSelectDropdownEn.js"></script>
    </c:when>
    <c:when test="${language eq 'ru'}">
        <script src="js/DependentSelectDropdownRu.js"></script>
    </c:when>
    <c:otherwise>
        <script src="js/DependentSelectDropdownRu.js"></script>
    </c:otherwise>
</c:choose>

</body>
</html>
