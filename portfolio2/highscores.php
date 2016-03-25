<!DOCTYPE html>
<html>
<head>
	<!-- This doesn't seem to do anything :( -->
	<link rel="stylesheet" type="text/css" href="style.css">
	<?php 
		include 'util.php';
		session_start();
		readScores();
	?>
	<title>High Scores</title>
</head>

<body>
	<?php 
		echo outputScoreTable(); 
	?>
</body>

</html>