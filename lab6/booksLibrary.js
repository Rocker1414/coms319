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
	
	//need max length
	var maxLength = 0;
	for(var i = 0; i < this.shelves.length; i++){
		if(this.shelves[i].books.length > maxLength){
			maxLength = this.shelves[i].books.length;
		}
	}
	
	
	var output = "";
	
	output += "<table>";
	
	for(var i = 0; i < this.shelves.length; i++){
		output += ("<th>" + this.shelves[i].name + "</th>"); 
	}

	//loop through rows of books
	for(var i = 0; i < maxLength; i++){
		output += "<tr>";
		//loop through shelves
		for(var j = 0; j < this.shelves.length; j++){
			
			//input a blank row if shelf is out of books
			if(i < this.shelves[j].books.length){
				
				output += ("<td>" + this.shelves[j].books[i].getTitle() + "</td>");
			}
			else{
				output += ("<td>" + "</td>");
			}
			
		}
		output += "</tr>";
	}
	
	output += "</table>";
	
	return output;
};

Library.prototype.display = function(){ document.getElementById("content").innerHTML = this.toHTML(); };

Library.prototype.search = function(title){
	
	for(var i = 0; i < this.shelves.length; i++){
		
		var currentShelf = this.shelves[i];
		for(var j = 0; j < currentShelf.books.length; j++){
			
			var currentBook = currentShelf.books[j];
			
			if(currentBook.getTitle() == title){
				return i;
			}
			
		}
		
	}
	
	return -1;
	
};

Library.prototype.findBook = function(){
	
	var title = document.getElementById("title").value;
	var shelf = this.search(title);
	
	if(shelf == -1){
		document.getElementById("content").innerHTML = "Book not found"; 
	}
	else{
		this.shelves[shelf].display();
	}
	
};

Library.prototype.clearView = function(){ document.getElementById("content").innerHTML = ""; };