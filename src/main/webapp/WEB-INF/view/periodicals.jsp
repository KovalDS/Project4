<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Home</title>

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
					<li class="active"><a href="/">Home</a></li>
					<li><a href="/" >Page 1</a></li>
					<li><a href="/" >Page 2</a></li>
					<li><a href="/" >Page 3</a></li>
				</ul>

				<c:if test = "${sessionScope.user_role.name eq 'guest'}">
					<ul class="nav navbar-nav navbar-right">
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
										<form action="/" method = "POST">
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
										<form action="/" method = "POST">
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



					</ul>
				</c:if>

				<c:if test = "${sessionScope.user_role.name ne 'guest'}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/?command=logout_command"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
					</ul>
				</c:if>
			</div>
        </nav>

        <div class="container">

            <c:forEach items = "${sessionScope.periodical_list}" var = "periodical">
				<div>
					<h2><c:out value = "${periodical.name}"/></h2>
					<hr/>
					<div>
					    <c:out value = "${periodical.description}"/>
					</div><br/>
					<form action="/">
						<div>
							<span class = "text-warning lead">Subscribe now for $
								<c:set var = "price" target = "periodical" property = "price" value = "${periodical.price/100}"/>
								<c:out value = "${price}"/>
							</span>
							<button type="submit" class="btn btn-info pull-right">Subscribe</button>
						</div>
					</form>
				</div>
            </c:forEach>

            <c:out value = "${requestScope.message}"/>
        </div>
    </body>
</html>
