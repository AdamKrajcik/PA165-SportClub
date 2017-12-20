<%--
  Created by IntelliJ IDEA.
  User: skrovina
  Date: 20/12/2017
  Time: 7:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New team">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/team/create/${coachId}"
               modelAttribute="team" cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control" type="text" maxlength="25" minLength="3"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="ageCategory" cssClass="col-sm-2 control-label">Age Category</form:label>
            <div class="col-sm-10">
                <form:select path="ageCategory" cssClass="form-control">
                    <c:forEach items="${ageGroups}" var="ageGroup">
                        <form:option value="${ageGroup}">${ageGroup}</form:option>
                    </c:forEach>
                </form:select>

                <form:errors path="ageCategory" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Add Team</button>
    </form:form>


</jsp:attribute>
</my:pagetemplate>
