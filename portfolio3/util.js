function parseCoords(coords){

	xVal = coords.charAt(0);
	yVal = coords.charAt(1);
	x = -1;
	y = -1;
	switch(xVal){
		case "A":
			x = 0;
			break;

		case "B":
			x = 1;
			break;

		case "C":
			x = 2;
			break;

		case "D":
			x = 3;
			break;

		case "E":
			x = 4;
			break;

		case "F":
			x = 5;
			break;

		case "G":
			x = 6;
			break;

		case "H":
			x = 7;
			break;
	}

	y = parseInt(yVal) - 1;


	return [x,y];
}

function markerToImage(marker, index, rotate){
	
	if(rotate){
		return "<img class='rotate ship-img img-responsive' src='resources/fragments/" + marker + index + ".png'>";
	}else{
		return "<img class='ship-img img-responsive' src='resources/fragments/" + marker + index + ".png'>";
	}
	
}

function yxToCoords(coords){
	var trans = "";
	var x = coords[1];
	var y = coords[0];

	switch(x){
		case 0:
			trans += "A";
			break;

		case 1:
			trans += "B";
			break;

		case 2:
			trans += "C";
			break;

		case 3:
			trans += "D";
			break;

		case 4:
			trans += "E";
			break;

		case 5:
			trans += "F";
			break;

		case 6:
			trans += "G";
			break;

		case 7:
			trans += "H";
			break;
	}

	switch(y){
		case 0:
			trans += "1";
			break;

		case 1:
			trans += "2";
			break;

		case 2:
			trans += "3";
			break;

		case 3:
			trans += "4";
			break;

		case 4:
			trans += "5";
			break;

		case 5:
			trans += "6";
			break;

		case 6:
			trans += "7";
			break;

		case 7:
			trans += "8";
			break;
	}

	return trans;


}

function markerToShip(marker){
	switch(marker){
		case "p":
			return "Patrol Boat";
			break;

		case "s":
			return "Submarine";
			break;

		case "d":
			return "Destroyer";
			break;

		case "b":
			return "Battleship";
			break;

		case "c":
			return "Carrier";
			break;

	}
}
