function validate_form(){
	var errs = [];
	//first make sure all the required fields are filled in
	
	//name
	var name = document.getElementById("form").elements["name"].value;
	console.log(name);
	if(name == ""){
		errs.push("Please enter your name");
	}
	
	//make sure gender not default value
	var gender = document.getElementById("form").elements["gender"].value;
	console.log(gender);
	if(gender == "default"){
		errs.push("Please select your gender");
	}
	
	//address
	var address = document.getElementById("form").elements["address"].value;
	console.log(address);
	if(address == ""){
		errs.push("Please enter your address");
	}
	
	//email
	var email = document.getElementById("form").elements["email"].value;
	console.log(email);
	if(email == ""){
		errs.push("Please enter your email");
	}

	//do phone parsing
	var phone = document.getElementById("form").elements["phone"].value;
	
	var num_valid = number_validation(phone);
	
	if(num_valid == 0){
		errs.push("Entered phone number not valid.");
	}

	
	if(errs.length == 0){
		alert("Form submitted!");
	}
	else{
		alert(errs);
	}

}

//make sure dashes are in right place
function number_validation(number){
	//first check if no dashes and if right size
	if(number.indexOf('-') != -1 && number.length == 10){
		//if parseable, return 1
		var intNum = parseInt(number);
		if(intNum != NaN){
			return 1;
		}
		else{
			return 0;
		}
	}
	else{
		//make sure dashes in right places
		
		//if they are, remove
		
		
	}
	
}

//function for removing the dashes from a number
function parse_number(number){
	while(number.indexOf('-') != -1)
	{
		number = number.replace(/-/i, '');
	}
	
	console.log(number);
	
	return number;
}


