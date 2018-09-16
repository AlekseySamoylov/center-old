<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/7/16
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SelectClient</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body ng-app>
<jsp:include page="../views/fragments/header.jsp"/>
<div class="container">
<spring:url value="/service-panel/create/client" var="clientUrl"/>
<form:form action="${clientUrl}" method="post" modelAttribute="orderTemplate">
    <form:hidden path="managerId"/>
    <form:hidden path="companyId"/>
    <form:hidden path="firstName"/>
    <form:hidden path="lastName"/>
    <form:hidden path="phone"/>
<div class="form-group">
    <label for="selClient">Выбор Клиента</label>
    <select class="form-control" name="clientId" id="selClient" ng-click="!master">
        <option value="${clients.get(0).id}" ng-selected="selected">${clients.get(0).name}</option>
        <c:forEach items="${clients}" var="client">
            <option value="${client.id}">${client.name}</option>
        </c:forEach>
    </select>
</div>
    <form:hidden path="clientCarInList"/>
    <form:hidden path="carModelId"/>
    <form:hidden path="carModelName"/>
    <form:hidden path="clientCarId"/>
    <form:hidden path="clientCarNumber"/>
    <form:hidden path="mainOrderText"/>
    <button type="submit" id="btnNext" class="btn btn-default" name="clientInBase" value="true">
        <spring:message code="button.next" text="Next"/>
    </button>
    <button type="submit" class="btn btn-default" name="clientInBase" value="false">
        <spring:message code="button.createnew" text="Create new"/>
    </button>
</form:form>

    <label>
        <input type="checkbox" class="btn btn-default" ng-model="selected" ng-checked="master"/>
        <spring:message code="button.iamlazy" text="I am lazy"/>
    </label>

</div>
</body>
</html>
