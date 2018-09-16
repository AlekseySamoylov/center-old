<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/7/16
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Список заказов</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>

<div class="container form-inline">


    <a class="btn btn-default" href="<spring:url value="/service-panel/create"/>">
        <spring:message code="button.create" text="Create"/>
    </a>
    <a class="btn btn-default" href="<spring:url value="/service-panel/new-user"/>">
        Новый пользователь
    </a>
    <a class="btn btn-default" href="<spring:url value="/service-panel/open-clients"/>">
        <spring:message code="button.clients" text="Clients"/>
    </a>
    <a class="btn btn-default" href="<spring:url value="/service-panel/open-employees"/>">
        <spring:message code="button.employees" text="Employees"/>
    </a>
    <a class="btn btn-default" href="<spring:url value="/service-panel/salary-page"/>">
        Зарплата за текущий день
    </a>

    <a class="btn btn-default" href="<spring:url value="/service-property" />">Данные автосервиса</a>


</div>
<div class="container">

    <table class="table table-hover">
        <tbody>
        <tr>
            <%--<th><spring:message code="table.number" text="number"/></th>--%>
            <th><spring:message code="table.date" text="date"/></th>
            <th><spring:message code="table.Car" text="car"/></th>
            <th><spring:message code="table.description" text="description"/></th>
            <th><spring:message code="table.sum" text="sum"/></th>
            <th>Информация</th>
            <th><spring:message code="table.edit" text="edit"/></th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                    <%--<td>${order.id}</td>--%>
                <td>${order.orderDate}</td>
                <td>${order.name}</td>
                <td>${order.description}</td>
                <td>${order.sum}</td>
                <td class="info-row">
                    ${order.complete}
                    <br/>
                    <span class="href-c">${order.rating}</span>${order.id}
                </td>
                <td>
                    <a href="<spring:url value="/service-panel/open/${order.id}"/>">
                        <spring:message code="table.open" text="open"/>
                    </a>
                    <a href="<spring:url value="/service-panel/delete-order/${order.id}"/>">
                        <spring:message code="table.delete" text="delete"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    var arr = [].slice.call(document.getElementsByClassName("href-c"));
    arr.forEach(function (item, i, arr) {
        if (item.innerHTML == 0) {
            item.innerHTML = "http://" + window.location.hostname + ":" + window.location.port + window.location.pathname + "rating/";
        } else {
            item.previousSibling.textContent = "Ваша оценка: ";
            item.nextSibling.textContent = "☆";
        }
    });
</script>
</body>
</html>
