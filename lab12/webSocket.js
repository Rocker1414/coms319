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
         this.emit('posts', getPosts(name));

         server.broadcastMessage(name + " logged in.", this);
      }
  	else{
  		this.emit('fail');
  	}
  });

  socket.on('sendPost', function(data){

      //if title exists, create new, else update
       var title = data[0];
        var content = data[1];

        var post = new Post(title, gName, content);

        if(post.exists(post)){

            updatePost(post);
            server.sendPosts();
            server.broadcastMessage(gName + " has updated the post " + title + ".", this);

        }
        else{
          newPost(post);
          server.sendPosts();
          server.broadcastMessage(gName + " has created the post " + title + ".", this);
        }

  });

  socket.on('logout', function(){
      server.broadcastMessage(gName + " has logged out.", this);
      this.emit('reset');
    }); 

   socket.on('disconnect', function(){
      server.broadcastMessage(gName + " has logged out.", this);
      this.emit('reset');
    }); 

});

function Server(){
  this.users = [];
}

Server.prototype.broadcastMessage = function(message, sender){
  for(var i = 0; i < this.users.length; i++){

      if(sender != this.users[i].socket){
        var socket = this.users[i].socket;
        socket.emit('broadcast', message);
      }
      
  }
}

Server.prototype.sendPosts = function(){
  for(var i = 0; i < this.users.length; i++){
      var socket = this.users[i].socket;
      socket.emit('posts', getPosts(this.users[i].name));
  }
}

function User(name, socket){
  this.name = name;
  this.socket = socket;
  this.loggedIn = false;
}

function Post(title, author, content){
  this.title = title;
  this.author = author;
  this.content = content;
}

Post.prototype.exists = function(post){
  var postData = JSON.parse(fs.readFileSync('posts.txt', 'utf8'));

  for(var i = 0; i < postData.length; i++){
    if(postData[i]["title"] == post.title && postData[i]["author"] == post.author){
      return true;
    }
  }

  return false;
}

function getPosts(name){
  var postData = JSON.parse(fs.readFileSync('posts.txt', 'utf8'));

  var all = "";

  for(var i = postData.length-1; i >= 0; i--){
    var current = "<div class='post'>";
    current = current + "<h1>" + postData[i]["title"] + "</h1>";

    //update link
    if(name == postData[i]["author"]){
      current = current + "<a onclick='update(\"" + postData[i]["title"] + "\", \"" + postData[i]["content"] + "\")' href='#'>Update</a>";
    }
  
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

function updatePost(post){
  var postData = JSON.parse(fs.readFileSync('posts.txt', 'utf8'));

  for(var i = 0; i < postData.length; i++){
    if(postData[i]["title"] == post.title && postData[i]["author"] == post.author){
      postData[i]["content"] = post.content;
      postData[i]["date"] = (new Date()).toDateString();

      fs.writeFileSync('posts.txt', JSON.stringify(postData), 'utf8');

      return;
    }
  }
}

function newPost(post){
  var postData = JSON.parse(fs.readFileSync('posts.txt', 'utf8'));
  var date = (new Date()).toDateString();

  var np = {"title": post.title, "author": post.author, "date": date, "content": post.content};

  postData.push(np);

  fs.writeFileSync('posts.txt', JSON.stringify(postData), 'utf8');

}

