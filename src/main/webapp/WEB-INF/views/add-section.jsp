<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/9/16
  Time: 7:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add section</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>

<div class="container">
    <spring:url value="/service-panel/open/work/select" var="workUrl"/>
    <form:form action="${workUrl}" method="post" modelAttribute="workTemplate">
        <form:hidden path="orderId"/>
        <form:hidden path="masterId"/>
        <form:hidden path="carModelId"/>
        <div class="form-group">
            <label for="selSec">Выбор Вида Работ</label>
            <select name="sectionId" class="c-select" id="selSec" required>
                <c:forEach items="${sections}" var="section">
                    <option value="${section.id}">${section.name}</option>
                </c:forEach>
            </select>
        </div>
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
</div>
</body>
</html>
