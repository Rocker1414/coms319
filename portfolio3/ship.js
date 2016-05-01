function Ship(size, start, orientation, marker){
	this.size = size;

	//1 is horizontal
	//-1 is vertical
	this.orientation = orientation;
	this.fragments = [];
	this.marker = marker;
	this.start = start;


	//patrol (p) = 2
	//sub (s) = 3
	//destroyer (d) = 3
	//battleship (b) = 4
	//carrier (c) = 5
}

Ship.prototype.isDestroyed = function(){
	//all fragments destroyed
	for(var i = 0; i < this.size; i++){
		//if any fragment not destroyed, ship intact
		if(!this.fragments[i].destroyed){
			return false;
		}
	}

	return true;
}

Ship.prototype.init = function(){

	var x = this.start[1];
	var y = this.start[0];
	for(var i = 0; i < this.size; i++){
		var frag = new Fragment(this, y, x, i);

		this.fragments.push(frag);

		//horizontal, increment y
		//verical, increment x
		if(this.orientation == 1){
			x++;
		}
		else if(this.orientation == -1){
			y++;
		}
		
	}

}

function Fragment(parent, y, x, index){
	this.x = x;
	this.y = y;
	this.index = index;
	this.parent = parent;

	this.destroyed = false;
}
