// calculator.js
// Ian Baer
// Chris Rogers
// ComS 319 - Group 14	

$(document).ready(function(){
	
		// Styles
		$("#style1").css("color", "red"); <!-- Html Element: p id: style1: effect: Turn text red-->
		$("#style2").css("background-color", "cyan"); <!-- Html Element: button id: style2: effect: Change background-color-->
		$("#style3").css("font-family", "Courier New"); <!-- Html Element: p id: style2: effect: Change font->\
		$("#style4").css("border", "1px solid black"); <!-- Html Element: p id: style2: effect: Add border -->
		$("#style5").css("font-variant", "small-caps"); <!-- Html Element: p id: style2: effect: Make small caps-->
	
		// Effects
		$("#effect1").on("click", function(){ <!-- Html Element: button id: effect1: effect: Fades out on click-->
			$(this).fadeOut(700);
		});
		$("#effect2").on("click", function(){ <!-- Html Element: p id: effect2: effect: Slides right on click-->
			$(this).animate({
				opacity: 2.0,
				paddingLeft: "+=80"
			});
		});
		$("#showhide").hide();
		$("#effect3").on("click", function(){ <!-- Html Element: button id: effect3: effect: Makes text appear-->
			$("#showhide").show();
		});
		$("#effect4").on("click", function(){ <!-- Html Element: button id: effect4: effect: Makes text disappear-->
			$("#showhide").hide();
		});
		$("#effect5").on("click", function(){ <!-- Html Element: button id: effect5: effect: Toggles text-->
			$("#showhide2").toggle();
		});

		// Events
		$("#event1").on("click", function(){ <!-- Html Element: button id: event1: event: Changes text color on click-->
			$(this).css("color","#43fedf");
		});
		$("#event2").on("mouseover", function(){ <!-- Html Element: p id: event2: event: Changes text on mouseover-->
			$(this).text("A clock.");
		});
		$("#event2").on("mouseout", function(){ <!-- Html Element: p id: event2: event: Changes text back to original on mouseout-->
			$(this).text("What has hands but can not clap?");
		});	
		$("#event3").on("dblclick", function(){ <!-- Html Element: p id: event3: event: Changes text back on doubleclick -->
			$(this).text("You double clicked!");
		});
		$(document).on("keyup", function(event){ <!-- Html Element: p id: event4: event: Reads and display which key is being pressed -->
			var key = String.fromCharCode(event.keyCode).toUpperCase();
			$("#event4").text("You pressed key: " + key );
		});
});
