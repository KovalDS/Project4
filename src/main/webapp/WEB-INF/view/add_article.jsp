<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
					Create new article
				</h1>
			</div>
			${requestScope.message}
			<form class = "form-horizontal" action = "/add_article/create_article" method = "POST">
				<div class="form-group row">
					<label class="control-label col-sm-2" for="name">Articlel name:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="name" placeholder="Enter name" required name = "article_name">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="description">Text:</label>
					<div class="col-sm-10">
						<textarea class="form-control" id="description" rows="20" placeholder= "Enter description" required name = "article_text"></textarea>
					</div>
				</div>
				<div class="form-group"> 
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default btn-danger">Create</button>
					</div>
				</div>
			</form>
        </div>
    </body>
</html>