<%--
  Created by IntelliJ IDEA.
  User: kkata
  Date: 18.12.2017
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Coaches">
<jsp:attribute name="body">

    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
    <my:a href="/coach/create" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New coach
    </my:a>
    </sec:authorize>

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>Teams</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${coaches}" var="coach">
            <tr>
                <td>${coach.id}</td>
                <td><c:out value="${coach.firstName}"/></td>
                <td><c:out value="${coach.lastName}"/></td>
                <td><c:out value="${coach.email}"/></td>
                <c:choose>
                    <c:when test="${coach.teams.size()==0}">
                        <td><c:out value="-"/></td>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${coach.teams}" var="team">
                            <td><c:out value="${team.name}"/></td>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <td>
                    <my:a href="/coach/view/${coach.id}" class="btn btn-primary">View</my:a>
                </td>
                <td>
                    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <form method="get" action="${pageContext.request.contextPath}/coach/update/${coach.id}">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                    </sec:authorize>
                </td>
                <td>
                    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <form method="post" action="${pageContext.request.contextPath}/coach/delete/${coach.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
