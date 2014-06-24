<%@page import="webPackage.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="managers.PostManager"%>
<%@page import="webPackage.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en-US">
<!--<![endif]-->


<head>
<!-- Meta Tags -->
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<!-- Title, Keywords and Description -->
<title>User login</title>


<link rel="profile" href="http://gmpg.org/xfn/11" />
<link rel="shortcut icon"
	href="http://joinwebs.com/beetube/wp-content/themes/beetube/images/favicon.ico" />
<link rel="pingback" href="../xmlrpc.php" />

<!-- Generated CSS BEGIN -->
<style type='text/css'>
body {
	background: #EEE url("wp-content/themes/beetube/images/bg-pattern.png")
		repeat center top fixed !important;
}

.info-less {
	height: 100px;
}
</style>
<!-- Generated CSS END -->
<style>
#main-nav {
	background: #444 !important;
}

#main-nav ul li ul {
	background: #444 !important
}
</style>
<link rel='stylesheet' id='jtheme-fonts-css'
	href='http://fonts.googleapis.com/css?family=Oxygen&amp;ver=3.9.1'
	type='text/css' media='all' />
<link rel='stylesheet' id='jtheme-mainstyle-css'
	href='wp-content/themes/beetube/css/stylesheet-red370e.css?ver=1.4.3'
	type='text/css' media='all' />
<link rel='stylesheet' id='jtheme-itotope-css'
	href='wp-content/themes/beetube/css/jquery.itoppage370e.css?ver=1.4.3'
	type='text/css' media='all' />
<link rel='stylesheet' id='jtheme-res-nav-css'
	href='wp-content/themes/beetube/css/responsive-nav370e.css?ver=1.4.3'
	type='text/css' media='all' />
<meta name="generator" content="WordPress 3.9.1" />

</head>

<body
	class="page page-id-446 page-template page-template-log-reg-php full-wrap">
	<div id="page">
		<header id="header">
			<div id="top-nav">
				<div class="wrap cf">

					<div id="header-search">
						<ul>
							<%
								User user = (User) request.getSession().getAttribute("currentUser");
								//User user = new User("user1");
								if (user == null) {
							%>
							<li class="acti1"><a href="login.jsp"><span>Are
										You New? </span> Register</a></li>
							<li class="acti2"><a href="login.jsp">Login</a></li>
						</ul>
						<%
							} else {
						%>
						<ul>
							<li class="acti1"><a href=<%="user.jsp?id=" + user.getID()%>>Hello,
									<%=user.getID()%></a></li>
							<li class="acti2"><form name="Test" action="LogoutServlet"
									method="post">
									<script type="text/javascript">
										function update() {
											Test.submit();
										}
									</script>
									<a href="javascript:update()">Logout</a>
								</form></li>
						</ul>
						<%
							}
						%>
					</div>
					<!-- end #header-search -->
					<!-- end #Top-nav -->
					<div class="clear"></div>
				</div>
			</div>

			<div class="header-secend">
				<div class="wrap cf">
					<div id="branding" class="image-branding" role="banner">
						<h1 id="site-title">
							<a rel="home" href="index.html">Catastrophe.ge</a>
						</h1>

						<a id="site-logo" rel="home" href="index.html"><img
							src="images/logo.png" alt="Catastrophe.ge" /></a>

						<h2 id="site-description" class="hidden">Catastrophe</h2>
					</div>
					<!-- end #branding -->
				</div>
			</div>
		</header>
		<!-- end #header-->

		<div id="main-nav">
			<div class="wrap cf">

				<ul id="menu-main" class="menu">
					<li id="menu-item" class="menu-item"><a href="index.jsp">Home</a>
					<li id="menu-item" class="menu-item"><a href="index.jsp">Popular
							Videos</a></li>
					<li id="menu-item" class="menu-item"><a href="recent.jsp">Recent
							Videos</a></li>
					<li id="menu-item" class="menu-item"><a href="shop.jsp">CP
							Shop</a></li>
				</ul>
			</div>
		</div>
		<!-- end #main-nav -->






		<div class="wrap cf ">

			<div class="user-container">


				<div class="login-container left">
					<div class="login">

						<h2 class="form-heading">LOGIN FORM</h2>
						<p></p>

						<form method="post" action="LoginServlet" class="wp-user-form">
							<div class="form-group">
								<label for="user_login">Username</label> <input type="text"
									class="form-control" id="user_login" placeholder="Enter email"
									name="name" value="" tabindex="100" />
							</div>
							<div class="form-group">
								<label for="user_pass">Password</label> <input type="password"
									class="form-control" placeholder="Password" name="pwd" value=""
									id="user_pass" tabindex="101">
							</div>

							<div class="login_fields">
								<input type="submit" name="user-submit" value="Login"
									tabindex="14" class="user-submit" /> <input type="hidden"
									name="redirect_to" value="index.jsp" /> <input type="hidden"
									name="user-cookie" value="1" />
							</div>
						</form>
					</div>
				</div>


				<div class="login-container right">
					<div class="login">

						<h2 class="form-heading">REGISTER FORM</h2>
						<p>Get Started with a new Account</p>

						<form method="post" action="RegisterServlet" class="wp-user-form">
							<div class="username">
								<label for="user_login">Username: </label> <input type="text"
									name="user_login" value="" size="20" id="user_login"
									tabindex="102" />
							</div>
							<div class="Email">
								<label for="user_email">Your Email: </label> <input type="text"
									name="user_email" value="" size="25" id="user_email"
									tabindex="103" />
							</div>
							<div class="password">
								<label for="user_password">Password: </label> <input
									type="password" name="user_password" value="" size="25"
									id="user_password" tabindex="104" />
							</div>
							<div class="login_fields">
								<input type="submit" name="user-submit" value="Sign up!"
									class="user-submit" tabindex="103" /> <input type="hidden"
									name="redirect_to" value="/user-login/?register=true" /> <input
									type="hidden" name="user-cookie" value="1" />
							</div>
						</form>

					</div>
				</div>
				<br clear="all" />

			</div>

		</div>



		<footer id="footer">
			<div id="footbar" class="footbar-c4" data-layout="c4">
				<div class="wrap cf">
					<div id="footbar-inner" class="">
						<div id="text-2" class="widget widget_text">
							<div class="widget-header">
								<h3 class="widget-title">Catastrophe.Ge</h3>
							</div>
							<div class="textwidget">Post, have some fun and win prizes!</div>
						</div>
					</div>
				</div>
			</div>

			<div id="colophon" role="contentinfo">
				<div class="wrap cf">


					<p id="copyright">
						Copyright 2014 © <a href="index.jsp">Catastrophe.ge</a> .
					</p>
					<p id="credits">
						All Content From <a target="_blank" href="index.jsp">Catastrophe.ge</a>
					</p>
				</div>
			</div>
		</footer>
	</div>
	<!-- end #page -->

</body>


</html>