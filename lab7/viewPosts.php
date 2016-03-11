<?php
include "util.php";
include "header.php";

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
					window.location.href = "login.html";
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
</script>

<div id="newPost">
Title: <input id="title" type="text" name="title"><br>
Content: <textarea id ="content" ></textarea><br>
<input id ="submitPost" type="submit" value="Submit" />
</div>


<?php
readInPosts();
outputPosts($_SESSION['posts']);

include "footer.php";
?>

