var app = angular.module('battleship', ['ngRoute','ngSanitize']);

app.filter('html', ['$sce', function ($sce) { 
    return function (code) {
        return $sce.trustAsHtml(code);
    };    
}]);

app.factory('socket', function ($rootScope) {
  var socket = io.connect('http://10.0.0.3');
  return {
    on: function (eventName, callback) {
      socket.on(eventName, function () {  
        var args = arguments;
        $rootScope.$apply(function () {
          callback.apply(socket, args);
        });
      });
    },

    emit: function (eventName, data, callback) {
      socket.emit(eventName, data, function () {
        var args = arguments;
        $rootScope.$apply(function () {
          if (callback) {
            callback.apply(socket, args);
          }
        });
      })
    }

  };
});

app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                templateUrl : 'lobby.html',
                controller  : 'MenuController'
            })

            .when('/host', {
                templateUrl : 'host.html',
                controller  : 'MenuController'
            })

            .when('/game', {
                templateUrl : 'game.html',
                controller  : 'SinglePlayerController'
            })

            .when('/game/:gameId', {
                templateUrl : 'game.html',
                controller  : 'MultiPlayerController'
            });


            $locationProvider.html5Mode({
			  enabled: true,
			  requireBase: false
			});
}]);

app.controller('MenuController', ['$scope', '$location', 'socket', function($scope, $location, socket) {

	$scope.games = [];

	$scope.hostFormTog = false;

	socket.on("addGame", function(data){
		var gr = new GameRef(data[0], data[1], data[2], data[3]);
		$scope.games.push(gr);
	});

	socket.on("removeGame", function(id){
		for(var i = 0; i < $scope.games.length; i++){
			if($scope.games[i].id == id){
				$scope.games.splice(i, 1);

				return;
			}

		}
	});

	socket.on("joinSuccess", function(id){

		$location.path("/game/" + id);

	});

	socket.on("hostSuccess", function(id){
		
		$location.path("/game/" + id);

	});

	$scope.cancel = function(){
		socket.emit("hostCancel");
		window.location.href = "/";
	}

	

	$scope.hostGame = function(){
		var gname = $("#gname").val();
		var hname = $("#hname").val();
		var pw = $("#pw").val();

		socket.emit("host", [gname, hname, pw]);

		$location.path("/host");

	};

	$scope.toggleHost = function(){
		$scope.hostFormTog = !$scope.hostFormTog;
	}

	$scope.joinGame = function(game){
		
		var success = -1;
		if(game.password != "")
		{
			var msg = "Please enter the game password.";
			
			while(success == -1){

				var pw = window.prompt(msg,"");		

				if(pw == game.password){
					success = 1;
				}
				else if(pw == null){
					success = 0;
				}
				else{
					msg = "Wrong password, try again.";
				}

			}

		}
		else{
			success = 1;
		}

		if(success == 1){
			//join
			socket.emit("join", game.id);
			
		}
		
	}

}]);

