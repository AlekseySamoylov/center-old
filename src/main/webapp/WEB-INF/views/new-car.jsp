<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/8/16
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html ng-app>
<head>
    <title>New car</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/new-car.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>
<div class="container">
    <spring:url value="/service-panel/create/text" var="textUrl"/>
    <form:form action="${textUrl}" method="post" modelAttribute="orderTemplate">
        <form:hidden path="managerId"/>
        <form:hidden path="companyId"/>
        <%--<form:hidden path="date"/>--%>
        <form:hidden path="clientInBase"/>
        <form:hidden path="firstName"/>
        <form:hidden path="lastName"/>
        <form:hidden path="phone"/>
        <form:hidden path="clientId"/>
        <form:hidden path="clientCarInList"/>
        <form:hidden path="clientCarId"/>

        <h3>Выберите марку и модель автомобиля</h3>
        <%--<div ng-show="!showDetail">--%>

        <label>Марка автомобиля</label>
        <select class="form-control" ng-model="data.markSelect" required>
            <c:forEach items="${carMarks}" var="carMark">
                <%--<option ng-click="show${carMark.id}Details = ! show${carMark.id}Details;--%>
                <%--showDetail = ! showDetail"--%>
                <option
                        value="${carMark.id}">${carMark.name}</option>
            </c:forEach>
        </select>

        <%--{{data.modelSelect = 1}}--%>

        <c:forEach items="${carMarks}" var="carMark">
            <%--<div ng-show="data.markSelect == ${carMark.id}">--%>

            <%--<a class="btn btn-default" ng-click="data.markSelect = 0;--%>
            <%--showDetail = ! showDetail">--%>
            <%--Другая марка--%>
            <%--</a>--%>

            <%--</div>--%>


            <div ng-show="data.markSelect == ${carMark.id}">
                <label>Модель автомобиля</label>
                <select class="form-control" ng-model="data.modelSelect" required>
                    <c:forEach items="${carMark.models}" var="carModel">
                        <option value="${carModel.id}">${carModel.name}</option>
                        <%--<div ng-show="data.markSelect == ${carMark.id}" class="form-group">--%>
                        <%--<label class="new-model"><form:radiobutton path="carModelId" value="${carModel.id}"/>--%>
                        <%--${carModel.name}--%>
                        <%--</label>--%>
                        <%--</div>--%>
                    </c:forEach>
                </select>
            </div>

        </c:forEach>


        <input type="number" name="carModelId" hidden="hidden" value="{{data.modelSelect}}">
        <form:hidden path="carModelName"/>
        <%--<form:hidden path="clientCarModelId"/>--%>
        <div class="form-group">
            <label for="carNumber">Гос Номер</label>
            <input class="form-control" name="clientCarNumber" id="carNumber" value="A111AA 159"/>
        </div>
        <form:hidden path="mainOrderText"/>
        <div class="form-group">
            <button type="submit" class="btn btn-default">
                <spring:message code="button.next" text="Next"/>
            </button>
        </div>
    </form:form>
</div>
<script src="<spring:url value="/resources/js/angular.min.js"/>"></script>

</body>
</html>
