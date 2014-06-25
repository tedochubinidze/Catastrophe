<%@page import="webPackage.Order"%>
<%@page import="managers.ProductManager"%>
<%@page import="webPackage.Product"%>
<%@page import="webPackage.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="managers.ProfileManager"%>
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
	<%
		User user = (User) request.getSession().getAttribute("currentUser");
	%>

	<%
		if (user == null || !user.isAdmin()) {
	%>

	<h1>You Don't Have Permission To This Page</h1>
	<%
		} else {
			Order order = new Order(Integer.parseInt(request
			.getParameter("id")));
			ArrayList<Product> ls = order.getProducts();
	%>
	<div id="page">
		<header id="header">
			<div id="top-nav">
				<div class="wrap cf">

					<div id="header-search">
						<ul>
							<%
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
								<li id="product" class="add product"><a href="addP.jsp">Add
										Product</a></li>
								<li id="cart" class="view cart"><a href="orders.jsp">View
										Orders</a></li>
								<%
									}
								%>
								<li id="post" class="add post"><a href="addFile.jsp">Add
										Post</a></li>
								<li id="cart" class="view cart"><a href="cart.jsp">View
										Cart</a></li>
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

	<div id="main" class="home-temp">
		<div class="wrap cf home-content">
			<div id="content">
				<div class="section-box">
					<div class="section-header">
						<h2 class="section-title">
							<span class="name">Products ordered by <%=order.getUserID()%></span>
						</h2>
					</div>
					<div class="section-content grid-medium">
						<div class="nag cf">
							<%
								for (Product p : ls) {
							%>
							<div id="post" class="post item item-post">
								<div class="thumb">
									<a class="clip-link" data-id="452" title=<%=p.getTitle()%>
										href=<%="product.jsp?id=" + p.getID()%>> <span
										class="clip"> <img src=<%="images/" + p.getImage()%>
											alt=<%=p.getTitle()%> height="1px" width="1px"><span
											class="vertical-align"></span>
									</span> <span class="overlay"></span>
									</a>
									<div class="hori-like">
										<p class="stats">
											<span class="views"><i class="count"><%=p.getPrice()%></i>
												<span class="suffix"></span></span><span class="comments"><i
												class="count"></i> <span class="suffix"></span></span><span
												class="jtheme-post-likes likes"><i class="count"
												data-pid="452"><%=p.getPrice()%></i> <span class="suffix"></span></span>
										</p>
									</div>
								</div>
								<div class="data">
									<h2 class="entry-title">
										<a href=<%="product.jsp?id=" + p.getID()%> rel="bookmark"
											title=<%=p.getTitle()%>><%=p.getTitle()%></a>
									</h2>

									<p class="entry-meta">
										<span class="stats"><span class="views"><i
												class="count"><%=p.getPrice()%></i> </span></span>
									</p>
								</div>
							</div>
							<%
								}
							%>
						</div>
					</div>
				</div>
			</div>
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
		<!-- end #colophon -->
	</footer>
	<!-- end #footer -->

	<!-- end #page -->
	<%
		}
	%>
</body>
</html>