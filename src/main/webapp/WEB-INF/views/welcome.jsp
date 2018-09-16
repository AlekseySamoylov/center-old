<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/15/16
  Time: 8:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>New text</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>
<div class="container">
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.authenticated}">
            <h1><spring:message code="header.repaircenter" text="Repair Center"/></h1>
            <div id="chart_div"></div>
            <%--<a class="btn btn-default" href="<spring:url value="/diagrams"/>">Diagrams</a>--%>
        </c:when>
        <c:otherwise>
            <div class="container text-center">
                <%--<a class="btn btn-default"><spring:message code="welcome.registrcenter" text="Reg Center"/></a>--%>
                <a class="btn btn-default" href="<spring:url value="/create-user"/>"><spring:message code="welcome.registruser" text="Registration User"/></a>
            </div>
        </c:otherwise>
    </c:choose>

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
            data.addColumn('number', 'Оборот');
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
