var app = require('http').createServer(handler)
var io = require('socket.io')(app);

var util = require('util');
var path = require('path');
var http = require('http');
var fs   = require('fs');

app.listen(80);

var server = new Server();

// attach handler
function handler(req,res) {

  //res.setHeader('Content-Type', 'application/javascript');

  if(req.url == "/"){
    req.url = "/index.html";
  }

  var file = path.normalize('..' + req.url);

  fs.exists(file, function(exists) {
    if (exists) {
      var rs = fs.createReadStream(file);

      rs.on('error', function() {
        res.writeHead(500); // error status
        res.end('Internal Server Error');
      });


      res.writeHead(200); // ok status

      // PIPE the read stream with the RESPONSE stream
      rs.pipe(res);
    } 
    else {
      res.writeHead(404); // error status
      res.end('NOT FOUND');
    }
  });

}

io.on('connection', function(socket) {
  console.log("connection");
  server.clients.push(socket);
  server.allGames(socket);

  socket.on("host", function(data){
    var gr = new GameRecord(data[0], data[1], data[2]);
    gr.player1 = this;

    server.createGame(gr);

  });

  socket.on("test", function(){
    console.log("test");
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

  socket.on("disconnect", function(){
      console.log("DC");
     server.disconnect(socket);
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
  //need to handle already used ids
  var id = Math.floor((Math.random() * 10000000) + 1);
  gr.id = id;
  this.games.push(gr);

  for(var i = 0; i < this.clients.length; i++){
    this.clients[i].emit("addGame", [gr.id, gr.name, gr.host, gr.password]);
  }

}

Server.prototype.allGames = function(socket){
  for(var i = 0; i < this.games.length; i++){
      var gr = this.games[i];
      socket.emit("addGame", [gr.id, gr.name, gr.host, gr.password]);
  }
}

Server.prototype.disconnect = function(socket){
  for(var i = 0; i < this.clients.length; i++){
    if(this.clients[i] == socket){
      this.clients.splice(i, 1);
    }
  }
}

function GameRecord(name, host, password){
  this.name = name;
  this.host = host;
  this.password = password;
  this.player1 = null;
  this.player2 = null;
  this.id = null;
}
