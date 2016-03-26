<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css">
	<?php 
		include 'util.php';
		session_start();
		readScores();
	?>
	<title>High Scores</title>
</head>
		<ul>
			<li><a href="index.html">Home</a></li>
		</ul>

<body>
	<div id = "scorecard" class = "center">
		<?php 
			echo outputScoreTable(); 
		?>
	</div>
</body>

</html>