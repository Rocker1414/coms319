<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
		$username = "root";
		$password = "";
		$dbServer = "localhost"; 
		$dbName   = "lab08";
		$conn = new mysqli($dbServer, $username, $password, $dbName);
        
		$userLogin = strip_tags(trim($_POST["userLogin"]));
		$userLogin = str_replace(array("\r","\n"),array(" "," "),$userLogin);

	    $passLogin = strip_tags(trim($_POST["passLogin"]));
		$passLogin = str_replace(array("\r","\n"),array(" "," "),$passLogin);	
		$passEnc = md5($passLogin);
		
		$sqlFeedback = array(
		);
		
		if(empty($userLogin) || empty($passLogin)){
			$sqlFeedback["sqlLoginMessage"] = "Invalid username and/or password.";
			http_response_code(403);

		} else{
			if ($conn->connect_error) {
				die("Connection failed: " . $conn->connect_error);
			} else {
				$sql = "SELECT password FROM group14_users WHERE username = '$userLogin'";
				$result = $conn->query($sql) or die("MySQL Error");
				$value = $result->fetch_assoc();
				$row_cnt = $result->num_rows;
				if($row_cnt == 0 || ($passEnc !== $value["password"])){
					http_response_code(403);
					$sqlFeedback["sqlLoginMessage"] = "Invalid password";
				} else{
					session_start();
					$_SESSION["username"] = $userLogin;
					$sqlFeedback["sqlLoginMessage"] = "Successful login";
				  }
			   }
			}
		$json = json_encode($sqlFeedback);
		echo($json);
		$conn->close();
	}
?>