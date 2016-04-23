var io = require('socket.io').listen(5000);
var fs = require('fs');

var server = new Server();

//event declaration
/* socket.on('eventName', function(content) {
  do something
}*/

//event trigger
//socket.emit('eventName', variable);


io.sockets.on('connection', function(socket) {
	var gName;
	
  socket.on('login', function(name, password){
	var logins = JSON.parse(fs.readFileSync('users.txt', 'utf8'));
	var valid = false;
	gName = name;
	for(var i = 0; i < logins.length; i++){
		if(logins[i]["user"] == name && logins[i]["pass"] == password){
			valid = true;
			break;
		}
	}
	
    if (valid){
       var u = new User(name, this);
       server.users.push(u);
       this.emit('success');
       this.emit('posts', getPosts());

       server.broadcastMessage(name + " logged in.");
    }
	else{
		this.emit('fail');
	}
	
	socket.on('logout', function(){
		server.broadcastMessage(gName + " has logged out.");
		this.emit('reset');
	});	
  });
});

function Server(){
  this.users = [];
}

Server.prototype.broadcastMessage = function(message){
  for(var i = 0; i < this.users.length; i++){
      var socket = this.users[i].socket;
      socket.emit('broadcast', message);
  }
}

Server.prototype.sendPosts = function(){
  for(var i = 0; i < this.users.length; i++){
      var socket = this.users[i].socket;
      socket.emit('posts', getPosts());
  }
}

function User(name, socket){
  this.name = name;
  this.socket = socket;
  this.loggedIn = false;
}

function getPosts(){
  var postData = JSON.parse(fs.readFileSync('posts.txt', 'utf8'));

  var all = "";

  for(var i = 0; i < postData.length; i++){
    var current = "<div class='post'>";
    current = current + "<h1>" + postData[i]["title"] + "</h1>";

    //update link
    //current = current + "<a onclick='update(\"" + postData[i]["title"] + "\", \"" + postData[i]["content"] + "\")' href='#'>Update</a>";
    //author
    current = current + "<h3>Author: " + postData[i]["author"] + "</h3>";
    //date

    current = current + "<h3>Last Updated: " + postData[i]["date"] + "</h3>";
    
    current = current + "<p class='content'>" + postData[i]["content"] + "</p>";
    
    current = current + "</div>";
    
    all = all + current + "<br>";



  }

  return all;
}
