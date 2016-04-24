function Game(){
	this.player = 0;
	this.opponent = 0;

	this.message = "";

	this.state = 0;

}

Game.prototype.init = function(){
	this.player = new Player();
	this.player.randomShips();

	this.opponent = new AI();
	this.opponent.randomShips();
}

Game.prototype.isOver = function(){
	//return 0 for not over, -1 for opponent won, 1 for player won
	if(this.player.isDefeated()){
		return -1;
	}
	else if(this.opponent.isDefeated()){
		return 1;
	}
	else{
		return 0;
	}
}

Game.prototype.multiInit = function(){
	this.player = new Player();
	this.player.randomShips();

	this.opponent = new Player();
	this.opponent.randomShips();
}