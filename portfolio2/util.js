//these are utility functions

function calculateNumCount(nums, target){
	var total = 0;
	for(var i = 0; i < nums.length; i++){
		if(nums[i] == target){
			total += target;
		}
	}

	return total;
}

function diceTotal(nums){
	var total = 0;
	for(var i = 0; i < nums.length; i++){
		total += nums[i];
	}

	return total;

}

function ofAKind(nums, target){
	var sNums = nums.sort();
	var count = 1;
	var current = sNums[0];
	for(var i = 1; i < sNums.length; i++){
		if(sNums[i] == current){
			count++;
		}
		else{
			current = sNums[i];
			count = 1;
		}

		if(count >= target){
			return true;
		}
	}

	return false;

}

function fullHouse(nums){
	var sNums = nums.sort();
	//if [0] and [4] are equal then they are all same, return false
	if(sNums[0] == sNums[4]){
		return false;
	}

	var first = sNums[0];
	var second;
	//have to at least have next match
	if(sNums[1] == first){

		//case 2, 3
		second = sNums[2];

		if(sNums[3] == second && sNums[4] == second){

			return true;
		}


		//case 3, 2
		if(sNums[2] == first){
			second = sNums[3];

			if(sNums[4] == second){
				return true;
			}
		}
	}

	return false;

	
}

function smallStraight(nums){
	var sNums = nums.sort();
	var first = sNums[0];

	if(sNums[1] != first+1){

		var current;
		for(var i = 1; i < sNums.length-1; i++){
			current = sNums[i];

			if(sNums[i+1] != current+1){
				return false;
			}
			
		}

		return true;
	}
	else{

		if(sNums[2] == sNums[1]+1 && sNums[3] == sNums[2]+1){
			return true;
		}
		else{
			return false;
		}

	}
	
	


}

function largeStraight(nums){
	var sNums = nums.sort();
	//all must sequence
	var current;
	for(var i = 0; i < sNums.length-1; i++){
		current = sNums[i];

		if(sNums[i+1] != current+1){
			return false;
		}
		
	}

	return true;

}

function yahtzee(nums){
	//if all same
	var target = nums[0];
	if(target < 1 || target > 6){
		return false;
	}

	var count = 1;
	for(var i = 1; i < nums.length; i++){
		if(nums[i] == target){
			count++;
		}
	}

	if(count == 5){
		return true;
	}
	else{
		return false;
	}

	
}

function drawTopLeft(dice){
	var x = (dice.width/3)/2;
	var y = (dice.height/3)/2;
	drawCircle(dice, [x,y]);
}

function drawMiddleLeft(dice){
	var x = (dice.width/3)/2;
	var y = dice.height/2;
	drawCircle(dice, [x,y]);
}
function drawBottomLeft(dice){
	var x = (dice.width/3)/2;
	var y = dice.height/2 + dice.height/3;
	drawCircle(dice, [x,y]);
}

function drawMiddle(dice){
	var x = dice.width/2;
	var y = dice.height/2;
	drawCircle(dice, [x,y]);
	
}

function drawTopRight(dice){
	var x = dice.width/2 + dice.width/3;
	var y = (dice.height/3)/2;
	drawCircle(dice, [x,y]);
}

function drawMiddleRight(dice){
	var x = dice.width/2 + dice.width/3;
	var y = dice.height/2;
	drawCircle(dice, [x,y]);
}

function drawBottomRight(dice){
	var x = dice.width/2 + dice.width/3;
	var y = dice.height/2 + dice.height/3;
	drawCircle(dice, [x,y]);
}

function drawCircle(dice, oPair){
	var x = oPair[0];
	var y = oPair[1];
	var radius = (dice.width/3)/3;

	var ctx = dice.getContext("2d");
	ctx.beginPath();
	ctx.arc(x,y,radius,0,2*Math.PI);
	ctx.fillStyle = 'black';
    ctx.fill();
	ctx.stroke();
}

function getSelected(){
	var radios = document.getElementsByName("score");

	for (var i = 0, length = radios.length; i < length; i++) {
		if (radios[i].checked)
			return radios[i].value;
    }
	
	var msg = "Warning: Please make a scoring selection."
	error(msg);
}	

