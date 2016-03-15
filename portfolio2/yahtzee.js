//this is the angular js code
var app = angular.module('yahtzee', []);

app.controller('GameController', ['$scope', function($scope) {

	$scope.die = [new Dice("dice1"), new Dice("dice2"), 
	new Dice("dice3"), new Dice("dice4"), new Dice("dice5")];

	$scope.getNums = function(){
		var nums = [];
		for(var i = 0; i < $scope.die.length; i++){
			nums.push($scope.die[i].val);
		}

		return nums;
	};

	$scope.ofAKind = function(target){
		var nums = $scope.getNums();
		var qual = ofAKind(nums, target);
		if(qual){
			return diceTotal(nums);
		}
		else{
			return 0;
		}
	}

	$scope.fullHouse = function(){
		var nums = $scope.getNums();
		var qual = fullHouse(nums);
		if(qual){
			return 25;
		}
		else{
			return 0;
		}
	}

	$scope.smallStraight = function(){
		var nums = $scope.getNums();
		var qual = smallStraight(nums);
		if(qual){
			return 30;
		}
		else{
			return 0;
		}
	}

	$scope.largeStraight = function(){
		var nums = $scope.getNums();
		var qual = largeStraight(nums);
		if(qual){
			return 40;
		}
		else{
			return 0;
		}
	}

	$scope.yahtzee = function(){
		var nums = $scope.getNums();
		var qual = yahtzee(nums);
		if(qual){
			return 50;
		}
		else{
			return 0;
		}
	}

	$scope.chance = function(){
		var nums = $scope.getNums();
		var score = diceTotal(nums);
		return score;
	}

	$scope.matchNumberScore = function(target){
		var nums = $scope.getNums();
		var score = calculateNumCount(nums, target);
		return score;
	};

	$scope.toggle = function(i){
		$scope.die[i].toggle();
	};

	$scope.clearAll = function(){
		for(var i = 0; i < $scope.die.length; i++){
			var dice = $scope.die[i];
			
			if(!dice.locked){
				dice.clear();
			}
		}
	};

	$scope.roll = function(){
		//first clear
		$scope.clearAll();
		for(var i = 0; i < $scope.die.length; i++){
			var dice = $scope.die[i];
			if(!dice.locked){
				dice.roll();
			}
			

		}	
		
	};

}]);






