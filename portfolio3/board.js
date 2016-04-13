function Board(width, height){

	this.height = height;
	this.width = width;

	//a 0 on the grid represents not fired
	//a 1 on the grid represents fired
	this.grid = [];

	//need something for ships
	this.ships = [];

}

Board.prototype.init = function(){
	for(var i = 0; i < this.height; i++){
		var arr1 = [];
		var arr2 = [];
		for(var j = 0; j < this.width; j++){

			arr1.push(0);
			arr2.push(0);
		}
		this.grid[i] = arr1;
		this.ships[i]= arr2;
	}
}

Board.prototype.getShip = function(coords){

	x = coords[0];
	y = coords[1];

	if(this.ships[x][y] != 0){
		return this.ships[x][y];
	}
	else{
		return "";
	}

	
}

Board.prototype.fire = function(coords){

	x = coords[0];
	y = coords[1];

	if(this.ships[x][y] == 0){
		this.grid[x][y] = -1;
	}
	else{
		this.grid[x][y] = 1;
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