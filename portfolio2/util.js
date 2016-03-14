//these are utility functions

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