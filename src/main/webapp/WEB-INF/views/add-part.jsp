<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/10/16
  Time: 10:16 AM
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
    <spring:url value="/service-panel/open/part" var="partUrl"/>
    <h1>Добавление товара в квитанцию</h1>
    <form:form action="${partUrl}" method="post" modelAttribute="partTemplate">
        <form:hidden path="orderId"/>
        <div class="form-group">
            <label>Наименование запчасти
                <input type="text" class="form-control" name="name"  aria-required="true" aria-describedby="name-format" required/>
            </label>
        </div>
        <div class="form-group">
            <label>Цена
                <input type="number" min="0" max="900000" name="price" class="form-control"/> р.</label>
        </div>
        <div class="form-group">
            <label>количество
                <input type="number" min="1" max="1000" name="value" value="1" class="form-control"/>шт.</label>
        </div>
        <form:hidden path="sum"/>
        <div class="form-group">
            <button type="submit" class="btn btn-default">
                <spring:message code="button.next" text="next"/>
            </button>
        </div>
    </form:form>
</div>

</body>
</html>
