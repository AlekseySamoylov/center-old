<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/9/16
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Complete</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>

<div class="container">
    <spring:url value="/service-panel/open/work/finish" var="workUrl"/>
    <form:form action="${workUrl}" method="post" modelAttribute="workTemplate">
        <form:hidden path="orderId"/>
        <form:hidden path="masterId"/>
        <form:hidden path="carModelId"/>
        <form:hidden path="sectionId"/>
        <form:hidden path="workInPrice"/>
        <form:hidden path="priceWorkId"/>

        <div class="form-group">
            <label for="pName">Название </label>
            <input type="text" class="form-control" name="priceName" id="pName" aria-required="true" aria-describedby="name-format" required/>
        </div>


        <div class="form-group">
        <label for="cost"><spring:message code="table.price" text="price"/> </label>
        <input type="number" min="0" max="900000" value="0" class="form-control" name="priceCost" id="cost"/>
    </div>

    <div class="form-group">
        <label for="stepper1"><spring:message code="table.value" text="value"/> </label>
        <input class="form-control" type="number" id="stepper1" name="priceValue" min="1" max="100" value="1" />
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-default">
            <spring:message code="button.next" text="Next"/>
        </button>
    </div>
    </form:form>
</body>
</html>
