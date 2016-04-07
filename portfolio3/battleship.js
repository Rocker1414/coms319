var app = angular.module('battleship', []);

app.controller('GameController', ['$scope', function($scope) {

	$scope.board = new Board(8,8);
	$scope.board.init();
}]);