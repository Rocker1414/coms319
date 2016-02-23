// calculator.js
// Ian Baer
// Chris Rogers
// ComS 319 - Group 14
 
var Calc = {


//-------------------------------------------------------------------------------------Run----------------------------------------------------------------------------------
																																													
	run : function() { 
		Calc.attachHandlers();
		//console.log(Calc.display());
		return Calc.display();
	},

//------------------------------------------------------------------------------------Display-------------------------------------------------------------------------------

	View : {
		textRow : {id: "textRow", type: "text", value: "", onclick:""},
		button7 : {id: "button7", type: "button", value: 7, onclick:""},
		button8 : {id: "button8", type: "button", value: 8, onclick:""},
		button9 : {id: "button9", type: "button", value: 9, onclick:""},
		buttonAdd : {id: "buttonAdd", type: "button", value: "+", onclick:""},
		
		button4 : {id: "button4", type: "button", value: 4, onclick:""},
		button5 : {id: "button5", type: "button", value: 5, onclick:""},
		button6 : {id: "button6", type: "button", value: 6, onclick:""},
		buttonSubtract : {id: "buttonSubtract", type: "button", value: "-", onclick:""},
		
		button1 : {id: "button1", type: "button", value: 1, onclick:""},
		button2 : {id: "button2", type: "button", value: 2, onclick:""},
		button3 : {id: "button3", type: "button", value: 3, onclick:""},
		buttonMultiply : {id: "buttonMultiply", type: "button", value: "*", onclick:""},
		
		button0 : {id: "button0", type: "button", value: 0, onclick:""},
		buttonDot : {id: "buttonDot", type: "button", value: ".", onclick:""},
		buttonEquals : {id: "buttonEquals", type: "button", value: "=", onclick:""},
		buttonDivide : {id: "buttonDivide", type: "button", value: "/", onclick:""},
		
		buttonC : {id: "buttonC", type: "button", value: "C", onclick:""},
		buttonMR : {id: "buttonMR", type: "button", value: "MR", onclick:""},
		buttonMMinus : {id: "buttonMMinus", type: "button", value: "M-", onclick:""},
		buttonMPlus : {id: "buttonMPlus", type: "button", value: "M+", onclick:""}
	},
	
	display : function() {
		var s;
		s = "<table id=\"myTable\" border=2>"
		s += "<tr><td>" + Calc.displayElement(Calc.View.textRow) + "</td></tr>";
		s += "<tr><td>";
		s += Calc.displayElement(Calc.View.button7);
		s += Calc.displayElement(Calc.View.button8);
		s += Calc.displayElement(Calc.View.button9);
		s += Calc.displayElement(Calc.View.buttonAdd);
		s += "<tr><td>";
		s += Calc.displayElement(Calc.View.button4);
		s += Calc.displayElement(Calc.View.button5);
		s += Calc.displayElement(Calc.View.button6);
		s += Calc.displayElement(Calc.View.buttonSubtract);
		s += "<tr><td>";
		s += Calc.displayElement(Calc.View.button1);
		s += Calc.displayElement(Calc.View.button2);
		s += Calc.displayElement(Calc.View.button3);
		s += Calc.displayElement(Calc.View.buttonMultiply);
		s += "<tr><td>";
		s += Calc.displayElement(Calc.View.button0);
		s += Calc.displayElement(Calc.View.buttonDot);
		s += Calc.displayElement(Calc.View.buttonEquals);
		s += Calc.displayElement(Calc.View.buttonDivide);
		s += "<tr><td>";
		s += Calc.displayElement(Calc.View.buttonC);
		s += Calc.displayElement(Calc.View.buttonMR);
		s += Calc.displayElement(Calc.View.buttonMMinus);
		s += Calc.displayElement(Calc.View.buttonMPlus);
		s += "</tr></td></table>";
		return s;
	},
	
	displayElement : function (element) {
		var s = "<input ";
		s += " id=\"" + element.id + "\"";
		s += " type=\"" + element.type + "\"";
		s += " value= \"" + element.value + "\"";
		s += " onclick= \"" + element.onclick + "\"";
		s += ">";
		return s;
	},


//--------------------------------------------------------------------------------Calculation-------------------------------------------------------------------------------
		
	Model : {
		current: 0,
		mem: 0,
		op: 0
	},
	
	digitInput : function(digit){	
		var cur = Calc.Model.current.toString();
		
		if(cur.indexOf(".") == -1){
			Calc.Model.current = digit;
			console.log("no . found");
		}
		else
			Calc.Model.current += digit;	
			document.getElementById("textRow").value += Calc.Model.current;
	},
	
	dotInput : function(){
		Calc.Model.current += ".";
	}
	
//------------------------------------------------------------------------------Button Handling-----------------------------------------------------------------------------

	attachHandlers : function() {
		Calc.View.button0.onclick = "Calc.button0Handler()"; 
		Calc.View.button1.onclick = "Calc.button1Handler()"; 
		Calc.View.button2.onclick = "Calc.button2Handler()"; 
		Calc.View.button3.onclick = "Calc.button3Handler()"; 
		Calc.View.button4.onclick = "Calc.button4Handler()"; 
		Calc.View.button5.onclick = "Calc.button5Handler()"; 
		Calc.View.button6.onclick = "Calc.button6Handler()"; 
		Calc.View.button7.onclick = "Calc.button7Handler()"; 
		Calc.View.button8.onclick = "Calc.button8Handler()"; 
		Calc.View.button9.onclick = "Calc.button9Handler()"; 
		//Calc.View.buttonDot.onclick = "Calc.buttonDotHandler()";
		/*Calc.View.buttonAdd.onclick = "Calc.buttonAddHandler()"; 
		Calc.View.buttonSubtract.onclick = "Calc.buttonSubtractHandler()"; 
		Calc.View.buttonMultiply.onclick = "Calc.buttonMultiplyHandler()"; 
		Calc.View.buttonDivide.onclick = "Calc.buttonDivideHandler()"; 
		Calc.View.buttonC.onclick = "Calc.buttonCHandler()"; */
	},
	
	button0Handler : function() {
		Calc.digitInput(0);
	},
	
	button1Handler : function() {
		Calc.digitInput(1);
	},
	
	button2Handler : function() {
		Calc.digitInput(2);
	},
	
	button3Handler : function() {
		Calc.digitInput(3);
	},
	
	button4Handler : function() {
		Calc.digitInput(4);
	},
	
	button5Handler : function() {
		Calc.digitInput(5);
	},
	
	button6Handler : function() {
		Calc.digitInput(6);
	},
	
	button7Handler : function() {
		Calc.digitInput(7);
	},
	
	button8Handler : function() {
		Calc.digitInput(8);
	},
	
	button9Handler : function() {
		Calc.digitInput(9);
	},
	
	buttonDotHandler : function() {
		Calc.dotInput();
	}
	
	/*
	buttonAddHandler : function() {
		var text = document.getElementById("textRow").value+="+";
	},
	
	buttonSubtractHandler : function() {
		var text = document.getElementById("textRow").value+="-";
	},
	
	buttonMultiplyHandler : function() {
		var text = document.getElementById("textRow").value+="*";
	},
	
	buttonDivideHandler : function() {
		var text = document.getElementById("textRow").value+="/";
	},
	
	buttonCHandler : function() {
		var text = document.getElementById("textRow").value="";
	}
	*/
} 
