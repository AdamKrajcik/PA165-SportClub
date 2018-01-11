<%--
  445403 Kristian Katanik
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Log In">
<jsp:attribute name="body">

    <div class="row">
    <c:if test="${not empty error}">
        <div class="error alert alert-danger">${error}</div>
    </c:if>
        <c:if test="${not empty msg}">
        <div class="msg alert alert-success">${msg}</div>
    </c:if>
    </div>


    <sec:authorize access="isAuthenticated()">
        <div class="row text-center">
            <div class="col-sm-6 col-sm-offset-3 col-xs-12 login-vertical-align">
                <i class="fa fa-user-circle-o fa-4" aria-hidden="true"></i>
                <p>You are currently logged in as user <b><sec:authentication property="principal.username" /></b>.</p>
                <p>To log out, use the button.</p>
                <form action="${pageContext.request.contextPath}/logout" method="get">
                    <button type="submit" class="btn btn-default navbar-btn">
                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                        Log out
                    </button>
                </form>
            </div>
        </div>
    </sec:authorize>

    <sec:authorize access="isAnonymous()">
        <form:form method="post" action="${pageContext.request.contextPath}/login" cssClass="form-horizontal">

            <div class="form-group">
                <label class="control-label semantic" for="username" style=""></label>
                <div class="input-group">
            <span class="input-group-addon">
                <i class="fa fa-user" aria-hidden="true"></i>
            </span>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Email" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label semantic" for="password"></label>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-lock" aria-hidden="true"></i>
                    </span>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                </div>
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />


            <button class="btn btn-primary" type="submit">Log In</button>
        </form:form>
</sec:authorize>


</jsp:attribute>
</my:pagetemplate>
