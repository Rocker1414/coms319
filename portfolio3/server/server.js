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
    gr.players[0] = this;

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
        gr.players[1] = this;

        gr.players[0].emit("hostSuccess", id);
        gr.players[1].emit("joinSuccess", id);

        //remove from other players list
        server.sendRemove(id);

      }
      else{
        //tell player its full
      }

  });

  socket.on("disconnect", function(){
      console.log("DC");
     server.disconnect(socket);
  });


  //game code
  //on a new game, opponents need to pass each other their boards
  socket.on("fire", function(data){
    var id = data[0];
    var y = data[1];
    var x = data[2];
    //pass move to opponent
    var gr = server.findById(id);
    var n = 1;

    if(this == gr.players[1]){n = 0;}

    gr.players[n].emit("opponentFire", [y,x]);
    gr.players[n].emit("giveTurn");
  });

  socket.on("gameConnect", function(id){
    //kick anyone who shouldnt be here
    var gr = server.findById(id);

    if(this != gr.players[0] && this != gr.players[1]){
      this.emit("kick");
    }

  });

  socket.on("gameReady", function(id){


    var gr = server.findById(id);
    for(var i = 0; i < gr.players.length; i++){
      if(this == gr.players[i]){
        gr.ready[i] = 1;
      }
    }

    if(gr.ready[0] == 1 && gr.ready[1] == 1){
      gr.players[gr.goFirst].emit("playerFirst");

      if(gr.goFirst == 0){gr.players[1].emit("opponentFirst");}
      if(gr.goFirst == 1){gr.players[0].emit("opponentFirst");}
      
    }


  });

  socket.on("sendShip", function(data){
    var id = data[0];
     var gr = server.findById(id);
     var size = data[1];
     var y = data[2];
     var x = data[3];
     var ori = data[4];
     var marker = data[5];

     var n = 1;
    if(this == gr.players[1]){n = 0;}

    gr.players[n].emit("recieveShip", [size, y, x, ori, marker]);
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
  var goFirst = Math.floor((Math.random() * 2));
  gr.id = id;
  gr.goFirst = goFirst;
  this.games.push(gr);

  for(var i = 0; i < this.clients.length; i++){
    this.clients[i].emit("addGame", [gr.id, gr.name, gr.host, gr.password]);
  }

}

Server.prototype.allGames = function(socket){
  for(var i = 0; i < this.games.length; i++){
      var gr = this.games[i];
      if(gr.players[1] == null){
        socket.emit("addGame", [gr.id, gr.name, gr.host, gr.password]);
      }

  }
}

Server.prototype.sendRemove = function(id){
  for(var i = 0; i < this.clients.length; i++){
    this.clients[i].emit("removeGame", id);
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
  this.players = [null, null];
  this.id = null;
  this.goFirst = -1;
  this.ready = [0, 0];
}
