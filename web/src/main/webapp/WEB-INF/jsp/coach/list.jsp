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

<my:pagetemplate title="Coaches">
<jsp:attribute name="body">

    <my:a href="/coach/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New coach
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>email</th>
            <th>teams</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${coaches}" var="coach">
            <tr>
                <td>${coach.id}</td>
                <td><c:out value="${coach.firstName}"/></td>
                <td><c:out value="${coach.lastName}"/></td>
                <td><c:out value="${coach.email}"/></td>
                <td>
                    <my:a href="/coach/view/${coach.id}" class="btn btn-primary">View</my:a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/coach/delete/${coach.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
