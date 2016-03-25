<!DOCTYPE HTML>
<html>
	<head>
		<title>Library</title>
		<script src ="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script type="text/javascript" src="logout.js"></script>
		<!--<link rel="stylesheet" href="style.css">-->
		<?php
			class Library{

				function __construct(){
					$this->shelves = array();
				}

				function addShelf($shelf){
					array_push($this->shelves, $shelf);
				}

				function getShelf($i){
					return $this->shelves[$i];
				}
			}
		?>
	</head>
	
	<body>
		<h1> Library Page </h1>
		<h2> 
			<?php 
				session_start();
				echo "Logged in as: " . $_SESSION["username"] . ".";
			?> 
		</h2>
		
		<button id = "logoutButton" type="onClick=logout()">Logout</button>
				
		<br>
		
		Search by title: <input type="text" id="title"/><button id="titleSearch">Search</button><br>
		Search by author: <input type="text" id="author"/><button id="authorSearch">Search</button><br>
		<br>
		<button>Return All</button>
		
		<div id="content">
		</div>

		<script>
			$(document).ready(function() {
				$('#titleSearch').submit(function(event) {
					message = $('#sqlMessage');
					event.preventDefault();
					
					var formData = $(registerForm).serialize();
				
					$.ajax({
						type: 'POST',
						url: "http://localhost/search.php",
						data: formData
					})
				
					.done(function(data) {
					})
				
					.fail(function(data) {
					});
			});

			$('#authorSearch').submit(function(event) {
				message = $('#sqlMessage');
				event.preventDefault();
				
				var formData = $(registerForm).serialize();
				
				$.ajax({
					type: 'POST',
					url: "http://localhost/search.php",
					data: formData
				})
				
				.done(function(data) {
					
				})
				
				.fail(function(data) {
					
				});
			});
		});
			</script>
		</body>
</html>