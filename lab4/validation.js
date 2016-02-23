function validate_form(){
	var errs = [];
	//first make sure all the required fields are filled in
	
	//name
	var name = document.getElementById("form").elements["name"].value;
	//console.log(name);
	if(name == ""){
		errs.push("Please enter your name");
	}
	
	//make sure gender not default value
	var gender = document.getElementById("form").elements["gender"].value;
	//console.log(gender);
	if(gender == "default"){
		errs.push("Please select your gender");
	}
	
	//address
	var address = document.getElementById("form").elements["address"].value;
	//console.log(address);
	if(address == ""){
		errs.push("Please enter your address");
	}
	
	//email
	var email = document.getElementById("form").elements["email"].value;
	//console.log(email);
	if(email == ""){
		errs.push("Please enter your email");
	}

	//do phone parsing
	var phone = document.getElementById("form").elements["phone"].value;
	
	var num_valid = numberValidation(phone);
	
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
function numberValidation(number){
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
		//xxx-xxx-xxxx
		//split into array of elements
		var numArr = number.split("-");

		
		//must have only 3 elements
		if(numArr.length != 3){
			console.log("Wrong # dashes");
			return 0;
		}
		
		console.log(numArr[0].length);
		console.log(numArr[1].length);
		console.log(numArr[2].length);
		
		//1st and 2nd must have 3 length, last must have 4
		if(numArr[0].length != 3 || numArr[1].length != 3 || numArr[2].length != 4){
			console.log("Wrong size");
			return 0;
		}
		//whole must parse of int
		var numNoDash = parseNumber(number);
		console.log(numNoDash);
		
		var intNum = parseInt(numNoDash);
		console.log(intNum);
		if(intNum == NaN){
			console.log("Not number");
			return 0;
		}
		
		return 1;
		
	}
	
}

//function for removing the dashes from a number
function parseNumber(number){
	while(number.indexOf('-') != -1)
	{
		number = number.replace(/-/i, '');
	}
	
	
	return number;
}


