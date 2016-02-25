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
		buttonMC : {id: "buttonMC", type: "button", value: "MC", onclick:""},
		buttonMR : {id: "buttonMR", type: "button", value: "MR", onclick:""},
		buttonMMinus : {id: "buttonMMinus", type: "button", value: "M-", onclick:""},
		buttonMPlus : {id: "buttonMPlus", type: "button", value: "M+", onclick:""}
	},
	
	display : function() {
		var s;
		s = "<table id=\"myTable\" border=2>"
		s += "<tr><td><input id= \"textRow\" type=\"text\" value=\"0\" onclick=\"\" readonly=\"readonly\"></td></tr>";
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
		s += Calc.displayElement(Calc.View.buttonMC);
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

	updateField : function(t){
		document.getElementById("textRow").value = "";
		document.getElementById("textRow").value = t;
	},
	
	getField : function(){
		return document.getElementById("textRow").value;
	},
//--------------------------------------------------------------------------------Calculation-------------------------------------------------------------------------------
		
	Model : {
		mem: 0,
		lastOperand: "0",
		op: "",
	},
	
	digitInput : function(digit){	
		var currentField = Calc.getField();
		
		if(currentField == "0"){
			currentField = digit;
		}
		else{
			currentField += digit;
		}
		Calc.Model.lastOperand += digit;
		Calc.updateField(currentField);
	},
	
	dotInput : function(){
		var currentField = Calc.getField();

		if(Calc.Model.lastOperand == ""){
			currentField += "0.";
		}
		else if(Calc.Model.lastOperand.indexOf(".") == -1){
			currentField += ".";
		}
		
		Calc.updateField(currentField);
	},
	
	operation : function(op){
		var currentField = Calc.getField();
		
		// Verify the last inputted digit is a number
		var lastDigit = currentField.substr(currentField.length - 1);
		switch(lastDigit){
			case "+":
				break;
			case "-":
				break;
			case "*":
				break;
			case "/":
				break;
			case " ":
				break;
			default:
				Calc.Model.op = op;
				currentField += " " + op + " ";
				Calc.Model.lastOperand = "";
				Calc.updateField(currentField);
				break;
		}
	},
	
	clear : function(){
		Calc.Model.lastOperand = "0";
		Calc.Model.clearNext = false;
		Calc.Model.op = "";
		Calc.updateField("0");
	},
	
	memView : function(){
		Calc.updateField(Calc.Model.mem);
	},
	
	memClear: function(){
		Calc.Model.mem = 0;
	},
	
	memMPlus : function(){
		var newVal = Calc.Model.mem + eval(Calc.getField());
		Calc.Model.mem = newVal;
	},
	
	memMMinus : function(){
		var newVal = +alc.Model.mem - eval(Calc.getField());
		Calc.Model.mem = newVal;
	},
	
	solve : function(equalsPressed){
		
		var currentField = Calc.getField();
		
		// Operators present
		if(currentField.indexOf("+") > -1 || currentField.indexOf("-") > -1 || currentField.indexOf("*") > -1 || currentField.indexOf("/") > -1){

			// Check for lone negative number : repeat last op
			if(currentField.charAt(0) == "-"){
				var ans = eval(currentField + Calc.Model.op + Calc.Model.lastOperand);
			}
			// Evaluate
			else{
				var ans = eval(currentField);
			}
		}
		// No operators present
		else{
			// Repeat last op
			var ans = eval(currentField + Calc.Model.op + Calc.Model.lastOperand);
		}
		Calc.updateField(ans);
	},
	
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
		Calc.View.buttonDot.onclick = "Calc.buttonDotHandler()";
		Calc.View.buttonEquals.onclick = "Calc.buttonEqualsHandler()";
		Calc.View.buttonC.onclick = "Calc.buttonCHandler()";
		Calc.View.buttonMC.onclick = "Calc.buttonMCHandler()";
		Calc.View.buttonMR.onclick = "Calc.buttonMRHandler()";
		Calc.View.buttonMPlus.onclick = "Calc.buttonMPlusHandler()";
		Calc.View.buttonMMinus.onclick = "Calc.buttonMMinusHandler()";
		Calc.View.buttonAdd.onclick = "Calc.buttonAddHandler()"; 
		Calc.View.buttonSubtract.onclick = "Calc.buttonSubtractHandler()"; 
		Calc.View.buttonMultiply.onclick = "Calc.buttonMultiplyHandler()"; 
		Calc.View.buttonDivide.onclick = "Calc.buttonDivideHandler()"; 
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
	},
	
	buttonEqualsHandler : function(){
		Calc.solve(true);
	},
	
	buttonCHandler : function() {
		Calc.clear();
	},
	
	buttonMRHandler : function() {
		Calc.memView();
	},
	
	buttonMCHandler : function() {
		Calc.memClear();
	},
	
	buttonMPlusHandler : function() {
		Calc.memMPlus();
	},
	
	buttonMMinusHandler : function(){
		Calc.memMMinus();
	},
	
	buttonAddHandler : function() {
		Calc.operation("+");
	},
	
	buttonSubtractHandler : function() {
		Calc.operation("-");
	},
	
	buttonMultiplyHandler : function() {
		Calc.operation("*");
	},
	
	buttonDivideHandler : function() {
		Calc.operation("/");
	}
} 