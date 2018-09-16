<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 8/13/16
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
    <title>Регистрация пользователя</title>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>

<div class="container" ng-app="registrationModule" ng-controller="registrationController">
    <h1>Быстрая регистрация</h1>

    <p>{{user}} {{noExistInList}} {{allChecked}}</p>
    <form name="registrationForm" ng-submit="saveUser()">
        <div class="form-group">
            <input class="form-control" placeholder="Имя" type="text" name="firstName" ng-model="user.firstName" ng-blur="check1()" required/>
            <span style="color: red;" ng-show="checkFN">Укажите Ваше имя корректно</span>
        </div>
        <div class="form-group">
            <input class="form-control" placeholder="Фамилия"  type="text" name="lastName" ng-model="user.lastName" ng-blur="check2()" required/>
            <span style="color: red;" ng-show="checkLN">Укажите Вашу фамилию корректно</span>
        </div>
        <div class="form-group">
            <input class="form-control" placeholder="Электронная почта"  type="email" name="userEmail" ng-model="user.email" ng-blur="checkEM = registrationForm.userEmail.$invalid; check()" required/>
            <span style="color: red;" ng-show="checkEM">Укажите Вашу электронную почту корректно</span>
        </div>
        <div class="form-group">
            <input class="form-control" placeholder="Логин"  type="text" name="login" ng-model="user.login" ng-blur="check3(); checkModel()" my-login="doSomething()" required/>
            <span style="color: red;" ng-show="checkLogin">Введите логин корректно</span>
            <span style="color: red;" ng-show="!noExistInList">Логин уже занят</span>
        </div>
        <div class="form-group">
            <input class="form-control" placeholder="Пароль"  type="password" name="password" ng-model="user.password" my-enter="doSomething()" ng-blur="check4()"  required/>
            <span style="color: red;" ng-show="checkPass1">Введите пароль (более 5 символов)</span>
        </div>
        <div class="form-group">
            <input class="form-control" placeholder="Пароль повторно"  type="password" ng-model="pass2" ng-blur="check5()" my-enter="doSomething()" required/>
            <span style="color: red;" ng-show="checkPass2">Повторно введите пароль</span>
            <span style="color: red;" ng-show="checkPass3">Пароли должны совпадать</span>
        </div>

        <div class="form-group">
            <button ng-show="noExistInList && allChecked" class="btn btn-success" type="submit">Зарегистрировать</button>
        </div>
    </form>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<spring:url value="/resources/js/registration-app.js"/>"></script>

</body>
</html>
