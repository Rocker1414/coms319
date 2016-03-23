<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $user = strip_tags(trim($_POST["user"]));
		$user = str_replace(array("\r","\n"),array(" "," "),$user);

	    $pass = strip_tags(trim($_POST["pass"]));
		$pass = str_replace(array("\r","\n"),array(" "," "),$pass);	
		
	    $pass2 = strip_tags(trim($_POST["passConfirm"]));
	    $pass2 = strip_tags(trim($_POST["passConfirm"]));
		
		$email = filter_var(trim($_POST["email"]), FILTER_SANITIZE_EMAIL);
		
		$phone = strip_tags(trim($_POST["phone"]));
		$phone = str_replace(array("\r","\n"),array(" "," "),$phone);
		
		$librarian = isset($_POST["librarian"]);
		
		$fname = strip_tags(trim($_POST["fname"]));
		$fname = str_replace(array("\r","\n"),array(" "," "),$fname);
		
		$lname = strip_tags(trim($_POST["lname"]));
		$lname = str_replace(array("\r","\n"),array(" "," "),$lname);
		
		$invalidFields = array(
			'header' => "The following issues must be corrected for registration:"
		);  	
		
		if(ctype_alnum($user) == false || empty($user)){
		    http_response_code(403);
			array_push($invalidFields, "Invalid username: $user (Must be non-empty and alphanumeric)");
		}
		/*if(usernameInDatabase($user) == true){
		    http_response_code(403);
			array_push($invalidFields, "Username $user has already been registered");
		}*/
		if(empty($pass) || strcmp($pass,$pass2) != 0){
			http_response_code(403);
			array_push($invalidFields, "Passwords must match and must not be empty");
		}
		if (filter_var($email, FILTER_VALIDATE_EMAIL) == false) {
		    http_response_code(403);
			array_push($invalidFields, "Invalid Email: $email (Must be of form aaa@bbb.ccc)");
		} 
		$regex = '/^\d{3}-?\d{3}-?\d{4}$/';
		if (!preg_match($regex,$phone)){
			http_response_code(403);
			array_push($invalidFields, "Invalid Phone: $phone (Must be of form xxxxxxxxxx or xxx-xxx-xxxx)");
		}
		if(ctype_alpha($fname) == false || empty($fname)){
		    http_response_code(403);
			array_push($invalidFields, "Invalid First Name: $fname (Must be alphabetical)");
		}
		if(ctype_alpha($lname) == false || empty($lname)){
		    http_response_code(403);
			array_push($invalidFields, "Invalid Last Name: $lname (Must be alphabetical)");
		}
		if(count($invalidFields) > 1){
			$json = json_encode($invalidFields);
			echo($json);
		}
		else{
			//echo("Success!");
		}
	}
	
	function usernameInDatabase($u){
		$username = "root";
		$password = "";
		$dbServer = "localhost"; 
		$dbName   = "lab09";
        $conn = new mysqli($dbServer, $username, $password, $dbName);
		
		// Check connection
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		} 
				
		$sql = "SELECT * FROM group14_users WHERE username = $u";
		
		$result = $conn->query($sql);
		
		if ($result->num_rows > 0) {
			$conn->close();
			return true;
		}
		else{
			$conn->close();
			return false;
		}		
	}
	
	/* Useful PHP stuff for later
		<?php
			$username = "root";
			$password = "";
			$dbServer = "localhost"; 
			$dbName   = "lab09";

			// --------------------------------------
			// --- PART-1 --- CONNECT TO DATABASE ---
			// --------------------------------------
			$conn = new mysqli($dbServer, $username, $password, $dbName);

			// Check connection
				if ($conn->connect_error) {
					die("Connection failed: " . $conn->connect_error);
				} else {
					echo "Connected successfully<br>";
				}

			// --------------------------------------
			// --- PART-2 --- INSERT DATA -----------
			// --------------------------------------
			$sql = "INSERT INTO userDetails (userID, userDetails) VALUES ('abc', 'john@example.com')";

			if ($conn->query($sql) === TRUE) {
				echo "New record created successfully<br>";
			} else {
				echo "Error: " . $sql . "<br>" . $conn->error;
			}


			// --------------------------------------
			// --- PART-3 --- GET DATA --------------
			// --------------------------------------
			$sql = "SELECT * FROM group14_users";

			$result = $conn->query($sql);

			if ($result->num_rows > 0) {
				// output data of each row
				while($row = $result->fetch_assoc()) {
					echo "username: " . $row["username"]. "  firstname: " . $row["firstname"]. "<br>";
				}
			} else {
				echo "0 results";
			}
			// --------------------------------------
			// --- PART-4 --- CLOSE -----------------
			// --------------------------------------
			$conn->close();
		?>
		*/
?>