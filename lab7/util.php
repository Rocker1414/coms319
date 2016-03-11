<?php

function readInPosts(){
	$_SESSION['posts'] = json_decode(file_get_contents('posts.txt'), true);
}

function writePosts(){
	
	file_put_contents('posts.txt.', json_encode($_SESSION['posts']));
}

function updatePost($i, $newPost){
	
	if($i == -1){
		array_push($_SESSION["posts"], $newPost);
	}
	else{
		
		//update
		$_SESSION["posts"][$i]["author"] = $newPost["author"];
		$_SESSION["posts"][$i]["date"] = $newPost["date"];
		$_SESSION["posts"][$i]["content"] = $newPost["content"];
	}
	
	writePosts();
}

function outputPosts($posts){
	for($i = 0; $i < sizeof($posts); $i++){
		
		$current = "<div class='post'>"	;
		//title
		$current = $current . "<h1>" . $posts[$i]["title"] . "</h1>";

		//author
		$current = $current . "<h3>Author: " . $posts[$i]["author"] . "</h3>";
		//date
		$current = $current . "<h3>Last Updated: " . $posts[$i]["date"] . "</h3>";
		
		//update link
		//$current = $current . 
		//content
		$current = $current . "<p class='content'>" . $posts[$i]["content"] . "</p>";
		
		
		$current = $current . "</div>";
		
		echo $current;
		echo "<br>";
	}
}

function findPostByTitle($posts, $new){
	//search all posts for new, return index or -1 if not there
	
	for($i = 0; $i < sizeof($posts); $i++){
		if($posts[$i]["title"] === $new["title"]){
			return $i;
			
		}
	}
	
	return -1;
	
}
?>