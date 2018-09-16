<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>

<div class="container">
    <p>${userInfo.firstName} ${userInfo.lastName} </p>
    <p>${userInfo.otherPhone}</p>
    <c:forEach items="${userInfo.companies}" var="company">
        <c:if test="${userInfo.companyId == company.id}">
            <h1>${company.name}</h1>
             <p>${company.phone}</p>
        </c:if>
    </c:forEach>
    <div id="chart_div"></div>
    <p>select your company:</p>
    <c:forEach items="${userInfo.companies}" var="company">
        <a href="<spring:url value="/user-page/select-company/${company.id}"/>">
                ${company.name}
        </a>
        <br/>
    </c:forEach>
    <c:forEach items="${userInfo.cars}" var="car">
        <p>${car.name}</p>
    </c:forEach>
    <p>Selected company:</p>
    <c:forEach items="${userInfo.companies}" var="company">
        <c:if test="${userInfo.companyId == company.id}">
            <p>${company.name} ${company.phone}</p>
        </c:if>
    </c:forEach>

    <%--<c:forEach items="${userInfo.orders}" var="order">--%>
        <%--<p>${order.name} ${order.sum}</p>--%>
    <%--</c:forEach>--%>

</div>
<script type="text/javascript">

    $.getJSON( "<spring:url value="/diagrams"/>", function( jsonObj ) {
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = new google.visualization.DataTable();
            var tempList = [];
            var counter = 0;
            data.addColumn('date', 'Дата');
            data.addColumn('number', 'Ежедневный оборот');
            jsonObj.forEach(function (entry) {
                tempList[counter] = ([new Date(entry.simpleDate), entry.daySum]);
                counter++;
            });
            data.addRows(tempList);
            // Set chart options
            var options = {'title':'Эффективность компании'};
            // Instantiate and draw our chart, passing in some options.
            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    });
</script>
</body>
</html>
