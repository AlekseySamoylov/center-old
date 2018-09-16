<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/8/16
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New text</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body ng-app="orderText" ng-controller="OrderController">
<jsp:include page="../views/fragments/header.jsp"/>
<div class="container">
    <h2>Задания или предполагаемые неисправности</h2>
</div>

<div class="container">
    <spring:url value="/service-panel/create/save" var="saveUrl"/>
    <form:form action="${saveUrl}" method="post" modelAttribute="orderTemplate">
        <form:hidden path="managerId"/>
        <form:hidden path="companyId"/>
        <form:hidden path="clientInBase"/>
        <form:hidden path="firstName"/>
        <form:hidden path="lastName"/>
        <form:hidden path="phone"/>
        <form:hidden path="clientId"/>
        <form:hidden path="clientCarInList"/>
        <form:hidden path="carModelId"/>
        <form:hidden path="carModelName"/>
        <form:hidden path="clientCarId"/>
        <form:hidden path="clientCarNumber"/>
        <div class="form-group">
            <a  ng-repeat="advice in advices" class="btn btn-default" ng-click="textFunc(advice.description)">
                {{advice.name}}
            </a>
        </div>
        <div class="form-group">
        <label for="orderText">Текст</label>
        <textarea name="mainOrderText" class="form-control" rows="8" id="orderText" ng-model="textOrder"></textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">
                <spring:message code="button.save" text="Save"/>
            </button>
        </div>
    </form:form>
</div>
<script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
<script src="<spring:url value="/resources/js/order-text.js"/>"></script>
</body>
</html>
