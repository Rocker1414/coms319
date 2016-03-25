<?php
	session_start();
	include 'libraryClass.php';
	include 'studentClass.php';
	include 'shelfClass.php';
	include 'bookClass.php';
	include 'util.php';

	$_SESSION['library'] = parseLibrary();

?>	