var app = angular.module('battleship', ['ngSanitize']);

app.filter('html', ['$sce', function ($sce) { 
    return function (code) {
        return $sce.trustAsHtml(code);
    };    
}]);

app.controller('GameController', ['$scope', function($scope) {
	$scope.message = "Test";
	$scope.player = new Player();
	$scope.player.randomShips();
	$scope.opponent = new AI();
	$scope.opponent.randomShips();

	$scope.aiTurn = function(){
		$scope.opponent.doTurn($scope.player.board);
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
		$scope.opponent.board.fire([y,x]);
		console.log($scope.opponent.board.grid[y][x]);
	};

	$scope.test = function(){
		console.log("test");
	};



}]);