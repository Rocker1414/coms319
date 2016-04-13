function AI(){
	
	this.board = new Board(8,8);
	this.board.init();
	//keeps track of the result of each cell
	this.resultHistory = [];

	//keep track of cells the AI has picked.
	this.turnHistory = [];
}

AI.prototype.doTurn = function(board){

	//pick a random cell from available
	var available = board.returnUntouched();

	//[x,y] coords
	var selection = available[Math.floor((Math.random() * available.length))];
	this.turnHistory.push(selection); 
	var result = board.fire(selection);
	this.resultHistory.push(result);
}