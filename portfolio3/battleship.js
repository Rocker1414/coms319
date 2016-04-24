var app = angular.module('battleship', ['ngSanitize']);

app.filter('html', ['$sce', function ($sce) { 
    return function (code) {
        return $sce.trustAsHtml(code);
    };    
}]);

app.controller('MenuController', ['$scope', function($scope) {
	$scope.games = [];

	$scope.games.push(new GameRef("test", "p1", "password"));
	$scope.games.push(new GameRef("test2", "p2"));

	$scope.hostGame = function(){
		var gname = $("#gname").val();
		var hname = $("#hname").val();
		var pw = null;

		if($("#pw").val() != ""){
			$("#pw").val() 
		}	

		$scope.games.push(new GameRef(gname, hname, pw));
	}
}]);

app.controller('GameController', ['$scope', '$timeout', function($scope, $timeout) {
	
	$scope.game = new Game();
	$scope.game.init();

	$scope.game.player.giveTurn();

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
	}

	$scope.$watch('game.state', function(){
		//game is over do something

	});


}]);