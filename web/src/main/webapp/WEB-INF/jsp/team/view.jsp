<%--
  Created by IntelliJ IDEA.
  User: skrovina
  Date: 19/12/2017
  Time: 12:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="${team.name}">
<jsp:attribute name="body">
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Coach's email</th>
            <th>Age category</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${team.id}</td>
            <td><c:out value="${team.name}"/></td>
            <td><c:out value="${team.coach.email}"/></td>
            <td><c:out value="${team.ageCategory}"/></td>
        </tr>
        </tbody>
    </table>
    <%--<my:a href="/team/edit/${team.id}" class="btn btn-default">--%>
        <%--<span class="" aria-hidden="true"></span>--%>
        <%--Edit Team--%>
    <%--</my:a>--%>

    <h2>Players</h2>
        <my:a href="/player/create" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Create New
        </my:a>
        <my:a href="/team/add-existing/${team.id}" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add Existing
        </my:a>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Jersey Number</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rosterEntries}" var="rosterEntry">
            <tr>
                <td>${rosterEntry.id}</td>
                <td>${rosterEntry.player.firstName}</td>
                <td>${rosterEntry.player.lastName}</td>
                <td>${rosterEntry.player.email}</td>
                <td>${rosterEntry.jerseyNumber}</td>
                <td>
                    <form method="get"
                          action="${pageContext.request.contextPath}/player/view/${rosterEntry.player.id}">
                        <button type="submit" class="btn btn-default">Show Info</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/team/remove-player/${team.id}/${rosterEntry.player.id}">
                        <button type="submit" class="btn btn-danger">Remove</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <td>
            <form method="get" action="${pageContext.request.contextPath}/team/list">
                <button type="submit" class="btn btn-primary">Return to teams</button>
            </form>
        </td>
</jsp:attribute>
</my:pagetemplate>
