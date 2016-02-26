function startDraw(){

	window.requestAnimFrame = (function(callback) {
			return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
			function(callback) {
			  window.setTimeout(callback, 1000 / 60);
			};
		  })();
	x = 0;
	y = 200;

	direction = 0;
	
	moveCursor();

}

function moveCursor(){
	var canvas = document.getElementById('cv');
    var context = canvas.getContext('2d');
	context.beginPath();
	
	var newX = x;
	var newY = y;
	
	newX += (direction == 0) ? 1 : 0;
	newY -= (direction == 90) ? 1 : 0; 
	newX -= (direction == 180) ? 1 : 0; 
	newY += (direction == 270) ? 1 : 0; 	
	
	context.moveTo(newX, newY);
	
	context.lineTo(x, y);
	context.closePath();
	
	context.stroke();
	
	x = newX;
	y = newY;
	
	//moveCursor();
	
	
	requestAnimFrame(function() {
          moveCursor();
        });

}

function turnLeft(){
	if(direction == 270){
		direction = 0;
	}
	else{
		direction += 90;
	}
}

function turnRight(){
	if(direction == 0){
		direction = 270;
	}
	else{
		direction -= 90;
	}
	
}