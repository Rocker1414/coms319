<?php

function readInPosts(){
	$_SESSION['posts'] = json_decode(file_get_contents('posts.txt'), true);
}

function writePosts(){
	
	file_put_contents('posts.txt.', json_encode($_SESSION['posts']));
}

function getAllPosts($posts){
	$all = "";
	
	for($i = sizeof($posts)-1; $i >= 0; $i--){
		
		$current = "<div class='post'>"	;
		//title
		$current = $current . "<h1>" . $posts[$i]["title"] . "</h1>";

		//update link
		$current = $current . "<a onclick='update(\"" . $posts[$i]["title"] . "\", \"" . $posts[$i]["content"] ."\")' href='#'>Update</a>";
		//author
		$current = $current . "<h3>Author: " . $posts[$i]["author"] . "</h3>";
		//date
		$current = $current . "<h3>Last Updated: " . $posts[$i]["date"] . "</h3>";
		
		//update link
		//$current = $current . 
		//content
		$current = $current . "<p class='content'>" . $posts[$i]["content"] . "</p>";
		
		
		$current = $current . "</div>";
		
		$all = $all . $current . "<br>";
	}
	
	return $all;
	
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
	echo "<div id='allPosts'>";
	for($i = sizeof($posts)-1; $i >= 0; $i--){
		
		$current = "<div class='post'>"	;
		//title
		$current = $current . "<h1>" . $posts[$i]["title"] . "</h1>";
		//update link
		$current = $current . "<a onclick='update(\"" . $posts[$i]["title"] . "\", \"" . $posts[$i]["content"] ."\")' href='#'>Update</a>";
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
	echo "</div>";

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