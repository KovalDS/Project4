<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>

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
			    <h1 class = "text-center"><fmt:message key = "my.account" bundle = "${bundle}"/></h1>
			</div>
			${requestScope.message}

			<div class = "row">
				<label class="control-label col-sm-2"><fmt:message key = "email" bundle = "${bundle}"/>:</label>
				<div class="col-sm-10">
					<p>${sessionScope.user.email}</p>
				</div>

			</div>
			<div class = "row">			
				<label class="control-label col-sm-2"><fmt:message key = "password" bundle = "${bundle}"/></label>
				<div class="col-sm-10">
					<p>${sessionScope.user.password}</p>
				</div>
			</div>
			<div class = "row">
				<label class="control-label col-sm-2"><fmt:message key = "first.name" bundle = "${bundle}"/></label>
				<div class="col-sm-10">
					<p>${sessionScope.user.firstName}</p>
				</div>
			</div>
			<div class = "row">
				<label class="control-label col-sm-2"><fmt:message key = "last.name" bundle = "${bundle}"/></label>
				<div class="col-sm-10">
					<p>${sessionScope.user.secondName}</p>
				</div>
			</div>
			<div class = "row">
				<label class="control-label col-sm-2"><fmt:message key = "status" bundle = "${bundle}"/>:</label>
				<div class="col-sm-10">
					<p>${sessionScope.user_role.name}</p>
				</div>
			</div>
			<div class = "row">			
				<label class="control-label col-sm-2"><fmt:message key = "balance" bundle = "${bundle}"/></label>
				<div class="col-sm-10">
					<p>
						$
						<c:set var = "balance" target = "user" property = "balance" value = "${user.balance/100}"/>
						<c:out value = "${balance}"/>
					</p>
				</div>
			</div>
			<hr>
			<form  action = "/my_account/update" method = "POST">
				<div class = "row">
					<label class="control-label col-sm-2 for="amount" style = "display: inline-block; vertical-align: middle; float: none;"><fmt:message key = "update.balance" bundle = "${bundle}"/>:</label>
					<input type = "text" name = "amount" id = "amount" placeholder = "<fmt:message key = 'amount' bundle = '${bundle}'/>">	
					<button type="submit" class="btn btn-default btn-success col-sm-offset-1"><fmt:message key = "update" bundle = "${bundle}"/></button>
				</div>
			</form>

			<hr>
		</div>
		
    </body>
</html>