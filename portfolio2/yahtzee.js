//this is the angular js code
var app = angular.module('yahtzee', []);

app.controller('GameController', ['$scope', function($scope) {

	$scope.die = [new Dice("dice1"), new Dice("dice2"), 
	new Dice("dice3"), new Dice("dice4"), new Dice("dice5")];

	$scope.toggle = function(i){
		$scope.die[i].toggle();
	}

	$scope.clearAll = function(){
		for($i = 0; $i < $scope.die.length; $i++){
			var dice = $scope.die[$i];
			
			if(!dice.locked){
				dice.clear();
			}
		}
	}

	$scope.roll = function(){
		//first clear
		$scope.clearAll();
		for($i = 0; $i < $scope.die.length; $i++){
			var dice = $scope.die[$i];
			if(!dice.locked){
				dice.roll();
			}
			

		}	
		
	}

}]);






