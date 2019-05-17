<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 15.05.19
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Performers</title>
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
            <c:forEach var="band" items="${performers}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/songs/all/${band.performerId}">${band.performerName}</a>
                    </td>
                    <td>

                        <a class="btn btn-success"
                           href="${pageContext.request.contextPath}/performers/delete/${band.performerId}">Delete</a>
                    </td>
                    <td>
                        <a class="btn btn-success"
                           href="${pageContext.request.contextPath}/songs/${band.performerId}/new/">Add Song</a>

                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="btn-group btn-group-justified">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/performers/new">New performer</a>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/performers/all/free">Free performers</a>
</div>

</body>
</html>
