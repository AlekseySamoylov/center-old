<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/19/16
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Автотехцентр</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/printer.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<spring:url value="/resources/js/angular.min.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div class="text-center">
    <h1>${webCompany.name}</h1>
</div>
<div class="container text-right">
    <pre> ${webCompany.other}</pre>
    <br/>
</div>
<div class="text-center"/>
<h3> Акт выполненных работ № ${purchaseOrder.id}</h3>
</div>
<div class="container">
    <p>Модель автомобиля: ${purchaseOrder.carName}</p>
    <p>Заказчик: ${purchaseOrder.client}. Телефон: ${purchaseOrder.clientPhone}</p>
</div>
<c:if test="${not empty purchaseOrder.webOrderWorkTable.webList}">
    <div class="container">
        <h1>Список Работ</h1>
        <table class="table table-hover">
            <tbody>
            <tr>
                <%--<th>--%>
                    <%--<spring:message code="table.number" text="number"/>--%>
                <%--</th>--%>
                <th>
                    <spring:message code="table.work" text="work"/>
                </th>
                <th>
                    <spring:message code="table.price" text="price"/>
                </th>
                <th>
                    <spring:message code="table.value" text="value"/>
                </th>
                <th>
                    <spring:message code="table.sum" text="sum"/>
                </th>
            </tr>
            <c:forEach items="${purchaseOrder.webOrderWorkTable.webList}" var="work">
                <tr>
                    <%--<td>${work.id}</td>--%>
                    <td>${work.name}</td>
                    <td>${work.price}</td>
                    <td>${work.value}</td>
                    <td>${work.sum}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${not empty purchaseOrder.webOrderPartTable.webList}">
    <div class="container">
        <h1>Список запчастей</h1>
        <table class="table table-hover">
            <tbody>
            <tr>
                <%--<th>--%>
                    <%--<spring:message code="table.number" text="number"/>--%>
                <%--</th>--%>
                <th>
                    <spring:message code="table.part" text="part"/>
                </th>
                <th>
                    <spring:message code="table.price" text="price"/>
                </th>
                <th>
                    <spring:message code="table.value" text="value"/>
                </th>
                <th>
                    <spring:message code="table.sum" text="sum"/>
                </th>
            </tr>
            <c:forEach items="${purchaseOrder.webOrderPartTable.webList}" var="part">
                <tr>
                    <%--<td>${part.id}</td>--%>
                    <td>${part.name}</td>
                    <td>${part.price}</td>
                    <td>${part.value}</td>
                    <td>${part.sum}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<div class="container text-right">

    <p>Сумма: ${purchaseOrder.sum}р.
        <c:if test="${purchaseOrder.discount > 0}">
            <c:choose>
                <c:when test="${purchaseOrder.workDiscount && purchaseOrder.partDiscount}">
                    Скидка: ${purchaseOrder.discount}%, Сумма со скидкой: ${purchaseOrder.sumWithDiscount}р.
                </c:when>
                <c:when test="${purchaseOrder.workDiscount}">
                    Скидка на работы: ${purchaseOrder.discount}%, Сумма со скидкой: ${purchaseOrder.sumWithDiscount}р.
                </c:when>
                <c:when test="${purchaseOrder.partDiscount}">
                    Скидка на запчасти: ${purchaseOrder.discount}%, Сумма со скидкой: ${purchaseOrder.sumWithDiscount}р.
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
        </c:if>
    </p>
    <p>Предоплата: ${purchaseOrder.prepayment}р. </p>

    <p>Итого к оплате: ${purchaseOrder.finalSum}р.</p>
</div>
<c:if test="${not empty purchaseOrder.advice}">
    <div class="container">
        <h3><spring:message code="printer.advices" text="Advices"/></h3>
        <p>${purchaseOrder.advice}</p>
    </div>
</c:if>
<div class="container">
    <p>Подписи</p>
    <p>Менеджер:______________</p>
    <br/>
    <p>Заказчик:______________</p>
</div>
</body>
</html>