app.controller('SinglePlayerController', ['$scope', '$timeout', '$routeParams', '$location', 'socket', 
	function($scope, $timeout, $routeParams, $location, socket) {
	// $('.carousel').carousel({
	// interval: false
	// });
	
	$scope.game = new Game();
	$scope.game.init();
	$scope.game.message = "Press Ready To Start";

	$scope.goFirst = Math.floor((Math.random() * 2));

	$scope.gameStart = false;

	$scope.menu = function(){
		window.location.href = "/";
	}

	$scope.ping = function(){
		socket.emit("test");
	}

	$scope.aiTurn = function(){
		if(!$scope.gameStart){return;}
		if($scope.game.state != 0){return;}
		if(!$scope.game.opponent.turn){return;}

		var result = $scope.game.opponent.doTurn($scope.game.player.board);

		if(result == -1){
			$scope.game.log = "Opponent > fired at " + yxToCoords([y,x]) + " and missed.\n\n" + $scope.game.log;
		}
		else{
			if(result.parent.isDestroyed()){
				$scope.game.log = "Opponent > fired at " + yxToCoords([y,x]) + " and destroyed your " + markerToShip(result.parent.marker) + ".\n\n" + $scope.game.log;
			}
			else{
				$scope.game.log = "Opponent > fired at " + yxToCoords([y,x]) + " and hit your " + markerToShip(result.parent.marker) + ".\n\n" + $scope.game.log;
			}
		}

		$scope.game.state = $scope.game.isOver();

		$scope.game.opponent.consumeTurn();
	};

	$scope.range = function(min, max, step) {
	    step = step || 1;
	    var input = [];
	    for (var i = min; i <= max; i += step) {
	        input.push(i);
	    }
	    return input;
	};

	$scope.playerAction = function(y, x){
		if(!$scope.gameStart){return;}
		if($scope.game.state != 0){return;}
		if(!$scope.game.player.turn){return;}
		if($scope.game.opponent.board.grid[y][x] != 0){return;}

		var result = $scope.game.opponent.board.fire([y,x]);

		if(result == -1){
			$scope.game.log = "You > fired at " + yxToCoords([y,x]) + " and missed.\n\n" + $scope.game.log;
		}
		else{
			if(result.parent.isDestroyed()){
				$scope.game.log = "You > fired at " + yxToCoords([y,x]) + " and destroyed your opponent's " + markerToShip(result.parent.marker) + ".\n\n" + $scope.game.log;
			}
			else{
				$scope.game.log = "You > fired at " + yxToCoords([y,x]) + " and hit your opponent's " + markerToShip(result.parent.marker) + ".\n\n" + $scope.game.log;
			}
		}
		
		$scope.game.state = $scope.game.isOver();

		$scope.game.player.consumeTurn();
	};

	$scope.$watch('game.player.turn', function(){

		if(!$scope.gameStart){return;}
		if($scope.game.player.turn){
			$scope.game.message = "Your turn";
			//player needs to move
		}
		//now opponents turn
		else{
			//$timeout($scope.changeCarousel, 2000, true, 0);
			
			$scope.game.opponent.giveTurn();
		}

	});

	$scope.$watch('game.opponent.turn', function(){
		if(!$scope.gameStart){return;}

		if($scope.game.opponent.turn){
			$scope.game.message = "Opponent's turn";
			$timeout($scope.aiTurn, 1000);
		
		}
		//now players turn
		else{
			//$timeout($scope.changeCarousel, 2000, true, 1);

			$scope.game.player.giveTurn();
		}

	});

	// $scope.changeCarousel = function(ind){
	// 	$('#carousel').carousel(ind);
	// 	$scope.currentSlide = ind;

	// }

	// $scope.changeSlide = function(){
	// 	if($scope.currentSlide == 0){
	// 		$scope.changeCarousel(1);
	// 	}
	// 	else{
	// 		$scope.changeCarousel(0);
	// 	}
	// }

	$scope.$watch('game.state', function(){
		//game is over do something
		if($scope.game.state == 1){
			$scope.game.message = "You win!";
			$scope.game.log = "You win!\n\n" + $scope.game.log;
		}
		else if($scope.game.state == -1){
			$scope.game.message = "You lose!";
			$scope.game.log = "You lose!\n\n" + $scope.game.log;
		}

	});

	$scope.start = function(){
		$scope.gameStart = true;

		if($scope.goFirst == 0){
			$scope.game.player.giveTurn();
		}
		else{
			$scope.game.opponent.giveTurn();
		}
	}


}]);

