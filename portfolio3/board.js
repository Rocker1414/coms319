function Board(width, height){

	this.height = height;
	this.width = width;
	this.grid = [[],[],[],[],[],[],[],[]];
}

Board.prototype.init = function(){
	for(var i = 0; i < this.height; i++){
		for(var j = 0; j < this.width; j++){
			this.grid[i].push(0);
		}
	}
}

Board.prototype.get = function(coords){

	pos = parseCoords(coords);

	x = pos[0];
	y = pos[1];

	return this.grid[x][y];
}