<?php 
header('Access-Control-Allow-Origin: *'); 
// read the users.txt file and then check the user/password. 
// return a json object with the success/failure info
	
	$n = $_REQUEST['name'];
	$p = $_REQUEST['pass'];
	
	$file = fopen("http://localhost/lab7/users.txt", "r") or die("Unable to open file!");
	while(!feof($file)){
		$line = trim(fgets($file));
		$combo = explode(" ", $line);
		
		$checkUser = strcmp($n, $combo[0]);
		$checkPass = strcmp($p,$combo[1]);	

		if($n === $combo[0] && $p === $combo[1]){	
			echo json_encode("Successful Login");
			break;
		}	
	}	
	
	fclose($file);
?>