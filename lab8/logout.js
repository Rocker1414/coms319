$(function () {
	$("#logoutButton").on('click', function () {				
		$.ajax({
			type: 'POST',
			url: "http://localhost/logout.php",
		})
		
		.done(function(data) {
			var msg = JSON.parse(data);
			
			if(msg = "Success"){
				window.location.href='index.html';
			} 
		})
	})
});
