<?php
include "util.php";
include "header.php";

//if not logged in, redirect
if(empty($_SESSION['name'])) {
   header("Location: http://localhost/login.html");
	exit();
}

error_reporting(E_ALL);
?>

<button id="logout">Logout</button>
<button id="makePost">Make Post</button>
</div>
<script> 
			
			$(document).ready(function () {		
				 $("#newPost").hide();
					
				$("#makePost").click(function () {
					$("#newPost").toggle();
				});

				 $("#logout").click(function () {
					 $.ajax({
						url: '/logout.php',
						type: 'POST',
						data: { },
						dataType: 'html',
						success: function(){
							window.location.href = "login.html";
						}
					});
					
				});
				
				$("#submitPost").click(function () {
					var title = $("#title").val();
					var name = $("#uname").text();
					var date = (new Date()).toDateString();
					var content = $("#content").val();
					var np = {"title": title, "author": name, "date": date, "content": content};
					
					$.ajax({
						url: '/updatePosts.php',
						type: 'POST',
						data: { newPost: JSON.stringify(np)},
						dataType: 'html',
						success: function(data){
							$("#allPosts").html(data);
						}
					});
					
					$("#title").val('');
					$("#content").val('');
					 $("#newPost").hide();
					
				});
				
			});
			
			function update(title, content){
				$("#newPost").show();
				$("#title").val(title);	
				$("#content").val(content);				
				
			};
</script>

<div id="newPost">
<span class="label"> Title: </span><input id="title" type="text" name="title"><br>
<span class="label">Content: </span><textarea id ="content" ></textarea><br>
<input id ="submitPost" type="submit" value="Submit" />
</div>


<?php
readInPosts();
outputPosts($_SESSION['posts']);

include "footer.php";
?>

