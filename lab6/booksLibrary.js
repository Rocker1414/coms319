function Book(title){
	this.title = title;
}

function Shelf(pos){
	this.position = pos;
	
	this.contents = [];
}

function Library(){
	this.shelves = [];
}