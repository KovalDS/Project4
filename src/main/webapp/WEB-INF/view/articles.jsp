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
					<li class = "dropdown ${requestScope.dropdown_open}">
						<a href="#" data-toggle="dropdown" class = "dropdown-toggle">
							${sessionScope.basket_badge}
							<span class="glyphicon glyphicon-shopping-cart"></span>
						</a>
						<ul class = "dropdown-menu" id = "dropdown" style = "min-width:250; padding-right:5px; padding-left:5px;">
							<li class="bg-primary text-info text-center">Basket </li>
							<li class="divider"></li>
							<c:if test = "${sessionScope.basket eq null}">
							<li>
								Your basket is empty
							</li>
							</c:if>
							<c:forEach items = "${sessionScope.basket}" var = "item">
								<li>
									<form action = "/" method = "POST" class="form-horizontal">
										<span><c:out value = "${item.name}"/></span>
										<input type = "hidden" value = "${item.id}" name = "periodical_id">
										
										<button type="submit" class="btn btn-xs btn-danger pull-right" aria-label="Close" name = "command" value = "delete_from_basket"><span aria-hidden="true">&times;</span></button>
										<span class = "pull-right">$
											<c:set var = "price" target = "item" property = "price" value = "${item.price/100}"/>
											<c:out value = "${price}"/>
											&nbsp;
										</span>
									</form>
								</li>
							</c:forEach>
							<li class="divider"></li>
							<li>
								<div class = "row" style = "margin-right:0px; margin-lef:0px">
									<span class = "pull-right bg-info">
									Total price: $ 
									<c:set var = "total_price" target = "sessionScope.total_basket_price" value = "${sessionScope.total_basket_price/100}"/>
									<c:out value = "${total_price}"/>
									</span>
								</div>
							</li>
							<li class="divider"></li>
							<li>
								<form action = "/" method = "POST" class="form-horizontal" style = "margin-bottom:0;">
									<button type = "submit" class = "btn btn-success center-block" name = "command" value = "subscribe_command">Checkout</button>
								</form>
							</li>
							
						</ul>
					</li>
				
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
						    <form action = "/" class = "pull-right">
						        <input type = "hidden" value = "${article.id}" name = "article_id">
						        <button type = "submit" class = "btn btn-success" name = "command" value = "show_article_command">Read</button>
						    </form>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
        </div>
    </body>
</html>