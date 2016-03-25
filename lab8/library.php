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