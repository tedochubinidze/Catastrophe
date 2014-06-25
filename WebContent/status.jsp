<%@page import="webPackage.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="textarea.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Post</title>
</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute("currentUser");
	%>

	<%
		if (user == null) {
	%>

	<h1>You Don't Have Permission To This Page</h1>
	<%
		} else {
	%>
	<script>
		function validateForm() {
			var x = document.forms["form1"]["title"].value;
			var y = document.forms["form1"]["textArea"].value;
			if (x == null || x == "" || y == null || y == "") {
				alert("Both Fields Must Be Filled !");
				return false;
			}
			return true;
		}
	</script>

	<div class="form">
		<div class="everything">
			<div class="img">
				<img src="images/logo.png" alt="logo" width="100" height="100">
			</div>
			<div class="next">



				<!-- 	<form action="UploadSevletVideo" method="post" enctype="multipart/form-data" >  -->

				<form name="form1" action="StatusUploadServlet" method="post"
					onsubmit="return validateForm()" enctype="multipart/form-data">
					<br>
					<!-- <font
						color="black"> Choose File To Upload:</font><br> 
						<input type="file" name="image" /> <br> <br> -->

					<div class="title">
						<h4>Choose Title For Your Post</h4>
					</div>
					<div class="titleField">
						<input type="text" name="title" /> <strong><i><font
								color="black"> Title</font></i></strong>
					</div>

					<!--
             <div class="combobox">
			<select name="types">
					<option>Choose Fi
					le Type</option>
					<option value="image">Image</option>
					<option value="video">Video</option>
					<option value="status">Status</option>
			    </select>
	</div>	
				
	-->
					<div class="area">
						<h4>Choose Status Text For Your Post</h4>
					</div>
					<div class="next1">
						<p class="text">
							<textarea name="textArea"></textarea>
						</p>
					</div>
					<div class="koko">
						<br>
					</div>
					<div class="last">

						<!-- 					<button type="button" onclick="onclick1()">Click Here</button>
 -->

						<input class="curs" type="submit" value="Upload">
						<!-- <input
						class="curs" type="submit" value="Back" />  -->
					</div>

				</form>
			</div>

		</div>

	</div>
	<%
		}
	%>
</body>
</html>