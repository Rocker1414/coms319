<?php
	include "init.php";

	$data = json_decode(file_get_contents('php://input'), true);

	$type = $data['type'];
	$title = $data['title'];
	$author = $data['author'];

	$book = -1;

	if($type == "title"){
		$book = searchByTitle($title);
	}
	else if($type == "author"){
		$book = searchByAuthor($author);
	}
	else if($type == "all"){
		$book = $_SESSION['library']->allBooks();
	}

	if($book == -1){
		echo "<p>Book not found.</p>";
	}
	else{
		echo $book->toHTML();
	}

?>