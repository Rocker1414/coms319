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