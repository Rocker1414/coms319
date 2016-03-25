<?php
	class Book{
		function __contruct($id, $shelf, $title, $author){
			$this->id = $id;
			$this->shelf = $shelf;
			$this->title = $title;
			$this->author = $author;
		}

		function toHTML(){
			$ret = "<p>ID: " . $this->id . "</p>";
			$ret = $ret . "<p>Shelf: " . $this->shelf . "</p>";
			$ret = $ret . "<p>Title: " . $this->title . "</p>";
			$ret = $ret . "<p>Author: " . $this->author . "</p>";

			return $ret;
		}
	}
?>