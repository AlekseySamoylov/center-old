<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/15/16
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>

    <div class="container">
        <div class="row">
            <h1><spring:message code="login.login" text="Services"/></h1>
        </div>
        <spring:url value="/login" var="loginUrl"/>
        <form id="appointment-form" action="${loginUrl}" method="POST" >
            <div class="form-group">
                <label for="user"><spring:message code="login.username" text="Username"/></label>
                <input type="text" name="custom_username" class="form-control" id="user"/>
            </div>
            <div class="form-group">
                <label for="pass"><spring:message code="login.pass" text="Password"/></label>
                <input type="password" name="custom_password" class="form-control" id="pass"/>
            </div>
            <c:if test="${param.error != null}">
                <p><spring:message code="login.wrong" text="Invalid Username or Password"/></p>
            </c:if>
            <sec:csrfInput/>
            <button type="submit" id="btn-save" class="btn btn-primary"><spring:message code="login.ok" text="Login"/></button>
        </form>
    </div>
</body>
</html>
