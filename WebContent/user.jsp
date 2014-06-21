<!DOCTYPE html>
<%@page import="webPackage.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="managers.PostManager"%>
<%@page import="webPackage.User"%>
<html lang="en-US">

<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<!-- Meta Tags -->
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<!-- Title, Keywords and Description -->
<%
	User user = (User) request.getSession().getAttribute("currentUser");
	String id = (String) request.getParameter("id");
	User visitedUser = new User(id);
%>
<title><%=visitedUser.getID() + "'s Page"%></title>


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
<link rel='stylesheet' id='jtheme-style-css'
	href='wp-content/themes/beetube/style370e.css?ver=1.4.3'
	type='text/css' media='all' />
<link rel='stylesheet' id='jtheme-mainstyle-css'
	href='wp-content/themes/beetube/css/stylesheet-red370e.css?ver=1.4.3'
	type='text/css' media='all' />
<body class="archive author author-admin author-1 full-wrap">
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
	<div id="main" class="authermain">
		<div class="wrap cf">
			<div id="content" role="main">

				<div class="loop-content-m">
					<div class="loop-content switchable-view grid-mini"
						data-view="grid-mini" data-ajaxload=1>

						<div class="author-info">
							<div class="author-thumb"></div>
							<div class="author-bio">
								<h3>
									About <a><%=visitedUser.getID()%></a>
								</h3>
								<p>
									This is profile page of
									<%=visitedUser.getID()%>.
								</p>
								<div class="author-links">
									<p>
										Username:
										<%=visitedUser.getID()%></p>
									<p>
										First Name:
										<%=visitedUser.getName()%></p>
									<p>
										Last Name:
										<%=visitedUser.getLastName()%></p>
									<p>
										Email:
										<%=visitedUser.getEmail()%></p>

									<p>
										User Type:
										<%=user.isAdmin()%></p>
									<%
										if (visitedUser.equals(user)) {
									%>
									<p>
										Points:
										<%=visitedUser.getPoints()%></p>
									<%
										}
									%>
									<div class="clear"></div>

								</div>
							</div>
							<div class="clear"></div>

						</div>
						<div class="section-header">
							<h2 class="section-title">
								<span class="name">Most Viewed</span>
							</h2>
						</div>
						<div class="nag cf">
							<%
								PostManager manager = (PostManager) request.getServletContext()
										.getAttribute("postManager");
								ArrayList<Post> ls = manager.getPopularPostsByUser(id);
								for (Post p : ls) {
							%>
							<div id="post" <%if (p.getType().equals("video")) {%>
								class="post item item-video" <%} else {%>
								class="post item item-post" <%}%>>

								<div class="thumb">
									<a class="clip-link" data-id="452" title=<%=p.getTitle()%>
										href=<%="post.jsp?id=" + p.getID()%>> <span class="clip">
											<img src=<%="images/" + p.getAttachment()%>
											alt=<%=p.getTitle()%>><span class="vertical-align"></span>
									</span> <span class="overlay"></span>
									</a>
									<div class="hori-like">
										<p class="stats">
											<span class="views"><i class="count"><%=p.getLikeCount() - p.getDislikeCount()%></i>
												<span class="suffix"></span></span><span class="comments"><i
												class="count"><%=p.getCommentCount()%></i> <span
												class="suffix"></span></span><span class="jtheme-post-likes likes"><i
												class="count" data-pid="452"><%=p.getLikeCount() - p.getDislikeCount()%></i>
												<span class="suffix"></span></span>
										</p>
									</div>
								</div>
								<div class="data">
									<h2 class="entry-title">
										<a href=<%="post.jsp?id=" + p.getID()%> rel="bookmark"
											title=<%=p.getTitle()%>><%=p.getTitle()%></a>
									</h2>

									<p class="entry-meta">
										<span class="author vcard"> <a class="url fn n"
											href=<%="user.jsp?id=" + p.getUserID()%>
											title=<%="View all posts by" + p.getUserID()%> rel="author"><%=p.getUserID()%></a>
										</span>

										<time class="entry-date" datetime=<%=p.getTimesTamp()%>><%=p.getTimesTamp()%></time>
										<span class="stats"><span class="views"><i
												class="count"><%=p.getLikeCount() - p.getDislikeCount()%></i>
										</span></span>
									</p>

									<p class="stats">
										<span class="views"><i class="count"> <%=p.getLikeCount() - p.getDislikeCount()%></i>
											<span class="suffix"></span></span><span class="comments"><i
											class="count"><%=p.getCommentCount()%></i> <span
											class="suffix"></span></span><span class="jtheme-post-likes likes"><i
											class="count" data-pid="452"><%=p.getLikeCount() - p.getDislikeCount()%></i>
											<span class="suffix"></span></span>
									</p>

									<p class="entry-summary"><%=p.getStatus()%></p>
								</div>
							</div>
							<%
								}
							%>
						</div>
						<div class="section-header">
							<h2 class="section-title">
								<span class="name">Recent Posts</span>
							</h2>
						</div>
						<div class="nag cf">
							<%
								ArrayList<Post> ls2 = manager.getRecentPostsByUser(id);
								for (Post p : ls2) {
							%>
							<div id="post" <%if (p.getType().equals("video")) {%>
								class="post item item-video" <%} else {%>
								class="post item item-post" <%}%>>

								<div class="thumb">
									<a class="clip-link" data-id="452" title=<%=p.getTitle()%>
										href=<%="post.jsp?id=" + p.getID()%>> <span class="clip">
											<img src=<%="images/" + p.getAttachment()%>
											alt=<%=p.getTitle()%>><span class="vertical-align"></span>
									</span> <span class="overlay"></span>
									</a>
									<div class="hori-like">
										<p class="stats">
											<span class="views"><i class="count"><%=p.getLikeCount() - p.getDislikeCount()%></i>
												<span class="suffix"></span></span><span class="comments"><i
												class="count"><%=p.getCommentCount()%></i> <span
												class="suffix"></span></span><span class="jtheme-post-likes likes"><i
												class="count" data-pid="452"><%=p.getLikeCount() - p.getDislikeCount()%></i>
												<span class="suffix"></span></span>
										</p>
									</div>
								</div>
								<div class="data">
									<h2 class="entry-title">
										<a href=<%="post.jsp?id=" + p.getID()%> rel="bookmark"
											title=<%=p.getTitle()%>><%=p.getTitle()%></a>
									</h2>

									<p class="entry-meta">
										<span class="author vcard"> <a class="url fn n"
											href=<%="user.jsp?id=" + p.getUserID()%>
											title=<%="View all posts by" + p.getUserID()%> rel="author"><%=p.getUserID()%></a>
										</span>

										<time class="entry-date" datetime=<%=p.getTimesTamp()%>><%=p.getTimesTamp()%></time>
										<span class="stats"><span class="views"><i
												class="count"><%=p.getLikeCount() - p.getDislikeCount()%></i>
										</span></span>
									</p>

									<p class="stats">
										<span class="views"><i class="count"> <%=p.getLikeCount() - p.getDislikeCount()%></i>
											<span class="suffix"></span></span><span class="comments"><i
											class="count"><%=p.getCommentCount()%></i> <span
											class="suffix"></span></span><span class="jtheme-post-likes likes"><i
											class="count" data-pid="452"><%=p.getLikeCount() - p.getDislikeCount()%></i>
											<span class="suffix"></span></span>
									</p>

									<p class="entry-summary"><%=p.getStatus()%></p>
								</div>
							</div>
							<%
								}
							%>
						</div>
					</div>
				</div>
				<!-- end .loop-content -->
				<br />
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
	</div>



</body>
</html>