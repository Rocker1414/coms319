<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
		$username = "root";
		$password = "";
		$dbServer = "localhost"; 
		$dbName   = "lab09";
		$conn = new mysqli($dbServer, $username, $password, $dbName);
        
		$user = strip_tags(trim($_POST["user"]));
		$user = str_replace(array("\r","\n"),array(" "," "),$user);

	    $pass = strip_tags(trim($_POST["pass"]));
		$pass = str_replace(array("\r","\n"),array(" "," "),$pass);	
		$passEnc = md5($pass);
		
	    $pass2 = strip_tags(trim($_POST["passConfirm"]));
	    $pass2 = strip_tags(trim($_POST["passConfirm"]));
	
		$email = filter_var(trim($_POST["email"]), FILTER_SANITIZE_EMAIL);
		
		$phone = strip_tags(trim($_POST["phone"]));
		$phone = str_replace(array("\r","\n"),array(" "," "),$phone);
		
		$librarian = isset($_POST["librarian"]);
		if(empty($librarian)){
			$librarian = 0;
		} else {
			$librarian = 1;
		}
		
		$fname = strip_tags(trim($_POST["fname"]));
		$fname = str_replace(array("\r","\n"),array(" "," "),$fname);
		
		$lname = strip_tags(trim($_POST["lname"]));
		$lname = str_replace(array("\r","\n"),array(" "," "),$lname);
		
		$invalidFields = array(
		);  	
		
		if(ctype_alnum($user) == false || empty($user)){
		    http_response_code(403);
			$invalidFields["user"] = "Invalid username: $user (Must be non-empty and alphanumeric)";
		} else {			
			if ($conn->connect_error) {
				die("Connection failed: " . $conn->connect_error);
			} else {
				$sql = "SELECT * FROM group14_users WHERE username = '$user'";
				$result = $conn->query($sql);
				$row_cnt = $result->num_rows;
				
				if($row_cnt > 0){
					http_response_code(403);
					$invalidFields["user"] = "Username $user has already been registered.";
					$conn->close();
				}
			}
		}
		
		if(empty($pass) || strcmp($pass,$pass2) != 0){
			http_response_code(403);
			$invalidFields["pass"] = "Passwords must match and must not be empty";
		}
		if (filter_var($email, FILTER_VALIDATE_EMAIL) == false) {
		    http_response_code(403);
			$invalidFields["email"] = "Invalid Email: $email (Must be of form aaa@bbb.ccc)";
		} 
		$regex = '/^\d{3}-?\d{3}-?\d{4}$/';
		if (!preg_match($regex,$phone) && !empty($phone)){
			http_response_code(403);
			$invalidFields["phone"] = "Invalid Phone: $phone (Must be of form xxxxxxxxxx or xxx-xxx-xxxx)";
		}
		if(ctype_alpha($fname) == false || empty($fname)){
		    http_response_code(403);
			$invalidFields["fname"] = "Invalid First Name: $fname (Must be alphabetical)";
		}
		if(ctype_alpha($lname) == false || empty($lname)){
		    http_response_code(403);
			$invalidFields["lname"] = "Invalid Last Name: $lname (Must be alphabetical)";
		}
		if(count($invalidFields) > 0){
			$json = json_encode($invalidFields);
			echo($json);
		}
		else{
			$sqlFeedback = array(
			);  	
			if ($conn->connect_error) {
				die("Connection failed: " . $conn->connect_error);
			} else {
			$sql = "INSERT INTO group14_users(username, password, email, phone, librarian, firstname, lastname) VALUES ('$user', '$passEnc', '$email', '$phone', '$librarian', '$fname', '$lname')";
				if ($conn->query($sql) === TRUE) {
					$sqlFeedback["sqlRegisterMessage"] = "New record created successfully";
				} else {
				$sqlFeedback["sqlRegisterMessage"] = "Error: " . $sql . "<br>" . $conn->error;
				}
			}
			$json = json_encode($sqlFeedback);
			echo($json);
		}
	}
?>