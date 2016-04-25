function Board(width, height){

	this.height = height;
	this.width = width;

	//a 0 on the grid represents not fired
	//a 1 on the grid represents fired
	this.grid = [];

	//ship fragments
	this.ships = [];
	this.markers = [];

}

Board.prototype.attachGrid = function(grid){
	for(var i = 0; i < grid.length; i++){
		var row = grid[i];
		for(var j = 0; j < row.length; j++){
			this.grid[i][j] = row[j];
		}
	}
}

Board.prototype.attachGrid = function(grid){
	for(var i = 0; i < grid.length; i++){
		var row = grid[i];
		for(var j = 0; j < row.length; j++){
			this.grid[i][j] = row[j];
		}
	}
}

Board.prototype.init = function(){
	for(var i = 0; i < this.height; i++){
		var arr1 = [];
		var arr2 = [];
		var arr3 = [];
		for(var j = 0; j < this.width; j++){

			arr1.push(0);
			arr2.push(0);
			arr3.push(0);
		}
		this.grid[i] = arr1;
		this.ships[i]= arr2;
		this.markers[i] = arr3;
	}
}

Board.prototype.getImage = function(coords){

	x = coords[1];
	y = coords[0];

	if(this.ships[y][x] != 0){
		return markerToImage(this.markers[y][x], this.ships[y][x].index, (this.ships[y][x].parent.orientation == 1));
	}
	else{
		return "";
	}

	
}

Board.prototype.showHitImage = function(coords){
	x = coords[1];
	y = coords[0];
	
	if((this.ships[y][x] != 0)  && (this.grid[y][x] == 1) && (this.ships[y][x].parent.isDestroyed())){
		return markerToImage(this.markers[y][x], this.ships[y][x].index, (this.ships[y][x].parent.orientation == 1));
	}
	else{
		return "";
	}
	
}

Board.prototype.getShip = function(coords){

	x = coords[1];
	y = coords[0];


	return this.ships[y][x];


	
}

Board.prototype.fire = function(coords){
	console.log(coords);
	x = coords[1];
	y = coords[0];

	if(this.ships[y][x] == 0){
		this.grid[y][x] = -1;
		return -1;
	}
	else{
		this.grid[y][x] = 1;
		this.ships[y][x].destroyed = true;
		//return the hit fragment
		return this.ships[y][x];
	}

}

Board.prototype.returnUntouched = function(){
	var arr = [];

	for(var i = 0; i < this.height; i++){
		for(var j = 0; j < this.width; j++){
			if(this.grid[i][j] == 0){
				arr.push([i,j]);
			}
		}
	}

	return arr;

}

Board.prototype.isClear = function(coords, orientation, length){
	var y = coords[0];
	var x = coords[1];

	//make sure all squares are clear
	for(var i = 0; i < length; i++){
		//always make sure within bounds
		if(x >= 0 && x < this.width && y >= 0 && y < this.height){

			//make sure space clear, if not, return false
			if(this.ships[y][x] != 0){
				return false;
			}
		}
		else{
			return false;
		}

		if(orientation == 1){
			x++;
		}
		else if(orientation == -1){
			y++;
		}
	}

	return true;

}