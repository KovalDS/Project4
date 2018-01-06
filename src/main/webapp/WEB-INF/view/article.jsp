<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Articles</title>

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
                  <a class="navbar-brand" href="/">WebSiteName</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/">My account</a></li>
                    <li><a href="/" >My subscriptions</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <p class="navbar-text text-warning">
                        Balance: $
                        <c:set var = "balance" target = "user" property = "balance" value = "${user.balance/100}"/>
                        <c:out value = "${balance}"/>
                    </p>
                    <li><a href="/?command=logout_command"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
			</div>
        </nav>

        <div class="container">
			<div class="page-header">
			    <h1 class = "text-center"><c:out value = "${requestScope.article.name}"/></h1>
			</div>
			${requestScope.article.text}
        </div>
    </body>
</html>