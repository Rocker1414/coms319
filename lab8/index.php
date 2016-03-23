<!DOCTYPE HTML>
<html>
	<head>
		<title>Library</title>
		<script src ="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<!--<link rel="stylesheet" href="style.css">-->
	</head>
	
	<body>
	
					
		<form id="registerAccount">
			Username: <input id = "user" type="text" name="user"><br>
			Password: <input id = "pass" type="password" name="pass"><br>
			Confirm Password: <input id = "pass" type="password" name="passConfirm"><br>
			Email: <input id = "email" type="text" name="email"><br>
			Phone: <input id = "phone" type="text" name="phone"><br>
			Librarian?: <input id = "librarian" type="checkbox" name="librarian"><br>
			First Name: <input id = "fname" type="text" name="fname"><br>
			Last Name: <input id = "lname" type="text" name="lname"><br>
			<input id = "registerButton" type="submit" value="Register Account">
		</form>

		<h1>Response from server:</h1>
		<div id="message"></div>
		
		<script>
			$(function() {
				form = $('#registerAccount');
				message = $('#message');
				
				$(form).submit(function(event) {
					event.preventDefault();
					
					var formData = $(form).serialize();
					
					$.ajax({
						type: 'POST',
						url: "http://localhost/register.php",
						data: formData
					})
					
					.done(function(response) {
						$(message).text(response);
					})
					
					.fail(function(data) {
						var invalidFields = JSON.parse(data.responseText);
						console.log(invalidFields);
						message.html(invalidFields.header + "<br>");
						for (var key in invalidFields) {
							if (invalidFields.hasOwnProperty(key)) {
								if(key != "header")
									message.append(invalidFields[key] + "<br>");
							}
						}
					});
				});
			});
		</script>
	</body>
</html>