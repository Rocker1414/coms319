function Player(){
	this.board = new Board(8,8);
	this.board.init();

	this.ships = [];
}


Player.prototype.init = function(){

}

Player.prototype.placeShips = function(){
	//place fragments
	for(var i = 0; i < this.ships.length; i++){
		var ship = this.ships[i];

		for(var j = 0; j < ship.fragments.length; j++){

			var frag = ship.fragments[j];
			var x = frag.x;
			var y = frag.y;
			this.board.ships[y][x] = frag;
			this.board.markers[y][x] = frag.parent.marker;
		}

	}
}



Player.prototype.predefined = function(){

	var s = new Ship(2, [1,0], -1, "p");
	s.init();
	this.ships.push(s);

	var s = new Ship(3, [3,2], -1, "s");
	s.init();
	this.ships.push(s);

	var s = new Ship(3, [2,6], -1, "d");
	s.init();
	this.ships.push(s);

	var s = new Ship(4, [0,2], 1, "b");
	s.init();
	this.ships.push(s);

	var s = new Ship(5, [7,3], 1, "c");
	s.init();
	this.ships.push(s);

}