function setScore(s){
	var score = document.getElementById(s + "Roll").innerHTML;
	var field = document.getElementById(s + "Field");
	if(field.getAttribute("scored") == "false"){
		field.innerHTML = score;
		field.setAttribute("scored", true);
		field.setAttribute("bgcolor", "#8CDD81");
		this.resetRollsLeft();
		this.reset
		error("");
		return true;
	}
	
	else{
		msg = "Warning: " + s + " field already set. Please make a different selection.";
		error(msg);
		return false;
	}
}

function setYahtzeeScore(){
	var score = document.getElementById("yahtzeeScoreRoll").innerHTML;
	var field = document.getElementById("yahtzeeScoreField");
	
	if(field.getAttribute("scored") == "false"){
		field.innerHTML = score;
		field.setAttribute("scored", true);
		field.setAttribute("bgcolor", "#8CDD81");
		this.resetRollsLeft();
		error("");
		return true;
	}
	else if(parseInt(score) > 0){
		field.innerHTML = parseInt(field.innerHTML) + 100;
		msg = "Yahtzee bonus applied (+100 points)";
		error(msg);
		this.resetRollsLeft();
		return true;
	}	
	else{
		msg = "Warning: Yahtzee field already set. Please make a different selection.";
		error(msg);
		return false;
	}
}

function error(msg){
	document.getElementById("error").innerHTML = msg;
}

function isTurn(){
	var rollsLeft = this.getRollsLeft();
	
	if(rollsLeft > 0)
		return true;
	else
		return false;
}

function getRollsLeft(){
	var rollButton = document.getElementById("rollButton");
	return rollButton.getAttribute("count");
}

function updateRollsLeft(){
	var rollButton = document.getElementById("rollButton");
	var rollsLeft = rollButton.getAttribute("count");
	rollButton.setAttribute("count", rollsLeft - 1);
}

function resetRollsLeft(){
	var rollButton = document.getElementById("rollButton");
	rollButton.setAttribute("count", 3);
}

function sumUpperSection(){
	var sum =  parseInt(document.getElementById("aceScoreField").innerHTML) + parseInt(document.getElementById("twoScoreField").innerHTML) + 
			  parseInt(document.getElementById("threeScoreField").innerHTML) + parseInt(document.getElementById("fourScoreField").innerHTML) +
			  parseInt(document.getElementById("fiveScoreField").innerHTML) + parseInt(document.getElementById("sixScoreField").innerHTML);
	return sum;
}

function sumLowerSection(){
	var sum =  parseInt(document.getElementById("threeOfAKindScoreField").innerHTML) + parseInt(document.getElementById("fourOfAKindScoreField").innerHTML) + 
			  parseInt(document.getElementById("fullHouseScoreField").innerHTML) + parseInt(document.getElementById("smallStraightScoreField").innerHTML) +
			  parseInt(document.getElementById("largeStraightScoreField").innerHTML) + parseInt(document.getElementById("yahtzeeScoreField").innerHTML) +
			  parseInt(document.getElementById("chanceScoreField").innerHTML);
	return sum;
}

function resetRollScores(){
	document.getElementById("aceScoreRoll").innerHTML = 0;
	document.getElementById("twoScoreRoll").innerHTML = 0;
	document.getElementById("threeScoreRoll").innerHTML = 0;
	document.getElementById("fourScoreRoll").innerHTML = 0;
	document.getElementById("fiveScoreRoll").innerHTML = 0;
	document.getElementById("sixScoreRoll").innerHTML = 0;
	document.getElementById("threeOfAKindScoreRoll").innerHTML = 0;
	document.getElementById("fourOfAKindScoreRoll").innerHTML = 0;
	document.getElementById("fullHouseScoreRoll").innerHTML = 0;
	document.getElementById("smallStraightScoreRoll").innerHTML = 0;
	document.getElementById("largeStraightScoreRoll").innerHTML = 0;
	document.getElementById("yahtzeeScoreRoll").innerHTML = 0;
	document.getElementById("chanceScoreRoll").innerHTML = 0;
};