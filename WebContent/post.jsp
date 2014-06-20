<%@page import="webPackage.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="managers.PostManager"%>
<%@page import="webPackage.User"%>
<%@page import="webPackage.Comment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en-US">
<%
	String id = request.getParameter("id");
	Post post = new Post(Integer.parseInt(id));
	PostManager postManager = (PostManager) request.getServletContext()
	.getAttribute("postManager");
%>
<head>

<!-- Title, Keywords and Description -->
<title><%=post.getTitle()%></title>

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
<script>
	subinsblogla = 0;
	setInterval(
			function() {
				if (document.readyState != 'complete') {
					document.documentElement.style.overflow = "hidden";
					var subinsblog = document.createElement("div");
					subinsblog.id = "subinsblogldiv";
					var polu = 99 * 99 * 99999999 * 999999999;
					subinsblog.style.zIndex = polu;
					subinsblog.style.background = "#f8f8f8 url(http://beetube.me/wp-content/uploads/2014/05/loading.gif) 50% 50% no-repeat";
					subinsblog.style.backgroundPositionX = "100%";
					subinsblog.style.backgroundPositionY = "100%";
					subinsblog.style.position = "fixed";
					subinsblog.style.right = "0px";
					subinsblog.style.left = "0px";
					subinsblog.style.top = "0px";
					subinsblog.style.bottom = "0px";
					if (subinsblogla == 0) {
						document.documentElement.appendChild(subinsblog);
						subinsblogla = 1;
					}
				} else if (document.getElementById('subinsblogldiv') != null) {
					document.getElementById('subinsblogldiv').style.display = "none";
					document.documentElement.style.overflow = "auto";
				}
			}, -1000);
</script>

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
<link rel='stylesheet' id='jtheme-fonts-css'  href='http://fonts.googleapis.com/css?family=Oxygen&amp;ver=3.9.1' type='text/css' media='all' />
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

