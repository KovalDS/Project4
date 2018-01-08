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
					<li class="bg-primary text-info text-center">Basket </li>
					<li class="divider"></li>
					<c:if test = "${sessionScope.basket eq null}">
					<li>
						Your basket is empty
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
							Total price: $ 
							<c:set var = "total_price" target = "sessionScope.total_basket_price" value = "${sessionScope.total_basket_price/100}"/>
							<c:out value = "${total_price}"/>
							</span>
						</div>
					</li>
					<li class="divider"></li>
					<li>
						<form action = "/subscribe" method = "POST" class="form-horizontal" style = "margin-bottom:0;">
							<button type = "submit" class = "btn btn-success center-block" name = "command" value = "subscribe_command">Checkout</button>
						</form>
					</li>
				</ul>
			</li>
			
			<c:if test = "${sessionScope.user_role.name eq 'guest'}">
				<li><a href="#" data-toggle="modal" data-target="#register_modal"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li><a href="#" data-toggle="modal" data-target="#login_modal"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>

				<div class="modal fade" id="login_modal" role="dialog">
					<div class="modal-dialog modal-sm">

						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title text-center">Login</h4>
							</div>
							<div class="modal-body">
								<form action="/home/login" method = "POST">
									<div class="form-group">
										<label for="email">Email address:</label>
										<input type="email" class="form-control" id="email" name = "email">
									</div>
									<div class="form-group">
										<label for="pwd">Password:</label>
										<input type="password" class="form-control" id="pwd" name = "password">
									</div>
									<button type="submit" class="btn btn-primary" name = "command" value = "login_command">Login</button>
								</form>
							</div>
							<div class="modal-footer">
								<label for="register_link">Don't have account? <a href="#" id = "register_link" data-toggle="modal" data-target="#register_modal" data-dismiss="modal">Sign Up</a></label>
							</div>
						</div>

					</div>
				</div>


				<div class="modal fade" id="register_modal" role="dialog">
					<div class="modal-dialog modal-sm">

						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title text-center">Register</h4>
							</div>
							<div class="modal-body">
								<form action="/home/register" method = "POST">
									<div class="form-group">
										<label for="email">Email address:</label>
										<input type="email" class="form-control" id="email" name = "email">
									</div>
									<div class="form-group">
										<label for="pwd">Password:</label>
										<input type="password" class="form-control" id="pwd" name = "password">
									</div>
									<div class="form-group">
										<label for="fname">First Name:</label>
										<input type="text" class="form-control" id="fname" name = "first_name">
									</div>
									<div class="form-group">
										<label for="sname">Second Name:</label>
										<input type="text" class="form-control" id="sname" name = "second_name">
									</div>
									<button type="submit" class="btn btn-primary " name = "command" value = "register_command">Sign up</button>
								</form>
							</div>
							<div class="modal-footer">
								<label for="login_link">Already registered? <a href="#" id = "login_link" data-toggle="modal" data-target="#login_modal" data-dismiss="modal">Login</a></label>
							</div>
						</div>

					</div>
				</div>
			</c:if>
			
			<c:if test = "${sessionScope.user_role.name ne 'guest'}">
				<p class="navbar-text text-warning">
					Balance: $
					<c:set var = "balance" target = "user" property = "balance" value = "${user.balance/100}"/>
					<c:out value = "${balance}"/>
				</p>
				<li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</c:if>
		</ul>

		<c:if test = "${sessionScope.user_role.name ne 'guest'}">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/">My account</a></li>
				<li><a href="/" >My subscriptions</a></li>
				<c:if test = "${sessionScope.user_role.name eq 'admin'}">
					<li class="active"><a href="/add_periodical" class = "text-danger">Add periodical</a></li>
				</c:if>
			</ul>
		</c:if>
		

	</div>
</nav>
