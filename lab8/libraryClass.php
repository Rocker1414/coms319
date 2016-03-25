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

		function allBooks(){

			for($i = 0; $i < sizeof($this->shelves); $i++){

				$shelf = $this->shelves[$i];

				for($j = 0; $j < sizeof($shelf->books); $j++){
					$book = $shelf->books[$j];
					
					//do html for book	
				}
			}
		}
	}
?>