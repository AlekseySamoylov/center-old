<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/30/16
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Клиенты</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>

<div class="container">
    <h1>Клиенты</h1>
    <table class="table table-bordered">
        <tr>
            <th>Номер</th>
            <th>Имя</th>
            <th>Авто</th>
            <th>Телефон</th>
            <th>Сделать</th>
        </tr>
        <c:forEach items="${clients}" var="client">
            <tr>
                <td>
                        ${client.id}
                </td>
                <td>
                        ${client.name}
                </td>
                <td>
                        ${client.car}
                </td>
                <td>
                        ${client.phone}
                </td>
                <td>
                    <a href="<spring:url value="/service-panel/set-manager/${client.id}"/>">
                        менеджером
                    </a> |
                    <a href="<spring:url value="/service-panel/set-master/${client.id}"/>">
                        слесарем
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
