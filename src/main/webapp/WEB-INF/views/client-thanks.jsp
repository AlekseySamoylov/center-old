<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/31/16
  Time: 9:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
    <title>Благодарим за оценку</title>
</head>
<body>
<div class="container text-center">
    <div class="col-xs">
        <h1>Благодарим за оценку</h1>
        <img src="<spring:url value="/resources/pictures/smile.jpg"/>" class="img-thumbnail" alt="Smile">
    </div>
</div>

</body>
</html>
