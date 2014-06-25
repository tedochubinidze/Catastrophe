<%@page import="managers.ProductManager"%>
<%@page import="webPackage.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="webPackage.User"%>
<%@page import="webPackage.Comment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en-US">
<%
	String id = request.getParameter("id");
	Product prod = new Product(Integer.parseInt(id));
%>
<head>

<!-- Title, Keywords and Description -->
<title><%=prod.getTitle()%></title>

<link rel="profile" href="http://gmpg.org/xfn/11" />
<link rel="shortcut icon"
	href="wp-content/themes/beetube/images/catastrophe.png" />
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

<style>
#back-top {
	position: fixed;
	bottom: 30px;
	right: 0px;
}

#back-top  a {
	width: 108px;
	display: block;
	text-align: center;
	font: 11px/100% Arial, Helvetica, sans-serif;
	text-transform: uppercase;
	text-decoration: none;
	color: #bbb;
	-webkit-transition: 1s;
	-moz-transition: 1s;
	transition: 1s;
}

#back-top  a:hover {
	color: #000;
}

#back-top  span {
	width: 108px;
	height: 108px;
	display: block;
	margin-bottom: 7px;
	background: #ddd
		url(http_/4.bp.blogspot.com/-0mlo-caVkrQ/Ub835FbxwKI/AAAAAAAACv4/y9bfGt2b1fs/s1600/0017.html)
		no-repeat center center;
	-webkit-border-radius: 15px;
	-moz-border-radius: 15px;
	border-radius: 15px;
	-webkit-transition: 1s;
	-moz-transition: 1s;
	transition: 1s;
}

#back-top  a:hover span {
	background-color: #777;
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
<script type='text/javascript'
	src='http://beetube.me/wp-includes/js/jquery/jquery.js?ver=1.11.0'></script>
<link rel="wlwmanifest" type="application/wlwmanifest+xml"
	href="http://beetube.me/wp-includes/wlwmanifest.xml" />

</head>

