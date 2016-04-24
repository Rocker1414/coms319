function Player(){
	//need to send for multi
	this.board = new Board(8,8);

	this.board.init();
	this.turn = false;

	//need to send for multi
	this.ships = [];
}


Player.prototype.init = function(){

}

Player.prototype.giveTurn = function(){
	this.turn = true;
}

Player.prototype.consumeTurn = function(){
	this.turn = false;
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

Player.prototype.placeShipIndex = function(i){
	var ship = this.ships[i];

		for(var j = 0; j < ship.fragments.length; j++){

			var frag = ship.fragments[j];
			var x = frag.x;
			var y = frag.y;
			this.board.ships[y][x] = frag;
			this.board.markers[y][x] = frag.parent.marker;
		}
}



Player.prototype.predefined1 = function(){

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

	this.placeShips();

}

Player.prototype.predefined2 = function(){

	var s = new Ship(2, [1,0], -1, "p");
	s.init();
	this.ships.push(s);

	var s = new Ship(3, [2,1], -1, "s");
	s.init();
	this.ships.push(s);

	var s = new Ship(3, [2,4], -1, "d");
	s.init();
	this.ships.push(s);

	var s = new Ship(4, [1,1], 1, "b");
	s.init();
	this.ships.push(s);

	var s = new Ship(5, [5,1], 1, "c");
	s.init();
	this.ships.push(s);

	this.placeShips();
}

Player.prototype.randomShips = function(){
	//pick random x, y, orientation
	
	var lengths = [2, 3, 3, 4, 5];
	var markers = ["p", "s", "d", "b", "c"];

	for(var i = 0; i < 5; i++){
		var placed = false;
		var length = lengths[i];
		var marker = markers[i];
		while(!placed){
			var x = Math.floor((Math.random() * this.board.width));
			var y = Math.floor((Math.random() * this.board.height));

			var ori = Math.pow(-1, Math.floor((Math.random() * 2) + 1));

			if(this.board.isClear([y, x], ori, length)){
				var s = new Ship(length, [y,x], ori, marker);
				s.init();
				this.ships.push(s);

				this.placeShipIndex(i);

				placed = true;
			}
		}
	}


}

Player.prototype.isDefeated = function(){

	//if any ship is not destroyed, still alive
	for(var i = 0; i < this.ships.length; i++){
		if(!this.ships[i].isDestroyed()){
			return false;
		}

	}

	return true;
}