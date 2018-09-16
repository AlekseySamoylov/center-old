<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/31/16
  Time: 8:51 AM
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
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/star-rating.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
    <title>Оцените работу</title>
</head>
<body>
<div class="container text-center">
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="col-xs">
        <h1>Оцените работу Автотехцентра</h1>
        <br/>
        <br/>

        <div class="star-rating">
            <input form="rating-form" type="radio" value="1" name="stars">
            <i></i>
            <input form="rating-form" type="radio" value="2" name="stars">
            <i></i>
            <input form="rating-form" type="radio" value="3" name="stars">
            <i></i>
            <input form="rating-form" type="radio" value="4" name="stars" checked>
            <i></i>
            <input form="rating-form" type="radio" value="5" name="stars">
            <i></i>
        </div>
        <br/>
        <br/>

        <spring:url value="/rating/save" var="ratingUrl"/>
        <form:form id="rating-form" action="${ratingUrl}" method="post" modelAttribute="ratingForm">
            <form:hidden path="id"/>
            <div class="form-group">
            <textarea class="form-control" id="comment" name="comment" rows="8"
                      placeholder="Ваш комментарий (Не обязательно)"></textarea>
            </div>
            <button class="btn-lg btn-success" type="submit">Отправить оценку</button>
        </form:form>


    </div>
</div>

<script>
    $(':radio').change(function () {
        $('.choice').text('Ваша оценка: ' + this.value);
    });
    //# sourceURL=pen.js
</script>
</body>
</html>
