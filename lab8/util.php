<?php

	function searchByTitle($title){
		$lib = $_SESSION['library'];
		$shelves = $lib->shelves;
		for($i = 0; $i < sizeof($shelves); $i++){
			$book = $shelves->getBook($i);

			if($book->title == $title){
				return $book;
			}

		}

		return null;
	}

	function searchByAuthor($author){
		$lib = $_SESSION['library'];
		$shelves = $lib->shelves;
		for($i = 0; $i < sizeof($shelves); $i++){
			$book = $shelves->getBook($i);

			if($book->author == $author){
				return $book;
			}

		}

		return null;
	}

	function parseLibrary(){
		$username = "root";
		$password = "";
		$dbServer = "localhost"; 
		$dbName   = "lab08";
		$conn = new mysqli($dbServer, $username, $password, $dbName);
        
		if ($conn->connect_error) {
				die("Connection failed: " . $conn->connect_error);
			} else {
				$sql = "SELECT shelfID FROM group14_shelves";
				$result = $conn->query($sql) or die("MySQL Error");

				if ($result->num_rows > 0) {
							
					$shelves = array(
					);
					
					while($row = $result->fetch_assoc()) {
						array_push($shelves, $row["shelfID"]);
				   }
				   $shelfObj = new Library($shelves);
				}
			}
	}
?>