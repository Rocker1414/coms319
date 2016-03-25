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
		//todo create library object and add all books
	}
?>