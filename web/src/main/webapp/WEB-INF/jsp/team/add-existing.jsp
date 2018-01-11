<%--
  Created by IntelliJ IDEA.
  User: skrovina
  Date: 20/12/2017
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Add existing player to team '${team.name}'">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/team/add-existing/${team.id}"
               modelAttribute="rosterEntry" cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="player.email" cssClass="col-sm-2 control-label">Player</form:label>
            <div class="col-sm-10">
                <form:select path="player.email" cssClass="form-control">
                    <c:forEach items="${inviteEmails}" var="email">
                        <form:option value="${email}">${email}</form:option>
                    </c:forEach>
                </form:select>

                <form:errors path="player.email" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${jerseyNumber_error?'has-error':''}">
            <form:label path="jerseyNumber" cssClass="col-sm-2 control-label">Jersey number</form:label>
            <div class="col-sm-10">
                <form:input path="jerseyNumber" cssClass="form-control" type="number" min="1" max="99"/>
                <form:errors path="jerseyNumber" cssClass="help-block"/>
            </div>
        </div>


        <button class="btn btn-primary" type="submit">Add Player</button>
    </form:form>


</jsp:attribute>
</my:pagetemplate>
