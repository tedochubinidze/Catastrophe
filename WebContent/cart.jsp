<%@page import="webPackage.Product"%>
<%@page import="webPackage.Cart"%>
<%@page import="webPackage.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="managers.PostManager"%>
<%@page import="webPackage.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en-US">

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />

<head>
<!-- Meta Tags -->
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<!-- Title, Keywords and Description -->
<title>Catastrophe</title>
<meta name="description" content="Catastrophe.ge" />

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
<!--  es fontebis temaa unda iyos wesit -->
<link rel='stylesheet' id='jtheme-style-css'
	href='wp-content/themes/beetube/style370e.css?ver=1.4.3'
	type='text/css' media='all' />
<!--  es style veshebia ra unda iyvnen mgoni -->
<link rel='stylesheet' id='jtheme-mainstyle-css'
	href='wp-content/themes/beetube/css/stylesheet-red370e.css?ver=1.4.3'
	type='text/css' media='all' />
<!--  es style veshebia ra unda iyvnen mgoni -->


<meta name="generator" content="WordPress 3.9.1" />
<body class="home page">
	<div id="page">
		<header id="header">
			<div id="top-nav">
				<div class="wrap cf">

					<div id="header-search">
						<ul>
							<%
								User user = (User) request.getSession().getAttribute("currentUser");
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
					<%
						if (user != null) {
					%>
					<div class="tnav">
						<nav class="nav-collapse">
							<ul id="menu-header" class="menu">
								<%
									if (user.isAdmin()) {
								%>
								<li id="product" class="add product"><a
									href="addProduct.jsp">Add Product</a></li>
								<li id="cart" class="view cart"><a href="orders.jsp">View
										Orders</a></li>
								<li class="ratings"><form name="Rate"
										action="CreateRatingServlet" method="post">
										<script type="text/javascript">
											function update1() {
												Rate.submit();
											}
										</script>
										<a href="javascript:update1()">Award Users</a>
									</form></li>
								<%
									}
								%>
								<li id="post" class="add post"><a href="addFile.jsp">Add
										Post</a></li>
								<li id="cart" class="view cart"><a href="cart.jsp">View
										Cart</a></li>
								<li class="ratings"><form name="Rate"
										action="CreateRatingServlet" method="post">
										<script type="text/javascript">
											function update1() {
												Rate.submit();
											}
										</script>
										<a href="javascript:update1()">Award Users</a>
									</form></li>
							</ul>
						</nav>
					</div>
					<%
						}
					%>
					<!-- end #Top-nav -->
					<div class="clear"></div>
				</div>
			</div>

			<div class="header-secend">
				<div class="wrap cf">
					<div id="branding" class="image-branding">
						<h1 id="site-title">
							<a rel="home" href="index.jsp">Catastrophe.ge</a>
						</h1>

						<a id="site-logo" href="index.jsp"><img src="images/logo.png"
							alt="Catastrophe.ge" /></a>
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
	</div>
	<!-- end #main-nav -->

	<div id="main">
		<div class="wrap cf archive-wrap">
			<%
				if (user == null) {
			%>
			<h1>Login To Use this feature</h1>
			<%
				} else {
			%>

			<div id="entry-header">
				<div class="inner">
					<h1 class="page-title"></h1>
				</div>
			</div>
			<!-- end .entry-header -->

			<div id="content" role="main">

				<div class="post-241 page type-page status-publish hentry"
					id="post-241">

					<div class="page-content rich-content archive-content">

						<div class="section-box">
							<div class="section-header">
								<%
									Cart cart = user.getCart();
								%>
								<h2>
									<span class="name">Cart Price: <%=cart.getCartPrice()%></span>
								</h2>
								<h2 class="section-title">
									<span class="name">Products in cart:</span>
								</h2>
							</div>
							<div class="section-content list-small">
								<div class="nag cf">
									<%
										ArrayList<Product> prods = cart.getProducts();
											for (Product tmpP : prods) {
									%>

									<div id="post"
										class="post type-post item clearfix cf item-post">

										<div class="thumb">
											<a class="clip-link" title=<%=tmpP.getTitle()%>
												href=<%="product.jsp?id=" + tmpP.getID()%>> <span
												class="clip"> <img
													src=<%="images/" + tmpP.getImage()%>
													alt=<%=tmpP.getTitle()%>><span class="vertical-align"></span>
											</span> <span class="overlay"></span>
											</a>
											<div class="hori-like">
												<p class="stats">
													<span class="views"><i class="count"><%=tmpP.getPrice()%></i>
														<span class="suffix"></span></span> <span class="suffix"></span><span
														class="jtheme-post-likes likes"><i class="count"
														data-pid="452"><%=tmpP.getPrice()%></i> <span
														class="suffix"></span></span>
												</p>
											</div>
										</div>
										<div class="data">
											<h2 class="entry-title">
												<a href=<%="product.jsp?id=" + tmpP.getID()%>
													title=<%=tmpP.getTitle()%>><%=tmpP.getTitle()%></a>
											</h2>

											<p class="meta">
												<span class="time"><%=tmpP.getPrice()%></span>
											</p>

											<p class="meta">
											<form action="RemoveFromCartServlet" method="post">
												<input class="button btn-primary" id="submit" type="submit"
													value="Remove"> <input type="hidden"
													name="productID" value=<%=tmpP.getID()%>>
											</form>
											</p>
										</div>
									</div>
									<!-- end #post-455 -->
									<%
										}
									%>
									<form action="CleanCartServlet" method="post">
										<input class="button btn-primary" id="submit" type="submit"
											value="Clean Cart">
									</form>
									<form action="MakeOrderServlet" method="post">
										<h3>Fill in your address</h3>
										<input type="text" name="address"> <input
											class="button btn-primary" id="submit" type="submit"
											value="Make Order">
									</form>
								</div>
							</div>
							<!-- end .section-content -->
						</div>
						<!-- end .section-box -->
						<!-- end .section-box -->
						<!-- end .section-box -->
						<!-- end .section-box -->
					</div>

				</div>
				<!--end .hentry-->

			</div>
			<%
				}
			%>


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
		<!-- end #footbar -->

		<div id="colophon">
			<div class="wrap cf">


				<p id="copyright">
					Copyright 2014 © <a href="index.jsp">Catastrophe.ge</a> .
				</p>
				<p id="credits">
					All Content From <a target="_blank" href="index.jsp">Catastrophe.ge</a>
				</p>
			</div>
		</div>
		<!-- end #colophon -->
	</footer>
	<!-- end #footer -->

	<!-- end #page -->
</body>
</html>