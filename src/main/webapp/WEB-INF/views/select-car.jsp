<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/8/16
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Select car</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="<spring:url value="/resources/js/jquery.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body ng-app>
<jsp:include page="../views/fragments/header.jsp"/>
<div class="container">
    <spring:url value="/service-panel/create/car" var="carUrl"/>
    <form:form action="${carUrl}" method="post" modelAttribute="orderTemplate">
        <form:hidden path="managerId"/>
        <form:hidden path="companyId"/>
        <form:hidden path="clientInBase"/>
        <form:hidden path="firstName"/>
        <form:hidden path="lastName"/>
        <form:hidden path="phone"/>
        <form:hidden path="clientId"/>
        <form:hidden path="carModelId"/>
        <select class="form-control" name="clientCarId">
            <c:forEach items="${clientCars}" var="clientCar">
                {{showb = true}}
                <option value="${clientCar.id}">${clientCar.name}</option>
            </c:forEach>
        </select>
        <form:hidden path="carModelName"/>
        <form:hidden path="clientCarNumber"/>
        <form:hidden path="mainOrderText"/>
        <button ng-show="showb" type="submit" class="btn btn-default" name="clientCarInList" value="true">
            <spring:message code="button.next" text="Next"/>
        </button>
        <button type="submit" class="btn btn-default" name="clientCarInList" value="false">
            <spring:message code="button.notinlist" text="Not in list"/>
        </button>
    </form:form>
</div>

</body>
</html>
