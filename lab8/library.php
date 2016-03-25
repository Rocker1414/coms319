<<<<<<< HEAD
<!DOCTYPE HTML>
<html>
	<head>
		<title>Library</title>
		<script src ="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script type="text/javascript" src="logout.js"></script>
		<!--<link rel="stylesheet" href="style.css">-->
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

	</body>
</html>
=======
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
>>>>>>> c772062b4ff56259af5276fb071bddb5a782549b
