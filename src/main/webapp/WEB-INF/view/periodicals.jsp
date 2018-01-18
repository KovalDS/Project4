<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="func" uri="taglib" %>

<html>
    <head>
        <title>Home</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
			$("document").ready(function() {
			  $('.dropdown-menu').on('click', function(e) {
				e.stopPropagation();
			  });
			});
			
			$(document).ready(function(){
				${requestScope.show_login_modal}
			});
			
			$(document).ready(function(){
				${requestScope.show_register_modal}
			});
		</script>
    </head>

    <body>
		<%@ include file="nav.jsp" %>

        <div class="container">
			${requestScope.message}
            <c:forEach items = "${requestScope.periodical_list}" var = "periodical">
				<div>
					<h2>
						<c:out value = "${periodical.name}"/>
						<c:if test = "${func:hasUnreadArticles(sessionScope.unread_articles, periodical.articles)}">
							<small><span class = "label label-danger"> <fmt:message key = "new.articles" bundle = "${bundle}"/></span></small>
						</c:if>
					</h2>
					<hr/>
					<div>
					    <c:out value = "${periodical.description}"/>
					</div><br/>
					<div>
						<c:choose>
							<c:when test = "${func:containsPeriodical(requestScope.available_periodicals, periodical)}">
								<form action = "/periodical" method = "GET">
									<input type = "hidden" value = "${periodical.id}" name = "periodical_id">
									<button type="submit" class="btn btn-success pull-right"><fmt:message key = "read" bundle = "${bundle}"/></button>
								</form>
							</c:when>
							<c:otherwise>
								<form action = "/add_to_basket" method = "POST">
									<span class = "text-warning lead"><fmt:message key = "subscribe.now" bundle = "${bundle}"/>
										<c:set var = "price" target = "periodical" property = "price" value = "${periodical.price/100}"/>
										<c:out value = "${price}"/>
									</span>
									<input type = "hidden" value = "${periodical.id}" name = "periodical_id">
									<button type="submit" class="btn btn-info pull-right"><fmt:message key = "subscribe" bundle = "${bundle}"/></button>
								</form>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
            </c:forEach>

			<div class = "text-center">
				<ul class = "pagination">
					<c:forEach items = "${requestScope.pages}" var = "page">
						<c:choose>
							<c:when test = "${page == requestScope.current_page}">
								<li class="active"><a href="/home?periodicals_page=${page}">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/home?periodicals_page=${page}">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
			${catalina.base}
        </div>
    </body>
</html>
