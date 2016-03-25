<?php
	class Student{
		function __construct($username, $email, $phone, $librarian, $first, $last){
			$this->username = $username;
			$this->email = $email;
			$this->phone = $phone;
			$this->librarian = $librarian;
			$this->first = $first;
			$this->last = $last;
		}
	}
?>