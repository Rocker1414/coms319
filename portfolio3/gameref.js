function GameRef(id, name, host, password){
	this.id = id;
	this.name = name;
	this.host = host;
	this.password = password;

}

GameRef.prototype.hasPassword = function(){
	if(this.password != ""){
		return "x";
	}
	else{
		return "";
	}
}