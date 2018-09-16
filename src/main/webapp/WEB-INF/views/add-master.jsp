<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/9/16
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add master</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>
<div class="container">
    <spring:url value="/service-panel/open/work/section" var="workUrl"/>
    <form:form action="${workUrl}" method="post" modelAttribute="workTemplate">
        <form:hidden path="orderId"/>
        <div class="form-group">
            <label for="selMaster">Выбор Мастера</label>
        <select name="masterId" class="c-select" id="selMaster" required>
            <c:forEach items="${masters}" var="master">
                <option value="${master.id}">${master.name}</option>
            </c:forEach>
        </select>
            </div>
        <form:hidden path="carModelId"/>
        <form:hidden path="sectionId"/>
        <form:hidden path="workInPrice"/>
        <form:hidden path="priceWorkId"/>
        <form:hidden path="priceName"/>
        <form:hidden path="priceCost"/>
        <form:hidden path="priceValue"/>
    <div class="form-group">
        <button type="submit" class="btn btn-default">
            <spring:message code="button.next" text="Next"/>
        </button>
    </div>
    </form:form>
</body>
</html>
