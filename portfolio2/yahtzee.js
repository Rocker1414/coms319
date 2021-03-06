//this is the angular js code
var app = angular.module('yahtzee', []);

app.controller('GameController', ['$scope', '$http', function($scope, $http) {

	$scope.die = [new Dice("dice1"), new Dice("dice2"), 
	new Dice("dice3"), new Dice("dice4"), new Dice("dice5")];
	
	$scope.card = new Scorecard();

	$scope.gameOver = false;

	$scope.rollsLeft = 3;

	$scope.$watch('gameOver', function(){

		if($scope.gameOver){
			$scope.checkScore();
		}

	});

	$scope.checkScore = function(){
		var data =  {
                         "id": "check",
                         "name": "Player",
                         "score": $scope.card.grandTotal()
                     };
		$http.post('/server.php', data).success(
			function (data, status, headers, config) {
				if(data == 1){
               	 	$scope.writeScore();
          		}
            });
	};

	$scope.writeScore = function(){
		var name = prompt("You got a High Score! Please enter your name:", "Player")
		var data =  {
                         "id": "save",
                         "name": name,
                         "score": $scope.card.grandTotal()

                     };

		$http.post('/server.php', data).success(
			function (data, status, headers, config) {
               //redirect to high score table
               window.location.href = "highscores.php";
            });
	}

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
	};

	$scope.fullHouse = function(){

		//joker
		if($scope.yahtzee() == 50 && $scope.card.yahtzeeScored() && $scope.card.isUpperScored($scope.die[0].val)){
			
			return 25;

		}

		var nums = $scope.getNums();
		var qual = fullHouse(nums);
		if(qual){
			return 25;
		}
		else{
			return 0;
		}
	};

	$scope.smallStraight = function(){

		//joker
		if($scope.yahtzee() == 50 && $scope.card.yahtzeeScored() && $scope.card.isUpperScored($scope.die[0].val)){
			
			return 30;

		}

		var nums = $scope.getNums();
		var qual = smallStraight(nums);
		if(qual){
			return 30;
		}
		else{
			return 0;
		}
	};

	$scope.largeStraight = function(){

		//joker
		if($scope.yahtzee() == 50 && $scope.card.yahtzeeScored() && $scope.card.isUpperScored($scope.die[0].val)){
			
			return 40;

		}

		var nums = $scope.getNums();
		var qual = largeStraight(nums);
		if(qual){
			return 40;
		}
		else{
			return 0;
		}
	};

	$scope.yahtzee = function(){

		var nums = $scope.getNums();
		var qual = yahtzee(nums);

		if(qual){
			return 50;
		}
		else{

			return 0;
		}
	};

	$scope.chance = function(){
		var nums = $scope.getNums();
		var score = diceTotal(nums);
		return score;
	};

	$scope.matchNumberScore = function(target){
		var nums = $scope.getNums();
		var score = calculateNumCount(nums, target);
		return score;
	};

	$scope.toggle = function(i){

		if($scope.die[i].val != 0){
			$scope.die[i].toggle();
		}
		
	};

	// Reset non-locked dice after a roll
	$scope.clearAll = function(){
		for(var i = 0; i < $scope.die.length; i++){
			var dice = $scope.die[i];
			
			if(!dice.locked){
				dice.clear();
			}
		}
	};
	
	// Reset all dice after a successful score
	$scope.resetAll = function(){
		for(var i = 0; i < $scope.die.length; i++){
			var dice = $scope.die[i];
			dice.val = 0;
			dice.unlock();
			
			dice.clear();
		}
		
		$scope.rollsLeft = 3;
	};

	$scope.roll = function(){
		if($scope.rollsLeft > 0){
			//first clear
			$scope.clearAll();
			for(var i = 0; i < $scope.die.length; i++){
				var dice = $scope.die[i];
				if(!dice.locked){
					dice.roll();
				}
			}
			$scope.rollsLeft--;
			error("");
		}
		else{
			msg = "Warning: Your turn is up. Please make a scoring selection.";
			error(msg);
		}
	};

	//force a yahtzee for testing purposes
	$scope.forceYahtzee = function(){
		$scope.resetAll();
		$scope.die[0].roll();
		var val = $scope.die[0].val;
		$scope.die[1].force(val);
		$scope.die[2].force(val);
		$scope.die[3].force(val);
		$scope.die[4].force(val);
	}
	
	$scope.keep = function(id, val){

		//if havent rolled, return
		if($scope.rollsLeft == 3){
			return;
		}

		//when keeping, check 
		if($scope.yahtzee() == 50 && $scope.card.yahtzeeScored()){
			$scope.card.yahtzeeBonus += 100;
		}

		$scope.card.keep(id, val);
		$scope.resetAll();

		//check if game is over
		if($scope.card.isFilled()){
			$scope.gameOver = true;
		}
	};
	
	
}]);