<body class="single single-post">
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
								<%
									}
								%>
								<li id="post" class="add post"><a href="addFile.jsp">Add
										Post</a></li>
								<li id="cart" class="view cart"><a href="cart.jsp">View
										Cart</a></li>
								<li class="ratings"><form name="Rate" action="CreateRatingServlet"
									method="post">
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
		<!-- end #main-nav -->



		<div id="main">
			<div class="wrap cf">



				<div id="content">

					<div class="post type-post">

						<div id="post">
							<div class="screen fluid-width-video-wrapper">

								<div class="thumb">
									<div class="clip-link">
										<span class="clip"> <img
											src=<%="images/" + prod.getImage()%> alt="Simple Post" /><span
											class="vertical-align"></span>
										</span>


									</div>

								</div>
							</div>
							<!-- end .screen -->
						</div>
						<!-- end #video-->
						<br clear="all" />
						<div class="entry-header cf">
							<div class="inner cf">
								<h1 class="entry-title"><%=prod.getTitle()%></h1>
								<div class="entry-meta">
									<span class="time"><%=prod.getPrice()%> </span>
									<%
										if (user != null) {
									%>
									<form action="AddToCart" method="post">
										<input class="button btn-primary" id="submit" type="submit"
											value="Add To Cart"> <input type="hidden"
											name="productID" value=<%=prod.getID()%>>
									</form>
									<%
										} else {
									%>
									<h2>
										You must Be <a href="login.jsp">Logged in</a> to buy this
										product
									</h2>
									<%
										}
									%>

								</div>
							</div>
							<!-- end .entry-header>.inner -->
						</div>
						<!-- end .entry-header -->
						<div id="details" class="section-box">
							<div class="section-content">
								<div id="info" class="more-less" data-less-height="100">


									<div class="entry-content rich-content">
										<div>
											<p><%=prod.getDiscription()%></p>
											<p>&nbsp;</p>
										</div>
									</div>
									<!-- end .entry-content -->

								</div>
								<!-- end #info -->
							</div>
							<!-- end .section-content -->

						</div>
						<!--end #deatils-->

					</div>
					<!-- end #post-452 -->


					<div class="section-box related-posts">
						<div class="section-header">
							<h3 class="section-title">Products You May Like</h3>
						</div>

						<div class="section-content grid-mini">
							<%
								ProductManager manager = (ProductManager) request
										.getServletContext().getAttribute("productManager");
								ArrayList<Product> ls = manager.getProducts();
								for (Product p : ls) {
							%>
							<div id="post" class="post item item-post">
								<div class="thumb">
									<a class="clip-link" title=<%=p.getTitle()%>
										href=<%="product.jsp?id=" + p.getID()%>> <span
										class="clip"> <img src=<%="images/" + p.getImage()%>
											alt=<%=p.getTitle()%>><span class="vertical-align"></span>
									</span> <span class="overlay"></span>
									</a>
									<div class="hori-like">
										<p class="stats">
											<span class="views"><i class="count"><%=p.getPrice()%></i>
												<span class="suffix"></span></span> <span class="suffix"></span><span
												class="jtheme-post-likes likes"><i class="count"
												data-pid="452"><%=p.getPrice()%></i> <span class="suffix"></span></span>
										</p>
									</div>
								</div>
								<div class="data">
									<h2 class="entry-title">
										<a href=<%="product.jsp?id=" + p.getID()%>
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
						<!-- end #post-455 -->

					</div>
				</div>
				<!-- end .related-posts -->

				<!-- end #content -->


				<div id="sidebar">

					<div class="border-sep widget widget_border masonry-brick"
						style="margin: 0px"></div>
					<div id="jtheme-widget-posts-2" class="widget widget-posts">
						<div class="widget-header">
							<h3 class="widget-title">Other Products</h3>
						</div>
						<%
							ArrayList<Product> arr;
							arr = manager.getProducts();
							for (int i = 0; i < arr.size(); i++) {
								Product tmpP = arr.get(i);
						%>
						<ul class="post-list">
							<li class="item cf item-post">

								<div class="thumb">
									<a class="clip-link" title=<%=tmpP.getTitle()%>
										href=<%="product.jsp?id=" + tmpP.getID()%>> <span
										class="clip"> <img src=<%="images/" + tmpP.getImage()%>
											alt=<%=tmpP.getTitle()%>><span class="vertical-align"></span>
									</span> <span class="overlay"></span>
									</a>
									<div class="hori-like">
										<p class="stats">
											<span class="views"><i class="count"><%=tmpP.getPrice()%></i>
												<span class="suffix"></span></span> <span class="suffix"></span><span
												class="jtheme-post-likes likes"><i class="count"
												data-pid="452"><%=tmpP.getPrice()%></i> <span class="suffix"></span></span>
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
								</div>
							</li>
							<%
								if (i > 6)
										break;
								}
							%>
						</ul>

					</div>
				</div>
			</div>
			<!-- end #main -->




			<footer id="footer">
				<div id="footbar" class="footbar-c4" data-layout="c4">
					<div class="wrap cf">
						<div id="footbar-inner" class="">
							<div id="text-2" class="widget widget_text">
								<div class="widget-header">
									<h3 class="widget-title">Catastrophe.Ge</h3>
								</div>
								<div class="textwidget">Post, have some fun and win
									prizes!</div>
							</div>
						</div>
					</div>
				</div>

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
			</footer>
			<!-- end #footer -->

		</div>
	</div>

	<!-- end #page -->

	<script type='text/javascript'
		src='../wp-content/plugins/contact-form-7/includes/js/jquery.form.min9d5c.js?ver=3.50.0-2014.02.05'></script>
	<script type='text/javascript'
		src='../wp-content/themes/beetube/js/jquery.fitvids5152.js?ver=1.0'></script>
	<script type='text/javascript'
		src='../wp-content/themes/beetube/js/theme5f04.js?ver=1.4.6'></script>
</body>

<!-- Mirrored from beetube.me/simple-image-based-post/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 04 Jun 2014 17:46:12 GMT -->
</html>