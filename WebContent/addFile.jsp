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

	<!-- <script type="text/javascript">

	var rqst = new XMLHttpRequest();
	
	function uploadFunction(){
		
		if(rqst.readyState==4){
			var data =rqst.responseText;
			alert(data);
		}
		
		rqst.open("post", "UploadServlet", true);
		rqst.send(null);
	}

</script>
 -->

	<div class="form">
		<div class="everything">
			<div class="img">
				<img src="images/logo.png" alt="logo" width="100" height="100">
			</div>
			<div class="next">



				<!-- 	<form action="UploadSevletVideo" method="post" enctype="multipart/form-data" >  -->

				<form name="form1" action="UploadServlet" method="post"
					enctype="multipart/form-data">
					<br> <br> <font color="black"> Choose File To
						Upload:</font><br> <input type="file" name="image" /> <br> <br>

					<!-- <input
						type="text" name="title"/> <strong><i><font
							color="black" id="id1" > Title</font></i></strong>  -->


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

					<!-- <div class="next1">
						<p class="text">
							<textarea name="textArea"></textarea>
						</p>
					</div> -->
					<div class="koko">
						<h4>Choose File To Upload. If do not wish , just Click "Next"
							button</h4>
						<br>
					</div>
					<div class="last">

						<!-- 					<button type="button" onclick="onclick1()">Click Here</button>
 -->


						<input class="curs" type="submit" value="Next">
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