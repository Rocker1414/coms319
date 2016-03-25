$(document).ready(function() {
	loginForm = $('#loginAccount');
	message = $('#sqlLoginMessage');
	
	$(loginForm).submit(function(event) {

		event.preventDefault();
		
		var formData = $(loginForm).serialize();
		
		$.ajax({
			type: 'POST',
			url: "http://localhost/login.php",
			data: formData
		})
		
		.done(function(data) {
			var sqlMsg = JSON.parse(data);
			
			if(sqlMsg = "Succcessful Login"){
				window.location.href='library.php';
			} else{
				$(message).html(sqlMsg["sqlLoginMessage"]);
			}
		})
		
		.fail(function(data) {
			var sqlMsg = JSON.parse(data.responseText);
		    $(message).html(sqlMsg["sqlLoginMessage"]);
		});
	});
});