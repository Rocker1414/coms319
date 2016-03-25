<?php 
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
		session_start();
		session_unset(); 
		session_destroy();
		$json = json_encode("Success");
		echo($json);
	}
?>

