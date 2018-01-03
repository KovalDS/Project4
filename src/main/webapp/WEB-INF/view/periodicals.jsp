<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Home</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>

    <body>
        <nav class="navbar">
            <div class="container-fluid bg-info">
                <div class="navbar-header">
                  <a class="navbar-brand" href="#">WebSiteName</a>
                </div>

            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#" >Page 1</a></li>
                <li><a href="#" >Page 2</a></li>
                <li><a href="#" >Page 3</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
          </div>
        </nav>

        <div class="container">
                    <div class = "page-header text-center">
                        <h2>All images</h2>
                    </div>

            <c:forEach items = "${requestScope.periodical_list}" var = "periodical">
                <c:out value = "${periodical.name}"/>
                <c:out value = "${periodical.name}"/>
                <c:out value = "${periodical.name}"/>
                <c:out value = "${periodical.name}"/>
            </c:forEach>
        </div>
    </body>
</html>
