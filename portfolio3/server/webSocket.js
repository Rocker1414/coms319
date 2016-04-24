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

  server.clients.push(socket);

  socket.on("host", function(name, host, password){
    var gr = new GameRecord(name, host, password);
    gr.player1 = this;

    server.createGame(gr);

  });

  socket.on("join", function(id){
      var gr = server.findById(id);

      if(gr == null){
        //error
      }

      if(gr.player2 == null){
        gr.player2 = this;
      }
      else{
        //tell player its full
      }

  });

});

function Server(){
  this.clients = [];
  this.games = [];
}

Server.prototype.findById = function(id){
  for(var i = 0; i < this.games.length; i++){
    if(this.games[i].id == id){
      return this.games[i];
    }
  }

  return null;
}

Server.prototype.createGame = function(gr){
  this.games.push(gr);

  for(var i = 0; i < this.clients.length; i++){
    this.clients[i].emit("addGame", gr);
  }

}

function GameRecord(name, host, password){
  this.name = name;
  this.host = host;
  this.password = password;
  this.player1 = null;
  this.player2 = null;
}