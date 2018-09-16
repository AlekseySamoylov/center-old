<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/21/16
  Time: 9:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="string" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Purchase-order</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<jsp:include page="../views/fragments/header.jsp"/>
<div class="container">
    <spring:url value="/service-property/save" var="propUrl"/>
    <form:form action="${propUrl}" cssClass="form-group" method="post" modelAttribute="webCompany">
        <form:hidden path="id"/>
        <div class="form-group">
            <label for="name"><spring:message code="company.name" text="Name"/> </label>
            <input type="text" class="form-control" id="name" name="name" value="${webCompany.name}"/>
        </div>
        <div class="form-group">
            <label for="country"><spring:message code="company.country" text="Country"/></label>
            <input type="text" class="form-control" id="country" name="country" value="${webCompany.country}"/>
        </div>
        <div class="form-group">
            <label for="city"><spring:message code="company.city" text="City"/></label>
            <input type="text" class="form-control" id="city" name="city" value="${webCompany.city}"/>
        </div>
        <div class="form-group">
            <label for="district"><spring:message code="company.district" text="District"/></label>
            <input type="text" class="form-control" id="district" name="district" value="${webCompany.district}"/>
        </div>
        <div class="form-group">
            <label for="street"><spring:message code="company.street" text="Street"/></label>
            <input type="text" class="form-control" id="street" name="street" value="${webCompany.street}"/>
        </div>
        <div class="form-group">
            <label for="house"><spring:message code="company.house" text="house"/></label>
            <input type="text" class="form-control" id="house" name="house" value="${webCompany.house}"/>
        </div>
        <div class="form-group">
            <label for="phone"><spring:message code="company.phone" text="phone"/></label>
            <input type="text" class="form-control" id="phone" name="phone" value="${webCompany.phone}"/>
        </div>
        <div class="form-group">
            <label for="site"><spring:message code="company.site" text="site"/></label>
            <input type="text" class="form-control" id="site" name="site" value="${webCompany.site}"/>
        </div>
        <div class="form-group">
            <label for="email"><spring:message code="company.email" text="email"/></label>
            <input type="text" class="form-control" id="email" name="email" value="${webCompany.email}"/>
        </div>
        <div class="form-group">
            <label for="other"><spring:message code="company.other" text="other"/></label>
            <textarea rows="6" cols="50"  class="form-control" id="other" name="other">${webCompany.other}"</textarea>
        </div>

        <button type="submit"><spring:message code="button.save" text="save"/> </button>


    </form:form>
</div>
</body>
</html>
