<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/7/16
  Time: 10:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Создание пользователя</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>

<div class="container">
    <%--<a href="#" class="btn btn-default" > если уже зарег поиск по номеру телефона</a>--%>
    <spring:url value="/service-panel/new-user" var="newUrl"/>
    <form:form action="${newUrl}" method="post" modelAttribute="orderTemplate">
        <div class="form-group">
            <label for="lname"><spring:message code="label.lastName" text="last name"/> </label>
            <input class="form-control" name="lastName" id="lname" placeholder="<spring:message code="label.lastName" text="last name"/> " aria-required="true" aria-describedby="name-format" required/>
        </div>
        <div class="form-group">
            <label for="fname"><spring:message code="label.firstName" text="first name"/> </label>

            <input class="form-control" name="firstName" id="fname" placeholder="<spring:message code="label.firstName" text="first name"/>" aria-required="true" aria-describedby="name-format" required/>
        </div>
        <div class="form-group">
            <label for="phone"><spring:message code="label.phone" text="phone" /> </label>
            <input class="form-control" name="phone" id="phone" required/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">
                <spring:message code="button.save" text="Save"/>
            </button>
        </div>
    </form:form>
</div>
</body>
</html>
