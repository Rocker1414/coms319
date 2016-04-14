function AI(){
	
	this.board = new Board(8,8);
	this.board.init();
	
	this.hitQueue = [];
}

AI.prototype.doTurn = function(board){

	//if have a hit to parse, 
	if(this.hitQueue.length > 0){
		//head of queue
		var current = this.hitQueue[0];

		//if orientation is known
		if(current.orientation != 0){
			var didFire = false;
			var xTrial = current.x;
			var yTrial = current.y;
			while(!didFire){

				if(current.orientation == 1){
					xTrial += current.direction;
				}
				else{
					yTrial += current.direction;
				}


				//have next test coords
				if(xTrial >= 0 && xTrial < board.width && yTrial >= 0 && yTrial < board.height){

					//if space open, try it
					if(board.grid[yTrial][xTrial] == 0){
						var result = board.fire([yTrial, xTrial]);
						//if hit ship, keep direction
						if(result != -1){

						}
						//didnt hit ship, change direction
						else{
							current.direction = -current.direction;
						}

						didFire = true;

					}
					//already fired there, change direction
					else if(board.grid[yTrial][xTrial] == -1){
						current.direction = -current.direction;
					}

				}
				else{
					//if not in bounds, change direction
					current.direction = -current.direction;
				}


			}//while

		}//if

		//else try and guess
		else{
			//randomly go left,right,up, or down
			//make sure that space is open
			var didFire = false;

			while(!didFire){
				var dX = 0;
				var dY = 0;
				var direction = Math.floor((Math.random() * 4));
				switch(direction){
					case 0:
						//left
						dX = -1;
						break;
					case 1:
						//right
						dX = 1;
						break;
					case 2:
						//up
						dY = -1;
						break;
					case 3:
						dY = 1;
						//down
						break;
				}
				var xTrial = current.x + dX;
				var yTrial = current.y + dY;
				//make sure that space is within bounds
				if(xTrial >= 0 && xTrial < board.width && yTrial >= 0 && yTrial < board.height){
					//make sure space is open
					if(board.grid[yTrial][xTrial] == 0){
						//if open, fire
						var result = board.fire([yTrial, xTrial]);
						if(result != -1){
							
							//if hit same ship, know orientation
							if(dX != 0){
								current.orientation = 1;

							}
							else if(dY != 0){
								current.orientation = -1;

							}

							//if new ship, add to hit queue
						}

						didFire = true;
					}
				}
				

			}//while

			//if hit same ship, know orientation

			//if new ship, add to hit queue

		}//dont know ori

	}//if hit queue
	//guess random
	else{
		var available = board.returnUntouched();
		var selection = available[Math.floor((Math.random() * available.length))];
		var result = board.fire(selection);

		if(result != -1){
			//hit a ship
			//if ship was not already hit, create new hit
			var hit = new Hit(result.parent, selection[0], selection[1]);
			this.hitQueue.push(hit);
		}
	}

	//go through hits, if a ship is destroyed, remove
	this.checkHits();
}

AI.prototype.checkHits = function(){
	//go thorugh all hits, if a ship was destroyed, remove it from queue
	var remove = [];
	for(var i = 0; i < this.hitQueue.length; i++){
		if(this.hitQueue[i].shipRef.isDestroyed()){
			remove.push(i);
		}
	}

	for(var i = 0; i < remove.length; i++){
		this.hitQueue.splice(remove[i], 1);
	}
}


function Hit(shipRef, y, x){
	//reference to the ship

	//dont know the orientation initially
	this.orientation = 0;
	
	//pick random direction to try later
	var d = Math.floor((Math.random() * 2));

	switch(d){
		case 0:
			//left
			this.direction = -1;
			break;
		case 1:
			//right
			this.direction = 1;
			break;
	}


	this.shipRef = shipRef
	this.x = x;
	this.y = y;

	//first the AI tries to determine the orientation of the ship

	//once it knows that, it goes up and down until it destroys it

	//if another ship is hit, it is placed in the queue and taken down later
}