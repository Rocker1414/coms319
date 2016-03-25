$(document).ready(function() {
	registerForm = $('#registerAccount');

	$(registerForm).submit(function(event) {
		message = $('#sqlMessage');
		event.preventDefault();
		
		var formData = $(registerForm).serialize();
		
		$.ajax({
			type: 'POST',
			url: "http://localhost/register.php",
			data: formData
		})
		
		.done(function(data) {
			var sqlMsg = JSON.parse(data);
			$(message).html(sqlMsg["sqlRegisterMessage"]);
			
			$('#userInput').val('');
			$('#passInput').val('');
			$('#passConfirmInput').val('');
			$('#emailInput').val('');
			$('#phoneInput').val('');
			$('#librarianInput').attr('checked',false);
			$('#fnameInput').val('');
			$('#lnameInput').val('');
			$('#userInput').val('');
			$('#passInput').val('');
			$('#emailInput').val('');
			$('#phoneInput').val('');
			$('#librarianInput').val('');
			$('#fnameInput').val('');
			$('#lnameInput').val('');
			$('#user').html('');
			$('#pass').html('');
			$('#email').html('');
			$('#phone').html('');
			$('#fname').html('');
			$('#lname').html('');
			$('#user').html('');
			$('#pass').html('');
			$('#email').html('');
			$('#phone').html('');
			$('#librarian').html('');
			$('#fname').html('');
			$('#lname').html('');
		})
		
		.fail(function(data) {
			$(message).html("");
			$('#passInput').val('');
			$('#passConfirmInput').val('');
			$('#user').html('');
			$('#pass').html('');
			$('#email').html('');
			$('#phone').html('');
			$('#fname').html('');
			$('#lname').html('');
			$('#user').html('');
			$('#pass').html('');
			$('#email').html('');
			$('#phone').html('');
			$('#librarian').html('');
			$('#fname').html('');
			$('#lname').html('');
			
			var invalidFields = JSON.parse(data.responseText);
			for (var key in invalidFields) {
				if (invalidFields.hasOwnProperty(key)) {
						$('#'+key).html(invalidFields[key]);
				}
			}
		});
	});
});