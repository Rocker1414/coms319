//Book class and functions
function Book(title, author, pages){
	this.title = title;
	this.author = author;
	this.length = pages;
}
Book.prototype.getTitle = function(){ return this.title; };
Book.prototype.display = function(){
	var str = JSON.stringify(this);
	document.getElementById("content").innerHTML = str; 
};


//Shelf class and functions
function Shelf(name){
	this.name = name;
	
	this.books = [];
}
Shelf.prototype.addBook = function(book){ this.books.push(book); };
Shelf.prototype.getBooks = function(){ return this.books; };
Shelf.prototype.toHTML = function(pos){

	var output = "";
	
	output += "<table>";
	output += ("<th>" + this.name + "</th>"); 
	for(var i = 0; i < this.books.length; i++){
		output += "<tr>";
		output += ("<td><a onclick=\"library.displayBook(" + pos + "," + i + ")\" href=\"javascript:void(0);\">" + this.books[i].getTitle() + "</a></td>");
		output += "</tr>";
	}
	
	output += "</table>";
	
	return output;
	
};
Shelf.prototype.display = function(pos){ document.getElementById("content").innerHTML = this.toHTML(pos); };

Shelf.prototype.display



//Library class and functions
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
				
				output += ("<td><a onclick=\"library.displayBook(" + j + "," + i + ")\" href=\"javascript:void(0);\">" + this.shelves[j].books[i].getTitle() + "</a></td>");

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

Library.prototype.displayAll = function(){ document.getElementById("content").innerHTML = this.toHTML(); };

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
		this.shelves[shelf].display(shelf);
	}
	
};

Library.prototype.listShelves = function(){
	
	var output = "";
	
	output += "<table>";

	for(var i = 0; i < this.shelves.length; i++){
		output += "<tr>";
		output += ("<td><a onclick=\"library.displayShelf(" + i + ")\" href=\"javascript:void(0);\">" + this.shelves[i].name + "</a></td>");
		output += "</tr>";
	}
	
	output += "</table>";
	
	document.getElementById("content").innerHTML = output; 
	
	
	
};

Library.prototype.clearView = function(){ document.getElementById("content").innerHTML = ""; };

Library.prototype.displayShelf = function(pos){
	this.shelves[pos].display(pos);
};

Library.prototype.displayBook = function(shelf, book){
	this.shelves[shelf].books[book].display();
};


//Populate library
var library = new Library();

library.addShelf(new Shelf("Shelf1"));

library.addShelf(new Shelf("Shelf2"));

library.addShelf(new Shelf("Shelf3"));

library.shelves[0].addBook(new Book("Book1", "me", 100));
library.shelves[0].addBook(new Book("Book1", "me", 100));
library.shelves[0].addBook(new Book("Book1", "me", 100));
library.shelves[0].addBook(new Book("Book2", "foo", 200));
library.shelves[0].addBook(new Book("Book2", "foo", 200));
library.shelves[0].addBook(new Book("Book3", "foo", 300));
library.shelves[0].addBook(new Book("Book3", "foo", 300));
library.shelves[0].addBook(new Book("Book3", "foo", 300));
library.shelves[0].addBook(new Book("Book4", "me", 400));

library.shelves[1].addBook(new Book("Book5", "me", 500));
library.shelves[1].addBook(new Book("Book5", "me", 500));
library.shelves[1].addBook(new Book("Book5", "me", 500));
library.shelves[1].addBook(new Book("Book5", "me", 500));
library.shelves[1].addBook(new Book("Book5", "me", 500));
library.shelves[1].addBook(new Book("Book6", "foo", 600));
library.shelves[1].addBook(new Book("Book6", "foo", 600));
library.shelves[1].addBook(new Book("Book8", "foo", 800));
library.shelves[1].addBook(new Book("Book8", "foo", 800));

library.shelves[2].addBook(new Book("Book9", "me", 900));
library.shelves[2].addBook(new Book("Book1", "me", 100));
library.shelves[2].addBook(new Book("Book2", "foo", 200));
library.shelves[2].addBook(new Book("Book3", "foo", 300));
library.shelves[2].addBook(new Book("Book5", "me", 500));

