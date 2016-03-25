<?php
	class Shelf{
		function __construct($id){
			$this->id = $id;
			$this->books = array();
		}

		function addBook($book){
			array_push($this->books, $book);
		}

		function getBook($i){
			return $this->books[$i];
		}
	}
?>