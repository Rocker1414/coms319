<?php

	include 'util.php';

	readInPosts();
	$posts = $_SESSION["posts"];
	$newPost = $_POST["newPost"];
	$newPost = json_decode($newPost, true);
	//$newPost = $_SESSION["newPost"];

	//get index
	$index = findPostByTitle($posts, $newPost);

	//update or create
	updatePost($index, $newPost);
	
	
	$allPosts = getAllPosts($_SESSION["posts"]);
	echo $allPosts;

?>