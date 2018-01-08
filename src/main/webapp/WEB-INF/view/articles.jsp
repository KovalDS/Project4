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
		<%@ include file="nav.jsp" %>

        <div class="container">
			<div class="page-header">
			    <h1 class = "text-center"><c:out value = "${requestScope.periodical.name}"/></h1>
			</div>
			<h3>Choose article, please</h3>
			<table class = "table table-hover">
				<thead>
					<tr>
						<th>Article name</th>
						<th>Date of publication</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items = "${requestScope.articles}" var = "article">
					<tr>
						<td><c:out value = "${article.name}"/></td>
						<td><c:out value = "${article.dateOfPublication}"/></td>
						<td >
						    <form action = "/periodical/article" class = "pull-right">
						        <input type = "hidden" value = "${article.id}" name = "article_id">
						        <button type = "submit" class = "btn btn-success" name = "command" value = "show_article_command">Read</button>
						    </form>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<c:if test = "${sessionScope.user_role.name eq 'admin'}">
				<form action = "/add_article" class = "pull-right">
					<input type = "hidden" value = "${requestScope.periodical.id}" name = "periodical_id">
					<button type = "submit" class = "btn btn-success btn-danger">Add article</button>
				</form>
			</c:if>
        </div>
    </body>
</html>