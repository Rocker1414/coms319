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
	}

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
	}

	$scope.toggle = function(i){
		$scope.die[i].toggle();
	}

	// Reset non-locked dice after a roll
	$scope.clearAll = function(){
		for(var i = 0; i < $scope.die.length; i++){
			var dice = $scope.die[i];
			
			if(!dice.locked){
				dice.clear();
			}
		}
	}
	
	// Reset all dice after a successful score
	$scope.resetAll = function(){
		for(var i = 0; i < $scope.die.length; i++){
			var dice = $scope.die[i];
			
			if(dice.locked){
				dice.toggle();
			}
			
			dice.clear();
			resetRollScores();
		}
	}

	$scope.roll = function(){
		if(isTurn()){
			//first clear
			$scope.clearAll();
			for(var i = 0; i < $scope.die.length; i++){
				var dice = $scope.die[i];
				if(!dice.locked){
					dice.roll();
				}
			}
			updateRollsLeft();
			error("");
		}
		else{
			msg = "Warning: Your turn is up. Please make a scoring selection.";
			error(msg);
		}
	}

	$scope.score = function(id){
		if(setScore(id)){
			$scope.resetAll();
		};
	}
	
	$scope.yahtzeeScore = function(){
		if(setYahtzeeScore()){
			$scope.resetAll();
		};
	}
	
	$scope.upperSectionTotal = function(){
		return sumUpperSection();
	}
	
	$scope.lowerSectionTotal = function(){
		return sumLowerSection();
	}
	
	$scope.bonus = function(){
		var upper = this.upperSectionTotal();
		
		if(upper >= 63)
			return 35;
		else
			return 0;
	}
	
	$scope.upperSectionGrandTotal = function(){
		return this.upperSectionTotal() + this.bonus();
	}
	
	$scope.grandTotal = function(){
		return this.upperSectionGrandTotal() + this.lowerSectionTotal();
	}
	
	$scope.showRollsLeft = function(){
		return getRollsLeft();
	}	
	
}]);






