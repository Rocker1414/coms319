<?php 
	include 'init.php';
	readScores();
?>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css">
	
	<title>High Scores</title>
</head>
		<ul>
			<li><a href="index.html">Home</a></li>
		</ul>

<body>
	<center><h2>Total Games Played: <?php echo getTotalGames(); ?></h2></center>
	<div id = "scorecard" class = "center">
		<?php 
			echo outputScoreTable();
		?>
	</div>
	
	<h3>Developed by Ian Baer and Chris Rogers. Version 1.0</h3>
</body>

</html>