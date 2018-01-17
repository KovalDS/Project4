<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>

<html>
    <head>
        <title>Add article</title>

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
					<fmt:message key = "create.article" bundle = "${bundle}"/>
				</h1>
			</div>
			${requestScope.message}
			<form class = "form-horizontal" action = "/add_article/create_article" method = "POST">
				<div class="form-group">
					<label class="control-label col-sm-2" for="periodical_name"><fmt:message key = "periodical" bundle = "${bundle}"/>:</label>
					<div class="col-sm-10">
						<p class="form-control-static">${requestScope.periodical.name}</p>
					</div>
				</div>
				<div class="form-group row">
					<label class="control-label col-sm-2" for="name"><fmt:message key = "article.name" bundle = "${bundle}"/>:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="name" placeholder="<fmt:message key = 'enter.name' bundle = '${bundle}'/>" required name = "article_name">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="description"><fmt:message key = "text" bundle = "${bundle}"/>:</label>
					<div class="col-sm-10">
						<textarea class="form-control" id="description" rows="20" placeholder= "<fmt:message key = 'enter.text' bundle = '${bundle}'/>" required name = "article_text"></textarea>
					</div>
				</div>
				<div class="form-group"> 
					<div class="col-sm-offset-2 col-sm-10">
						<input type = "hidden" value = "${requestScope.periodical.id}" name = "periodical_id">
						<button type="submit" class="btn btn-default btn-danger"><fmt:message key = "create" bundle = "${bundle}"/></button>
					</div>
				</div>
			</form>
        </div>
    </body>
</html>