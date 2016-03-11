<!DOCTYPE HTML>
<html>
	<head>
		<title>Post Application</title>
		<script src ="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<link rel="stylesheet" href="style.css" type="text/css">
		<?php session_start(); ?>
	</head>
	
	<body>
	<div id="titlebar">
	<h2>Hello: <span id="uname"><?php echo $_SESSION['name'];?></span></h2>
	