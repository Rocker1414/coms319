function Calculator()
{
	this.inputVal = 0;
	this.inputArr = [];
	
	this.inputValue = function(input)
	{
		this.inputArr.push(input);
		this.displayInput();
	}
	
	this.compute = function()
	{
		var x = document.getElementById("output-text");
		x.value = this.inputVal;
		this.inputVal = 0;
	}
	
	this.clearOutput = function()
	{
		var x = document.getElementById("output-text");
		x.value = "";	
	}
	
	this.clearInput = function()
	{
		this.inputVal = 0;
		this.inputArr = [];
		this.displayInput();
	}
	
	this.displayInput() = function()
	{
		var ostring = "";
		for(var i = 0; i < inputArr.length; i++)
		{
			ostring += inputArr[i].toString();
		}
		
		var x = document.getElementById("input-text");
		x.value = ostring;	
		
	}
}