<?php 
function readScores(){
	$_SESSION['scores'] = json_decode(file_get_contents('high_scores.txt'), true);
}

function trySave($name, $score){
	
}

function writeScores(){

	file_put_contents('high_scores.txt.', json_encode($_SESSION['scores']));

}

function outputScoreTable(){
	$table = "<table>";

	$table = $table . "<th>Name</th>";
	$table = $table . "<th>Score</th>";

	for($i = 0; $i < sizeof($_SESSION['scores']); $i++){

		$table = $table . "<tr>";

		$table = $table . "<td>" . $_SESSION['scores'][$i]["name"] . "</td>";
		$table = $table . "<td>" . $_SESSION['scores'][$i]["score"] . "</td>";

		$table = $table . "</tr>";
	}

	$table = $table . "</table>";

	return $table;
}

?>

