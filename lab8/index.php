<!DOCTYPE HTML>
<html>
	<head>
		<title>Library Checkout</title>
		<script src ="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<link rel="stylesheet" href="style.css">
	</head>
	
	<body>
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

			/*// --------------------------------------
			// --- PART-2 --- INSERT DATA -----------
			// --------------------------------------
			$sql = "INSERT INTO userDetails (userID, userDetails) VALUES ('abc', 'john@example.com')";

			if ($conn->query($sql) === TRUE) {
				echo "New record created successfully<br>";
			} else {
				echo "Error: " . $sql . "<br>" . $conn->error;
			}*/


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
	</body>
</html>