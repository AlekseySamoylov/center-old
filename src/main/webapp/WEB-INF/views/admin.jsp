<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/19/16
  Time: 8:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Panel</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>

<div class="container">
    <h1>Admin panel</h1>
    <table class="table table-row-cell">
        <tr>
            <th>Date</th>
            <th>Text</th>
        </tr>
        <c:forEach items="${logs}" var="log">
            <tr>
                <td>${log.logDate}</td>
                <td>${log.name}</td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
