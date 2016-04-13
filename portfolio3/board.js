function Board(width, height){

	this.height = height;
	this.width = width;

	//a 0 on the grid represents not fired
	//a 1 on the grid represents fired
	this.grid = [];

	//need something for ships
	this.ships = [];
	this.markers = [];
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

Board.prototype.getMarker = function(coords){

	x = coords[1];
	y = coords[0];

	if(this.ships[y][x] != 0){
		return this.markers[y][x];
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