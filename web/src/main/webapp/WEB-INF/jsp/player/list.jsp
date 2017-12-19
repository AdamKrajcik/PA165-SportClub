<%--
  @445403 Kristian Katanik
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Players">
<jsp:attribute name="body">

    <my:a href="/player/create" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New player
    </my:a>

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
            <th>Number of rosters</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${players}" var="player">
            <tr>
                <td>${player.id}</td>
                <td><c:out value="${player.firstName}"/></td>
                <td><c:out value="${player.lastName}"/></td>
                <td><c:out value="${player.email}"/></td>
                <td><c:out value="${player.height}"/></td>
                <td><c:out value="${player.weight}"/></td>
                <td><c:out value="${player.dateOfBirth.toLocaleString()}"/></td>
                <td><c:out value="${player.rosterEntries.size()}"/></td>
                <td>
                    <my:a href="/player/view/${player.id}" class="btn btn-primary">View</my:a>
                </td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/player/update/${player.id}">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/player/delete/${player.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
