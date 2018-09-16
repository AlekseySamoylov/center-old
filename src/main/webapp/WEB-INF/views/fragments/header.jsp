<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" href="<spring:url value="/"/>"><spring:message code="header.repaircenter" text="Repair Center"/> </a>
        </div>

        <ul class="nav navbar-nav">

            <li><a href="<spring:url value="/"/>"><spring:message code="header.home" text="Home"/></a></li>

            <sec:authorize access="${isManager}">
                <li><a href="<spring:url value="/service-panel/"/>"><spring:message code="header.servicepanel" text="Service Panel"/></a></li>
            </sec:authorize>

            <sec:authorize access="${isAdmin}">
                <li><a href="<spring:url value="/admin"/>"><spring:message code="header.admin" text="Admin"/></a></li>
            </sec:authorize>
            <sec:authorize access="${isClient}">
                <li><a href="#"><spring:message code="header.mycabinet" text="My Cabinet"/></a></li>
            </sec:authorize>
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.authenticated}">
                    <li>
                        <p class="navbar-text">
                            <spring:message code="header.welcome" text="Welcome"/>
                            <a href="<spring:url value="/user-page"/>"><sec:authentication property="name"/></a>
                            <a href="<spring:url value="/logout"/>"><spring:message code="header.logout" text="Logout"/></a>
                        </p>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<spring:url value="/login/"/>"><spring:message code="header.signin" text="Sign In"/></a></li>
                </c:otherwise>
            </c:choose>

        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="<%=request.getContextPath()%>?languageVar=en">EN</a></li>
            <li><a href="<%=request.getContextPath()%>?languageVar=ru">RU</a></li>
            <li><a href="<%=request.getContextPath()%>?languageVar=zh_CN">CN</a></li>
        </ul>
    </div>
</nav>