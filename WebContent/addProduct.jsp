<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="textarea.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
  
	<div class="form">
		<div class="everything">
			<div class="img">
				<img src="images/logo.png" alt="logo" width="100" height="100">
			</div>
			<div class="next">
			
				
				
				<!-- 	<form action="UploadSevletVideo" method="post" enctype="multipart/form-data" >  -->
					
					  <form name="form1" action="AddProductImageServlet" method="post"  onsubmit="return validateForm()" enctype="multipart/form-data" >
						<br> <br> <font	color="black"> <strong>Choose Product Image To Upload:</strong></font><br><br>
						<input type="file" name="image" id="img1"  /> <br> <br>
						 
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
				<!-- <h4>Choose File To Upload. If do not wish , just Click "Next" button</h4>
 					<br>-->
 					</div> 
				<div class="last">
					
<!-- 					<button type="button" onclick="onclick1()">Click Here</button>
 -->				
 					
 
				 	<input class="curs" type="submit" value="Next">  <!-- <input
						class="curs" type="submit" value="Back" />  -->
					
				</div>
				 
				 </form>
			</div>
			
		</div>
		
	</div>
	
<script>

function validateForm() {

    if (document.getElementById("img1").value == "") {
        alert("Choose Image For Your Product !");
        return false;
    }
    return true;
}

</script>
	
</body>
</html>