<body
	class="single single-post postid-452 single-format-standard full-wrap">
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
									<%=user.getName()%></a></li>
							<li class="acti2"><a href="LogoutServlet">Logout</a></li>
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



		<div id="main">
			<div class="wrap cf">



				<div id="content" role="main">

					<div
						class="post"
						id="post-452">

						<div id="video">
							<div class="screen fluid-width-video-wrapper">

								<div class="thumb">
									<div class="clip-link">
										<span class="clip"> <img
											src="images/logo.png"
											alt="Simple Post" /><span class="vertical-align"></span>
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
								<h1 class="entry-title"><%=post.getTitle() %></h1>
								<div class="entry-meta">
									<span class="author"> <a
										href="user.jsp?id=<%=post.getUserID()%>"
										title="Posts by admin" rel="author"><%=post.getUserID()%></a></span>
									<span class="time"><%=post.getTimesTamp()%> </span> <span
										class="stats"><span class="comments"><i
											class="count"><%=post.getCommentCount()%></i> <span
											class="suffix"></span></span><span class="jtheme-post-likes likes"><i
											class="count" data-pid="452"><%=post.getLikeCount() - post.getDislikeCount()%></i>
											<span class="suffix"></span></span></span>


								</div>
								<%
									if (post.isActive()) {
								%>
								<div class="entry-actions">
									<span class="jtheme-like-post"><a class="like" href=""
										data-pid="452">Like?</a></span>
								</div>
								<%
									}
								%>

							</div>
							<!-- end .entry-header>.inner -->
						</div>
						<!-- end .entry-header -->
						<div id="details" class="section-box">
							<div class="section-content">
								<div id="info" class="more-less" data-less-height="100">


									<div class="entry-content rich-content">
										<div>
											<p><%=post.getStatus()%></p>
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
							<h3 class="section-title">Recent posts of user</h3>
						</div>

						<div class="section-content grid-mini">
							<div class="nag cf">
								<div id="post-455"
									class="post-455 post type-post status-publish format-standard has-post-thumbnail hentry category-animation category-hd category-random category-slow-motion tag-hosted tag-self tag-videos item clearfix cf item-video">

									<div class="thumb">
										<a class="clip-link" data-id="455"
											title="Self Hosted Video Post With Custom Player"
											href="../self-hosted-video-post-with-custom-player/index.html">
											<span class="clip"> <img
												src="../wp-content/uploads/2014/03/animals_widewallpaper_mouse-with-hat_62210-320x180.jpg"
												alt="Self Hosted Video Post With Custom Player" /><span
												class="vertical-align"></span>
										</span> <span class="overlay"></span>
										</a>
										<div class="hori-like">
											<p class="stats">
												<span class="views"><i class="count">3.72K</i> <span
													class="suffix"></span></span><span class="comments"><i
													class="count">0</i> <span class="suffix"></span></span><span
													class="jtheme-post-likes likes"><i class="count"
													data-pid="455">62</i> <span class="suffix"></span></span>
											</p>
										</div>
									</div>
									<div class="data">
										<h2 class="entry-title">
											<a
												href="../self-hosted-video-post-with-custom-player/index.html"
												rel="bookmark"
												title="Permalink to Self Hosted Video Post With Custom Player">Self
												Hosted Video Post With Custom Player</a>
										</h2>

										<p class="entry-meta">
											<span class="author vcard"> <a class="url fn n"
												href="../author/admin/index.html"
												title="View all posts by admin" rel="author">admin</a>
											</span>

											<time class="entry-date" datetime="25th Mar, 2014">25th
												Mar, 2014</time>
											 <span class="stats"><span class="views"><i
													class="count">3.72K</i> <span class="suffix">Views</span></span></span>
										</p>

										<p class="stats">
											<span class="views"><i class="count">3.72K</i> <span
												class="suffix"></span></span><span class="comments"><i
												class="count">0</i> <span class="suffix"></span></span><span
												class="jtheme-post-likes likes"><i class="count"
												data-pid="455">62</i> <span class="suffix"></span></span>
										</p>

									</div>
								</div>
								<!-- end #post-455 -->
							</div>
						</div>
					</div>
					<!-- end .related-posts -->

				</div>
				<!-- end #content -->


				<div id="sidebar" role="complementary" class="">

					<div class="border-sep widget widget_border masonry-brick"
						style="margin: 0px"></div>
					<div id="jtheme-widget-posts-2" class="widget widget-posts">
						<div class="widget-header">
							<h3 class="widget-title">Most Popular</h3>
						</div>
						<%
							ArrayList<Post> arr;
							arr = postManager.getPopularPosts();
							for (int i = 0; i < arr.size(); i++) {
								Post tmpP = arr.get(i);
						%>
						<ul class="post-list">
							<li class="item cf item-video">

								<div class="thumb">
									<a class="clip-link" data-id=<%=tmpP.getID()%>
										title=<%=tmpP.getTitle()%>
										href="../video-from-youtube-29/index.html"> <span
										class="clip"> <img
											src="wp-content/uploads/2014/03/elephants-dream-fantasy-animatio-160x90.jpg"
											alt=<%=tmpP.getTitle()%> /><span class="vertical-align"></span>
									</span> <span class="overlay"></span>
									</a>
								</div>
								<div class="data">
									<h4 class="entry-title">
										<a href="video-from-youtube-29/index.html"
											title=<%=tmpP.getTitle()%>><%=tmpP.getTitle()%></a>
									</h4>

									<p class="meta">
										<span class="author">Added by <a href="user.jsp?id=<%=tmpP.getUserID() %>"
											title="Post by user" rel="author"><%=tmpP.getUserID() %></a></span> <span class="time"><%=tmpP.getTimesTamp() %></span>
									</p>

									<p class="stats">
										<span class="views"><i class="count">113</i> <span
											class="suffix"></span></span><span class="comments"><i
											class="count">0</i> <span class="suffix"></span></span><span
											class="jtheme-post-likes likes"><i class="count"
											data-pid="43">1</i> <span class="suffix"></span></span>
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