function Player(){
	this.board = new Board(8,8);
	this.board.init();
}

Player.prototype.predefined = function(){
	this.board.ships[0][0] = "p";
	this.board.ships[1][0] = "p";

	this.board.ships[3][2] = "s";
	this.board.ships[4][2] = "s";
	this.board.ships[5][2] = "s";

	this.board.ships[2][6] = "d";
	this.board.ships[3][6] = "d";
	this.board.ships[4][6] = "d";

	this.board.ships[0][2] = "b";
	this.board.ships[0][3] = "b";
	this.board.ships[0][4] = "b";
	this.board.ships[0][5] = "b";

	this.board.ships[7][3] = "c";
	this.board.ships[7][4] = "c";
	this.board.ships[7][5] = "c";
	this.board.ships[7][6] = "c";
	this.board.ships[7][7] = "c";
}