app.controller('MultiPlayerController', ['$scope', '$timeout', '$routeParams', '$location', 'socket', 
	function($scope, $timeout, $routeParams, $location, socket) {
	// $('.carousel').carousel({
	// interval: false
	// });

	$scope.gameId = $routeParams.gameId;  


	$scope.game = new Game();
	$scope.game.multiInit();

	$scope.game.message = "Press Ready To Start";
	$scope.gameStart = false;

	$scope.currentSlide = 0;

	socket.emit("gameConnect", $scope.gameId);

	$scope.menu = function(){
		window.location.href = "/";
	}

	$scope.ping = function(){
		socket.emit("test");
	}

	socket.on("recieveShip", function(data){

		var size = data[0];
		var y = data[1];
		var x = data[2];
		var ori = data[3];
		var marker = data[4];

		var ship = new Ship(size, [y,x], ori, marker);

		ship.init();

		$scope.game.opponent.ships.push(ship);
		$scope.game.opponent.placeShipIndex($scope.game.opponent.ships.length-1);
	});

	socket.on("kick", function(){
		window.location.href = "/";
	})

	socket.on("opponentFirst", function(){
		$scope.game.message = "Opponent goes first.";

	});

	socket.on("playerFirst", function(){
		$scope.game.message = "You go first.";
		$timeout($scope.firstTurn, 1000);
	});

	$scope.firstTurn = function(){
		$scope.game.player.giveTurn();
	}

	socket.on("opponentFire", function(data){
		var result = $scope.game.player.board.fire([data[0], data[1]]);

		if(result == -1){
			$scope.game.log = "Opponent > fired at " + yxToCoords([y,x]) + " and missed.\n\n" + $scope.game.log;
		}
		else{
			if(result.parent.isDestroyed()){
				$scope.game.log = "Opponent > fired at " + yxToCoords([y,x]) + " and destroyed your " + markerToShip(result.parent.marker) + ".\n\n" + $scope.game.log;
			}
			else{
				$scope.game.log = "Opponent > fired at " + yxToCoords([y,x]) + " and hit your " + markerToShip(result.parent.marker) + ".\n\n" + $scope.game.log;
			}
		}

		$scope.game.state = $scope.game.isOver();
	});

	socket.on("giveTurn", function(){
		$scope.game.player.giveTurn();
	});


	$scope.range = function(min, max, step) {
	    step = step || 1;
	    var input = [];
	    for (var i = min; i <= max; i += step) {
	        input.push(i);
	    }
	    return input;
	};

	$scope.playerAction = function(y, x){
		if(!$scope.gameStart){return;}
		if($scope.game.state != 0){return;}
		if(!$scope.game.player.turn){return;}
		if($scope.game.opponent.board.grid[y][x] != 0){return;}

		var result = $scope.game.opponent.board.fire([y,x]);

		if(result == -1){
			$scope.game.log = "You > fired at " + yxToCoords([y,x]) + " and missed.\n\n" + $scope.game.log;
		}
		else{
			if(result.parent.isDestroyed()){
				$scope.game.log = "You > fired at " + yxToCoords([y,x]) + " and destroyed your opponent's " + markerToShip(result.parent.marker) + ".\n\n" + $scope.game.log;
			}
			else{
				$scope.game.log = "You > fired at " + yxToCoords([y,x]) + " and hit your opponent's " + markerToShip(result.parent.marker) + ".\n\n" + $scope.game.log;
			}
		}

		socket.emit("fire", [$scope.gameId, y,x]);
		
		$scope.game.state = $scope.game.isOver();

		$scope.game.player.consumeTurn();
	};

	$scope.$watch('game.player.turn', function(){
		if(!$scope.gameStart){return;}
		
		if($scope.game.player.turn){
			//$timeout($scope.changeCarousel, 2000, true, 1);
			$scope.game.message = "Your turn";
			//player needs to move
		}
		//now opponents turn
		else{
			$scope.game.message = "Opponent's turn";
			//$timeout($scope.changeCarousel, 2000, true, 0);
		}

	});

	// $scope.changeCarousel = function(ind){
	// 	$('#carousel').carousel(ind);
	// 	$scope.currentSlide = ind;

	// }

	// $scope.changeSlide = function(){
	// 	if($scope.currentSlide == 0){
	// 		$scope.changeCarousel(1);
	// 	}
	// 	else{
	// 		$scope.changeCarousel(0);
	// 	}
	// }

	$scope.$watch('game.state', function(){
		//game is over do something
		if($scope.game.state == 1){
			$scope.game.message = "You win!";
			$scope.game.log = "You win!\n\n" + $scope.game.log;
		}
		else if($scope.game.state == -1){
			$scope.game.message = "You lose!";
			$scope.game.log = "You lose!\n\n" + $scope.game.log;
		}

	});

	$scope.start = function(){
		for(var i = 0; i < $scope.game.player.ships.length; i++){
			var ship = $scope.game.player.ships[i];

			socket.emit("sendShip", [$scope.gameId, ship.size, ship.start[0], ship.start[1], ship.orientation, ship.marker]);

		}
		socket.emit("gameReady", $scope.gameId);
		$scope.game.message = "Waiting on Opponent";
		$scope.gameStart = true;
	}




}]);