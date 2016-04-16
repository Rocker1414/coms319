var io = require('socket.io').listen(5000);

var server = new Server();

io.sockets.on('connection', function(socket) {

  server.clients.push(socket);

  server.sendMessage("New connection");


});

function Server(){
  this.clients = [];
}

Server.prototype.broadcastMessage = function(message){
  for(var i = 0; i < this.clients.length; i++){
      var socket = this.clients[i];
      socket.emit('server', message);
  }
}


//event declaration
/* socket.on('eventName', function(content) {
  do something
}*/

//event trigger
//socket.emit('eventName', variable);