<%--
  Created by IntelliJ IDEA.
  User: Jan Cech
  Date: 19.12.2017
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate title="Update player">
<jsp:attribute name="body">


    <form:form method="post" action="${pageContext.request.contextPath}/player/edit"
               modelAttribute="player" cssClass="form-horizontal">
        <form:hidden path="id"/>
        <div class="form-group ${firstName_error?'has-error':''}">
            <form:label path="firstName" cssClass="col-sm-2 control-label">First name</form:label>
            <div class="col-sm-10">
                <form:input path="firstName" cssClass="form-control"/>
                <form:errors path="firstName" cssClass="help-block"/>
                <c:out value="${player.firstName}"/>
            </div>
        </div>
        <div class="form-group ${lastName_error?'has-error':''}">
            <form:label path="lastName" cssClass="col-sm-2 control-label">Last name</form:label>
            <div class="col-sm-10">
                <form:input path="lastName" cssClass="form-control"/>
                <form:errors path="lastName" cssClass="help-block"/>
                <c:out value="${player.lastName}"/>
            </div>
        </div>
        <div class="form-group ${height_error?'has-error':''}">
            <form:label path="height" cssClass="col-sm-2 control-label">Height</form:label>
            <div class="col-sm-10">
                <form:input path="height" cssClass="form-control" type="number" min="80" max="280"/>
                <form:errors path="height" cssClass="help-block"/>
                <c:out value="${player.height}"/>
            </div>
        </div>
        <div class="form-group ${weight_error?'has-error':''}">
            <form:label path="weight" cssClass="col-sm-2 control-label">Weight</form:label>
            <div class="col-sm-10">
                <form:input path="weight" cssClass="form-control" type="number" min="30" max="150"/>
                <form:errors path="weight" cssClass="help-block"/>
                <c:out value="${player.weight}"/>
            </div>
        </div>




        <button class="btn btn-primary" type="submit">Update player</button>
    </form:form>


</jsp:attribute>
</my:pagetemplate>
