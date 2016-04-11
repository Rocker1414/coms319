function Library(){
	this.shelves = [];
}

Library.prototype.addShelf = function(shelf){ this.shelves.push(shelf); }

Library.prototype.premade = function(){
	this.addShelf(new Shelf("Shelf1"));

	this.addShelf(new Shelf("Shelf2"));

	this.addShelf(new Shelf("Shelf3"));

	this.shelves[0].addBook(new Book("Book1", "Ian", 100));
	this.shelves[0].addBook(new Book("Book1", "Ian", 100));
	this.shelves[0].addBook(new Book("Book1", "Ian", 100));
	this.shelves[0].addBook(new Book("Book2", "Chris", 200));
	this.shelves[0].addBook(new Book("Book2", "Chris", 200));
	this.shelves[0].addBook(new Book("Book3", "Chris", 300));
	this.shelves[0].addBook(new Book("Book3", "Chris", 300));
	this.shelves[0].addBook(new Book("Book3", "Chris", 300));
	this.shelves[0].addBook(new Book("Book4", "Ian", 400));

	this.shelves[1].addBook(new Book("Book5", "Ian", 500));
	this.shelves[1].addBook(new Book("Book5", "Ian", 500));
	this.shelves[1].addBook(new Book("Book5", "Ian", 500));
	this.shelves[1].addBook(new Book("Book5", "Ian", 500));
	this.shelves[1].addBook(new Book("Book5", "Ian", 500));
	this.shelves[1].addBook(new Book("Book6", "Chris", 600));
	this.shelves[1].addBook(new Book("Book6", "Chris", 600));
	this.shelves[1].addBook(new Book("Book8", "Chris", 800));
	this.shelves[1].addBook(new Book("Book8", "Chris", 800));

	this.shelves[2].addBook(new Book("Book9", "Ian", 900));
	this.shelves[2].addBook(new Book("Book1", "Ian", 100));
	this.shelves[2].addBook(new Book("Book2", "Chris", 200));
	this.shelves[2].addBook(new Book("Book3", "Chris", 300));
	this.shelves[2].addBook(new Book("Book5", "Ian", 500));
}

Library.prototype.bookIndex = function(){
	//get max book and construct

	var arr = [];
	var maxBook = 0;

	for(var i = 0; i < this.shelves.length; i++){
		if(this.shelves[i].books.length > maxBook){
			maxBook = this.shelves[i].books.length;
		}
	}


	for(var i = 0; i < maxBook; i++){
		arr.push(i);
	}

	return arr;
}

Library.prototype.shelfIndex = function(){
	//contruct array thats num of shelves
	var arr = [];

	for(var i = 0; i < this.shelves.length; i++){
		arr.push(i);
	}

	return arr;
}