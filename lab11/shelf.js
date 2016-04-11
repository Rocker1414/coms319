function Shelf(name){
	this.name = name;
	
	this.books = [];
}
Shelf.prototype.addBook = function(book){ this.books.push(book); };