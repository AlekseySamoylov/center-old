<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 8/6/16
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %><html>
<head>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <title>Salary</title>
</head>
<body ng-app="SalaryModule" ng-controller="SalaryController">
<jsp:include page="../views/fragments/header.jsp"/>

<div class="container">
    <h1>Зарплата за текущую смену</h1>
    <p>Доля автослесаря с работы: {{salaryPercent}}% </p>
    <input class="form-control" type="range" min="0" max="100" ng-model="salaryPercent">

</div>

<div class="container" ng-repeat="employee in employees">
    <p><strong>Имя работника: {{employee.name}}</strong></p>
    <p>Работа и доля автослесаря</p>
    <ul>
        <li ng-repeat="work in employee.works">
            {{work.name}} : <strong>{{work.price * salaryPercent * 0.01}}</strong> р.
        </li>
    </ul>
    <p>Зарплата итого: <strong>{{returnSum(employee) * salaryPercent * 0.01}}</strong> р.</p>
</div>
<script src="<spring:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<spring:url value="/resources/js/salary-app.js"/>"></script>
</body>
</html>
