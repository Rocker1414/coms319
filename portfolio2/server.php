<?php
	include 'init.php';
	readScores();

	$data = json_decode(file_get_contents('php://input'), true);
	$id = $data['id'];
	$name = $data['name'];
	$score = $data['score'];

	if($id == "check"){
		addGame();
		echo isHighScore($score);
	}
	elseif($id == "save"){
		echo trySave($name, $score);
	}

?>