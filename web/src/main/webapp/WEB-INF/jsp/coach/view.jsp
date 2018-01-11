<%--
  445403 Kristian Katanik
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Coach view">
<jsp:attribute name="body">

    <h1>Detail:</h1>

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${coach.id}</td>
            <td><c:out value="${coach.firstName}"/></td>
            <td><c:out value="${coach.lastName}"/></td>
            <td><c:out value="${coach.email}"/></td>
        </tr>
        </tbody>
    </table>

    <h2>Managed teams:</h2>
        <my:a href="/team/create/${coach.id}" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add New Team
        </my:a>

        <c:choose>
            <c:when test="${coach.teams.size()==0}">
                <td><c:out value="Coach has no teams"/></td>
            </c:when>
            <c:otherwise>
                <table class="table">
                                 <thead>
                                 <tr>
                                     <th>Team name</th>
                                     <th>Age group</th>
                                 </tr>
                                 </thead>
                                 <tbody>
                                 <tr>
                            <c:forEach items="${coach.teams}" var="team">
                                     <td><c:out value="${team.name}"/></td>
                                     <td><c:out value="${team.ageCategory}"/></td>
                                <td>
                                    <form method="get" action="${pageContext.request.contextPath}/team/view/${team.id}">
                                        <button type="submit" class="btn btn-primary">View</button>
                                    </form>
                                </td>
                                <td>
                                    <form method="get" action="${pageContext.request.contextPath}/team/delete/${team.id}">
                                        <button type="submit" class="btn btn-primary">Delete</button>
                                    </form>
                                </td>
                                 </tr>
                        </c:forEach>
                                 </tbody>
                            </table>
                    </c:otherwise>
        </c:choose>
        <td>
            <form method="get" action="${pageContext.request.contextPath}/coach/list">
                <button type="submit" class="btn btn-primary">Return to coaches</button>
            </form>
        </td>



</jsp:attribute>
</my:pagetemplate>