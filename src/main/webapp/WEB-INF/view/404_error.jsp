<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>404-error</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%@include file = "i18n.jsp"%>
        <div class = "container">
            <div class = "jumbotron">
                <h1><i class="fa fa-ban red"></i>Error 404 Page Not Found</h1>
                <p class="lead">Sorry! Page you are looking for doesn't exist</p>
                <p><a href = "/home" class="btn btn-default btn-lg">Homepage</a></p>
            </div>
        </div>
    </body>

</html>