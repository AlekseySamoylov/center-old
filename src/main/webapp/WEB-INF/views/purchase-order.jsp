<%--
  Created by IntelliJ IDEA.
  User: alekseysamoylov
  Date: 7/8/16
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <p>${purchaseOrder.rating}</p>
    <spring:url value="/service-panel/open/save" var="saveUrl"/>
    <form:form action="${saveUrl}" cssClass="form-group" method="post" modelAttribute="textPlusAdvice"
               id="textPlusAdvice">

        <input type="hidden" name="id" value="${purchaseOrder.id}">

        <div class="form-group">
            <label for="textT">Заказ-наряд</label>
            <textarea rows="6" cols="50" name="text" form="textPlusAdvice" class="form-control"
                      id="textT">${purchaseOrder.name}</textarea>
        </div>
        <div class="form-group">
            <button type="submit" name="operation" value="1" class="btn btn-default"><spring:message code="button.save"
                                                                                                     text="Save"/></button>

            <button type="submit" name="operation" value="2" class="btn btn-default">Добавить работу</button>
            <button type="submit" name="operation" value="3" class="btn btn-default">Добавить запчасть</button>
            <a href="<spring:url value="/service-panel/print-plan/${purchaseOrder.id}"/>" class="btn btn-default"
               target="_blank">
                Печать заказ-наряда
            </a>
            <a class="btn btn-default" href="<spring:url value="/service-panel/print-order/${purchaseOrder.id}"/>"
               target="_blank">
                <spring:message code="table.print" text="print"/>
            </a>
        </div>
        <div class="form-group">
            <label for="textP">Предоплата</label>
            <input type="number" name="prepayment" id="textP" class="form-control" value="${purchaseOrder.prepayment}"/>
        </div>
        <div class="form-group">
            <label for="textA"><spring:message code="printer.advices" text="Advices"/></label>
            <textarea name="advice" form="textPlusAdvice" class="form-control"
                      id="textA">${purchaseOrder.advice}</textarea>
        </div>

        <div class="form-group">
            <label for="textD">Скидка(%)</label>
            <input type="number" name="discount" id="textD" min="0" max="100" class="form-control"
                   value="${purchaseOrder.discount}"/>
            <label><input type="checkbox" name="discountWorks" value="true" ${purchaseOrder.discountWorksChecked}/>Скидка
                на работы</label>
            <label><input type="checkbox" name="discountParts" value="true" ${purchaseOrder.discountPartsChecked}/>Скидка
                на запчасти</label>
        </div>

        <div class="checkbox">
            <label><input type="checkbox" name="complete" id="textC" value="true" ${purchaseOrder.checked}/>Все работы
                выполнены</label>
        </div>
        <input type="hidden" name="worksSum" form="textPlusAdvice" class="form-control"
               value="${purchaseOrder.finalSum}"/>
        <input type="hidden" name="partsSum" form="textPlusAdvice" class="form-control"
               value="${purchaseOrder.finalSum}"/>

        <input type="hidden" name="finalSum" form="textPlusAdvice" class="form-control"
               value="${purchaseOrder.finalSum}"/>
        <input type="hidden" name="noFinalSum" form="textPlusAdvice" class="form-control" value="${purchaseOrder.sum}"/>


    </form:form>

    <div class="container">

        <p>Клиент ${purchaseOrder.client} | Телефон ${purchaseOrder.clientPhone} |
            Автомобиль ${purchaseOrder.carName}</p>

        <p>Менеджер ${purchaseOrder.managerName}</p>

        <p>Предоплата ${purchaseOrder.prepayment}</p>

        <p>Скидка ${purchaseOrder.discount}%</p>
    </div>
    <div class="container">
        <label>Таблица Работ</label>

        <table class="table table-hover">
            <tbody>
            <tr>
                <th><spring:message code="table.number" text="number"/></th>
                <th><spring:message code="table.master" text="master"/></th>
                <th><spring:message code="table.work" text="work"/></th>
                <th><spring:message code="table.price" text="price"/></th>
                <th><spring:message code="table.value" text="value"/></th>
                <th><spring:message code="table.sum" text="sum"/></th>
                <th><spring:message code="table.edit" text="edit"/></th>
            </tr>
            <c:forEach items="${purchaseOrder.webOrderWorkTable.webList}" var="work">
                <tr>
                    <td>${work.id}</td>
                    <td>${work.master}</td>
                    <td>${work.name}</td>
                    <td>${work.price}</td>
                    <td>${work.value}</td>
                    <td>${work.sum}</td>
                    <td>
                        <a href="<spring:url value="/service-panel/open/delete-work/${work.id}+${purchaseOrder.id}"/>">
                            <spring:message code="table.delete" text="delete"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="container">
        <label>Таблица запчастей</label>
        <table class="table table-hover">
            <tbody>
            <tr>
                <th><spring:message code="table.number" text="number"/></th>
                <th><spring:message code="table.part" text="part"/></th>
                <th><spring:message code="table.price" text="price"/></th>
                <th><spring:message code="table.value" text="value"/></th>
                <th><spring:message code="table.sum" text="sum"/></th>
                <th><spring:message code="table.edit" text="edit"/></th>
            </tr>
            <c:forEach items="${purchaseOrder.webOrderPartTable.webList}" var="part">
                <tr>
                    <td>${part.id}</td>
                    <td>${part.name}</td>
                    <td>${part.price}</td>
                    <td>${part.value}</td>
                    <td>${part.sum}</td>

                    <td>
                        <a href="<spring:url value="/service-panel/open/delete-part/${part.id}+${purchaseOrder.id}"/>">
                            <spring:message code="table.delete" text="delete"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="container">

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

</div>

</body>
</html>
