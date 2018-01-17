<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <title>Add Periodical</title>

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
			    <h1 class = "text-center">
					<fmt:message key = "create.periodical" bundle = "${bundle}"/>
				</h1>
			</div>
			${requestScope.message}
			<form class = "form-horizontal" action = "/add_periodical/create_periodical" method = "POST">
				<div class="form-group row">
					<label class="control-label col-sm-2" for="name"><fmt:message key = "periodical.name" bundle = "${bundle}"/>:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="name" placeholder="<fmt:message key = 'enter.name' bundle = '${bundle}'/>" required name = "periodical_name">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="description"><fmt:message key = "description" bundle = "${bundle}"/>:</label>
					<div class="col-sm-10">
						<textarea class="form-control" id="description" rows="5" placeholder= "<fmt:message key = 'enter.description' bundle = '${bundle}'/>" required name = "periodical_description"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="price"><fmt:message key = "price" bundle = "${bundle}"/> ($):</label>
					<div class="col-sm-2">
						<input type="number" class="form-control" id="price" required name = "periodical_price"  step = "any">
					</div>
				</div>
				<div class="form-group"> 
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default btn-danger"><fmt:message key = "create" bundle = "${bundle}"/></button>
					</div>
				</div>
			</form>
        </div>
    </body>
</html>