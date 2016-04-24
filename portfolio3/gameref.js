function GameRef(name, host, password){
	this.name = name;
	this.host = host;
	this.password = password;

}

GameRef.prototype.hasPassword = function(){
	if(this.password != null){
		return "x";
	}
	else{
		return "";
	}
}