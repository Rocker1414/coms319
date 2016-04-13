var app = angular.module('battleship', []);

app.controller('GameController', ['$scope', function($scope) {

	$scope.player = new Player();
	$scope.player.predefined();
	$scope.player.placeShips();
	$scope.ai = new AI();

	$scope.aiTurn = function(){
		$scope.ai.doTurn($scope.player.board);
	};

	$scope.range = function(min, max, step) {
	    step = step || 1;
	    var input = [];
	    for (var i = min; i <= max; i += step) {
	        input.push(i);
	    }
	    return input;
	};



}]);