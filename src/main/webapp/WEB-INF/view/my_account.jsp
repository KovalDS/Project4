<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>My Account</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>


    <body>
		<%@ include file="nav.jsp" %>

        <div class="container">
			<div class="page-header">
			    <h1 class = "text-center">My account</h1>
			</div>
			${requestScope.article.text}

			<div>
				<label class="control-label col-sm-2">Email:</label>
				<div class="col-sm-10">
					<p>${sessionScope.user.email}</p>
				</div>

			</div>
			<div>			
				<label class="control-label col-sm-2">Password:</label>
				<div class="col-sm-10">
					<p>${sessionScope.user.password}</p>
				</div>
			</div>
			<div>
				<label class="control-label col-sm-2">First name:</label>
				<div class="col-sm-10">
					<p>${sessionScope.user.firstName}</p>
				</div>
			</div>
			<div>
				<label class="control-label col-sm-2">Second name:</label>
				<div class="col-sm-10">
					<p>${sessionScope.user.secondName}</p>
				</div>
			</div>
			<div>
				<label class="control-label col-sm-2">Status:</label>
				<div class="col-sm-10">
					<p>${sessionScope.user_role.name}</p>
				</div>
			</div>
		</div>
    </body>
</html>