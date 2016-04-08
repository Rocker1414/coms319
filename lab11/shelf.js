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