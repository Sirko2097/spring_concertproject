<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 17.05.19
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Concerts</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<header class="well well-sm">
    <div align="right" style="padding-right: 5%">
        <form:form action="${pageContext.request.contextPath}/logout" method="post">
            <div class="btn-group">
                <a class="btn btn-success" href="${pageContext.request.contextPath}/">Home Page</a>
                <input type="submit" class="btn btn-success" value="Log Out"/>
            </div>
        </form:form>
    </div>
</header>
<div class="container">
    <div class="jumbotron">
        <table class="table table-hover">
            <c:forEach var="concert" items="${concerts}">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/concerts/all/${concert.concertId}">
                            ${concert.concertName}</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/concerts/${concert.concertId}/editdate/">${concert.concertDate}</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/performers/all/${concert.concertId}">Performers from
                        Concert</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/concerts/delete/${concert.concertId}">Delete</a>
                </td>
            </tr>
            </c:forEach>
</body>
</html>
