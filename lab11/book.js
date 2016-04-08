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