<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 17.05.19
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>
<header class="well well-sm">
    <div align="right" style="padding-right: 5%">
        <form:form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" class="btn btn-success" value="Log Out"/>
        </form:form>
    </div>
</header>
<div class="container">
    <div class="jumbotron">
        <div class="btn-group btn-group-justified">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/performers/all">All performers</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/concerts/all">Concerts</a>
        </div>
    </div>
</div>
</body>
</html>
