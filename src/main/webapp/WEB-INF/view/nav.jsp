<%@include file = "i18n.jsp"%>

<nav class="navbar">
	<div class="container-fluid bg-info">
		<div class="navbar-header">
		  <a class="navbar-brand" href="/home">WebSiteName</a>
		</div>
		
		<ul class="nav navbar-nav navbar-right">
			<li class = "dropdown ${requestScope.dropdown_open}">
				<a href="#" data-toggle="dropdown" class = "dropdown-toggle">
					${sessionScope.basket_badge}
					<span class="glyphicon glyphicon-shopping-cart"></span>
				</a>
				<ul class = "dropdown-menu" id = "dropdown" style = "min-width:250; padding-right:5px; padding-left:5px;">
					<li class="bg-primary text-info text-center">
						<fmt:message key = "basket" bundle = "${bundle}"/> </li>
					<li class="divider"></li>
					<c:if test = "${sessionScope.basket eq null}">
					<li>
						<fmt:message key = "empty.basket" bundle = "${bundle}"/>
					</li>
					</c:if>
					<c:forEach items = "${sessionScope.basket}" var = "item">
						<li>
							<form action = "/delete_from_basket" method = "POST" class="form-horizontal">
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
							<fmt:message key = "total.price" bundle = "${bundle}"/>
							<c:set var = "total_price" target = "sessionScope.total_basket_price" value = "${sessionScope.total_basket_price/100}"/>
							<c:out value = "${total_price}"/>
							</span>
						</div>
					</li>
					<li class="divider"></li>
					<li>
						<form action = "/subscribe" method = "POST" class="form-horizontal" style = "margin-bottom:0;">
							<button type = "submit" class = "btn btn-success center-block"><fmt:message key = "checkout" bundle = "${bundle}"/></button>
						</form>
					</li>
				</ul>
			</li>
			
			<li class = "dropdown">
				<a href="#" data-toggle="dropdown" class = "dropdown-toggle">
					<fmt:message key = "language" bundle = "${bundle}"/>
				</a>
				<ul class = "dropdown-menu">
					<li><p style = "padding-top: 3px; padding-right: 20px; padding-bottom: 3px; padding-left: 20px;"><fmt:message key = "select.language" bundle = "${bundle}"/></p></li>
					<li class="divider"></li>
					<li><a href="/select_lang?language=en_US" >English</a></li>
					<li><a href="/select_lang?language=ru_RU" >&#x420;&#x443;&#x441;&#x441;&#x43A;&#x438;&#x439;</a></li>
				</ul>
			</li>
			
			<c:if test = "${sessionScope.user_role.name eq 'guest'}">
				<li><a href="#" data-toggle="modal" data-target="#register_modal"><span class="glyphicon glyphicon-user"></span> <fmt:message key = "sign.up" bundle = "${bundle}"/></a></li>
				<li><a href="#" data-toggle="modal" data-target="#login_modal"><span class="glyphicon glyphicon-log-in"></span> <fmt:message key = "login" bundle = "${bundle}"/></a></li>

				<div class="modal fade" id="login_modal" role="dialog">
					<div class="modal-dialog modal-sm">

						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title text-center"><fmt:message key = "login" bundle = "${bundle}"/></h4>
							</div>
							<div class="modal-body">
								<form action="/home/login" method = "POST">
									<div class="form-group">
										<label for="email"><fmt:message key = "email.address" bundle = "${bundle}"/></label>
										<input type="email" class="form-control" id="email" name = "email">
									</div>
									<div class="form-group">
										<label for="pwd"><fmt:message key = "password" bundle = "${bundle}"/></label>
										<input type="password" class="form-control" id="pwd" name = "password">
									</div>
									<button type="submit" class="btn btn-primary" name = "command" value = "login_command"><fmt:message key = "login" bundle = "${bundle}"/></button>
								</form>
								<p class = "text-danger text-center bg-danger" style = "border-radius: 2px;">${requestScope.login_message}</p>
							</div>
							<div class="modal-footer">
								<label for="register_link"><fmt:message key = "not.registered" bundle = "${bundle}"/> <a href="#" id = "register_link" data-toggle="modal" data-target="#register_modal" data-dismiss="modal"><fmt:message key = "sign.up" bundle = "${bundle}"/></a></label>
							</div>
						</div>

					</div>
				</div>


				<div class="modal fade" id="register_modal" role="dialog">
					<div class="modal-dialog modal-sm">

						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title text-center"><fmt:message key = "register" bundle = "${bundle}"/></h4>
							</div>
							<div class="modal-body">
								<form action="/home/register" method = "POST">
									<div class="form-group">
										<label for="email"><fmt:message key = "email.address" bundle = "${bundle}"/></label>
										<input type="email" class="form-control" id="email" name = "email">
									</div>
									<div class="form-group">
										<label for="pwd"><fmt:message key = "password" bundle = "${bundle}"/></label>
										<input type="password" class="form-control" id="pwd" name = "password">
									</div>
									<div class="form-group">
										<label for="fname"><fmt:message key = "first.name" bundle = "${bundle}"/></label>
										<input type="text" class="form-control" id="fname" name = "first_name">
									</div>
									<div class="form-group">
										<label for="sname"><fmt:message key = "last.name" bundle = "${bundle}"/></label>
										<input type="text" class="form-control" id="sname" name = "second_name">
									</div>
									<button type="submit" class="btn btn-primary " name = "command" value = "register_command"><fmt:message key = "sign.up" bundle = "${bundle}"/></button>
								</form>
								<p class = "text-danger text-center bg-danger" style = "border-radius: 2px;">${requestScope.register_message}</p>
							</div>
							<div class="modal-footer">
								<label for="login_link"><fmt:message key = "registered" bundle = "${bundle}"/> <a href="#" id = "login_link" data-toggle="modal" data-target="#login_modal" data-dismiss="modal"><fmt:message key = "login" bundle = "${bundle}"/></a></label>
							</div>
						</div>

					</div>
				</div>
			</c:if>
			
			<c:if test = "${sessionScope.user_role.name ne 'guest'}">
				<p class="navbar-text text-warning">
					<fmt:message key = "balance" bundle = "${bundle}"/>  $
					<c:set var = "balance" target = "user" property = "balance" value = "${user.balance/100}"/>
					<c:out value = "${balance}"/>
				</p>
				<li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> <fmt:message key = "logout" bundle = "${bundle}"/></a></li>
			</c:if>
		</ul>

		<c:if test = "${sessionScope.user_role.name ne 'guest'}">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/my_account"><fmt:message key = "my.account" bundle = "${bundle}"/></a></li>
				<li><a href="/my_subscriptions" ><fmt:message key = "my.subscriptions" bundle = "${bundle}"/> ${sessionScope.unread_articles_badge}</a></li>
				<c:if test = "${sessionScope.user_role.name eq 'admin'}">
					<li class="active"><a href="/add_periodical" class = "text-danger"><fmt:message key = "add.periodical" bundle = "${bundle}"/></a></li>
				</c:if>
			</ul>
		</c:if>
		

	</div>
</nav>
