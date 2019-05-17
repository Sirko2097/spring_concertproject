<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sirko
  Date: 17.05.19
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Concert</title>
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
        <form:form action="confirm" modelAttribute="concert" method="post" class="form-horizontal">
            <label>Input name: </label><form:input path="concertName"/>
            <form:errors path="concertName"/>
            <br>
            <label>Input Date: </label><form:input path="concertDate" type="date"/>
            <br>
            <label>Break:</label><br>
            <div class="radio">
                <label><form:radiobutton path="breakId" value="1"/>Small</label>
                <br>
                <label><form:radiobutton path="breakId" value="2"/>Big</label>
                <br>
            </div>
            <label>Announcer:</label><br>
            <div class="radio">
                <label><form:radiobutton path="announcerId" value="1"/>Ryan Reynolds </label>
                <br>
                <label><form:radiobutton path="announcerId" value="2"/>Jimmy Kimmel </label>
            </div>
            <input type="submit" class="btn btn-primary" value="Submit">
        </form:form>

    </div>
</div>
</body>
</html>
