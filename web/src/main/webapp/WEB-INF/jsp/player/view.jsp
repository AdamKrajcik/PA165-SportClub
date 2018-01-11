<%--
  445403 Kristian Katanik
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Player view">
<jsp:attribute name="body">

    <h1>Detail:</h1>

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>Height</th>
            <th>Weight</th>
            <th>Date of birth</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${player.id}</td>
            <td><c:out value="${player.firstName}"/></td>
            <td><c:out value="${player.lastName}"/></td>
            <td><c:out value="${player.email}"/></td>
            <td><c:out value="${player.height}"/></td>
            <td><c:out value="${player.weight}"/></td>
            <td><c:out value="${player.localDate}"/></td>
        </tr>
        </tbody>
    </table>

    <h2>Rosters:</h2>
        <c:choose>
            <c:when test="${player.rosterEntries.size()==0}">
                <td><c:out value="Player has no Rosters"/></td>
            </c:when>
            <c:otherwise>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Team name</th>
                        <th>Player's jersey number</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:forEach items="${player.rosterEntries}" var="rosterEntry">
                        <td><c:out value="${rosterEntry.team.name}"/></td>
                        <td><c:out value="${rosterEntry.jerseyNumber}"/></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                    </c:otherwise>
        </c:choose>
        <td>
            <form method="get" action="${pageContext.request.contextPath}/player/list">
                <button type="submit" class="btn btn-primary">Return to players</button>
            </form>
        </td>




</jsp:attribute>
</my:pagetemplate>