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

Library.prototype.premade = function(){
	this.addShelf(new Shelf("Shelf1"));

	this.addShelf(new Shelf("Shelf2"));

	this.addShelf(new Shelf("Shelf3"));

	this.shelves[0].addBook(new Book("Book1", "me", 100));
	this.shelves[0].addBook(new Book("Book1", "me", 100));
	this.shelves[0].addBook(new Book("Book1", "me", 100));
	this.shelves[0].addBook(new Book("Book2", "foo", 200));
	this.shelves[0].addBook(new Book("Book2", "foo", 200));
	this.shelves[0].addBook(new Book("Book3", "foo", 300));
	this.shelves[0].addBook(new Book("Book3", "foo", 300));
	this.shelves[0].addBook(new Book("Book3", "foo", 300));
	this.shelves[0].addBook(new Book("Book4", "me", 400));

	this.shelves[1].addBook(new Book("Book5", "me", 500));
	this.shelves[1].addBook(new Book("Book5", "me", 500));
	this.shelves[1].addBook(new Book("Book5", "me", 500));
	this.shelves[1].addBook(new Book("Book5", "me", 500));
	this.shelves[1].addBook(new Book("Book5", "me", 500));
	this.shelves[1].addBook(new Book("Book6", "foo", 600));
	this.shelves[1].addBook(new Book("Book6", "foo", 600));
	this.shelves[1].addBook(new Book("Book8", "foo", 800));
	this.shelves[1].addBook(new Book("Book8", "foo", 800));

	this.shelves[2].addBook(new Book("Book9", "me", 900));
	this.shelves[2].addBook(new Book("Book1", "me", 100));
	this.shelves[2].addBook(new Book("Book2", "foo", 200));
	this.shelves[2].addBook(new Book("Book3", "foo", 300));
	this.shelves[2].addBook(new Book("Book5", "me", 500));
}