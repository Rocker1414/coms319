function Book(title){
	this.title = title;
}
Book.prototype.getTitle = function(){ return this.title; };

function Shelf(name){
	this.name = name;
	
	this.books = [];
}
Shelf.prototype.addBook = function(book){ this.books.push(book); };
Shelf.prototype.getBooks = function(){ return this.books; };
Shelf.prototype.toHTML = function(){

	var output = "";
	
	output += "<table>";
	output += ("<th>" + this.name + "</th>"); 
	for(var i = 0; i < this.books.length; i++){
		output += "<tr>";
		output += ("<td>" + this.books[i].getTitle() + "</td>");
		output += "</tr>";
	}
	
	output += "</table>";
	
	return output;
	
};
Shelf.prototype.display = function(){ document.getElementById("content").innerHTML = this.toHTML(); };

function Library(){
	this.shelves = [];
}

Library.prototype.addShelf = function(shelf){ this.shelves.push(shelf); };
Library.prototype.getShelves = function(){ return this.shelves; };
Library.prototype.toHTML = function(){
	
	var output = "";
	
	output += "<table>";
	
	for(var i = 0; i < this.shelves.length; i++){
		output += ("<th>" + this.shelves[i].name + "</th>"); 
	}
	
	var cells = [];
	for(var i = 0; i < this.shelves.length; i++){
		cells.push([]);
		
	}
	
	var allEmpty
	while(
	for(var i = 0; i < this.shelves.length; i++){
		
		var shelf = this.shelves[i];
		
		for(var j = 0; j < shelf.books.length; j++){
			output += "<tr>";
			output += ("<td>" + this.books[i].getTitle() + "</td>");
			output += "</tr>";
		}
	}
	
	output += "</table>";
	
	return output;
};

Library.prototype.display = function(){ document.getElementById("content").innerHTML = this.toHTML(); };