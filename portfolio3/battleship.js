var app = angular.module('battleship', ['ngRoute','ngSanitize']);

app.filter('html', ['$sce', function ($sce) { 
    return function (code) {
        return $sce.trustAsHtml(code);
    };    
}]);

app.factory('socket', function ($rootScope) {
  var socket = io.connect('http://localhost');
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

	

	$scope.hostGame = function(){
		var gname = $("#gname").val();
		var hname = $("#hname").val();
		var pw = $("#pw").val();

		socket.emit("host", [gname, hname, pw]);

		$location.path("/host");

	};

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
	$('.carousel').carousel({
	interval: false
	});

	$scope.game = new Game();
	$scope.game.init();

	$scope.currentSlide = 0;

	$scope.game.player.giveTurn();

	$scope.menu = function(){
		window.location.href = "/";
	}

	$scope.ping = function(){
		socket.emit("test");
	}

	$scope.aiTurn = function(){
		if($scope.game.state != 0){return;}

		if(!$scope.game.opponent.turn){return;}

		$scope.game.opponent.doTurn($scope.game.player.board);

		$scope.gameOver = $scope.game.isOver();

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

		if($scope.game.state != 0){return;}
		if(!$scope.game.player.turn){return;}

		$scope.game.opponent.board.fire([y,x]);
		
		$scope.gameOver = $scope.game.isOver();

		$scope.game.player.consumeTurn();
	};

	$scope.$watch('game.player.turn', function(){

		
		if($scope.game.player.turn){
			$scope.game.message = "Your turn";
			//player needs to move
		}
		//now opponents turn
		else{
			$timeout($scope.changeCarousel, 2000, true, 0);
			
			$scope.game.opponent.giveTurn();
		}

	});

	$scope.$watch('game.opponent.turn', function(){

		if($scope.game.opponent.turn){
			$scope.game.message = "Opponent's turn";
			$timeout($scope.aiTurn, 4000);
		
		}
		//now players turn
		else{
			$timeout($scope.changeCarousel, 2000, true, 1);

			$scope.game.player.giveTurn();
		}

	});

	$scope.changeCarousel = function(ind){
		$('#carousel').carousel(ind);
		$scope.currentSlide = ind;

	}

	$scope.changeSlide = function(){
		if($scope.currentSlide == 0){
			$scope.changeCarousel(1);
		}
		else{
			$scope.changeCarousel(0);
		}
	}

	$scope.$watch('game.state', function(){
		//game is over do something

	});


}]);

app.controller('MultiPlayerController', ['$scope', '$timeout', '$routeParams', '$location', 'socket', 
	function($scope, $timeout, $routeParams, $location, socket) {
	$('.carousel').carousel({
	interval: false
	});

	$scope.gameId = $routeParams.gameId;  

	$scope.game = new Game();
	$scope.game.multiInit();

	$scope.currentSlide = 0;

	socket.emit("gameConnect", $scope.gameId);

	$scope.menu = function(){
		window.location.href = "/";
	}

	$scope.ping = function(){
		socket.emit("test");
	}

	socket.on("kick", function(){
		window.location.href = "/";
	})

	socket.on("opponentFire", function(data){
		$scope.game.player.board.fire([data[0], data[1]]);
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

		if($scope.game.state != 0){return;}
		if(!$scope.game.player.turn){return;}

		$scope.game.opponent.board.fire([y,x]);
		socket.emit("fire", [$scope.gameId, y,x]);
		
		$scope.gameOver = $scope.game.isOver();

		$scope.game.player.consumeTurn();
	};

	$scope.$watch('game.player.turn', function(){

		
		if($scope.game.player.turn){
			$timeout($scope.changeCarousel, 2000, true, 1);
			$scope.game.message = "Your turn";
			//player needs to move
		}
		//now opponents turn
		else{
			$scope.game.message = "Opponent's turn";
			$timeout($scope.changeCarousel, 2000, true, 0);
		}

	});

	$scope.changeCarousel = function(ind){
		$('#carousel').carousel(ind);
		$scope.currentSlide = ind;

	}

	$scope.changeSlide = function(){
		if($scope.currentSlide == 0){
			$scope.changeCarousel(1);
		}
		else{
			$scope.changeCarousel(0);
		}
	}

	$scope.$watch('game.state', function(){
		//game is over do something

	});

	socket.emit("gameReady", $scope.gameId);


}]);