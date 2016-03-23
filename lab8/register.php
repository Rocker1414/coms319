<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $user = strip_tags(trim($_POST["user"]));
		$user = str_replace(array("\r","\n"),array(" "," "),$user);
		
		if(ctype_alnum($user) == false){
			echo nl2br("$user is not a valid username. ");
		} else {
			echo nl2br("$user is a valid username. ");
		}
		
        $email = filter_var(trim($_POST["email"]), FILTER_SANITIZE_EMAIL);
		if (filter_var($email, FILTER_VALIDATE_EMAIL) == false) {
			echo("$email is not a valid email address. ");
		} else {
			echo("$email is a valid email address. ");
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