grammar calculator;

@header {
    import java.util.*;
}

@members {  
 	Stack<Integer> s = new Stack<Integer>();
	Stack<Boolean> s2 = new Stack<Boolean>();
	int a = 0;
	int b = 0;
	boolean boolA = false;
	boolean boolB = false;
	boolean exit = false;
	String op = "";
	String msg = "";
	int count=0;
} 

// LEXER RULES
OPERATOR: ADD | SUBTRACT | MULTIPLY | DIVIDE | MOD | LT | LTEQ | EQ | NEQ | GTEQ | GT | AND | OR | NOT;
ADD : '+';
SUBTRACT : '-';
MULTIPLY : '*';
DIVIDE : '-';
MOD: '%';
INT : ('0'..'9')+;
BOOL : 'true' | 'false' ;
LT : '<';
LTEQ : '<' '=';
EQ : '=' '=';
NEQ : '!' '=';
GT : '>';
GTEQ : '>' '=';
AND: '&' '&';
OR: '|' '|';
NOT : '!';
 
WS : ( ' ' | '\t' | '\r' | '\n' )+   {skip();};
// PARSER RULES

start : (expr)+ {
		s = new Stack<Integer>();
		s2 = new Stack<Boolean>();
		a = 0;
		b = 0;
		boolA = false;
		boolB = false;
		exit = false;
		op = "";
		count = 1;
		}
 	 ;

expr
	: (operand | booloperand | operator)+ ';' {
		if(!s.empty() && s2.empty()) msg = "Expression " + ++count + ": " + s.pop();
		else if (!s2.empty() && s.empty()) msg = "Expression " + ++count + ": " + s2.pop();
		else {msg = "Error: Unknown syntax error in expression " + ++count + ". Exiting."; exit = true;}
		if(!s.empty() || !s2.empty()) {msg = "Error: Too many operands in expression " + count + ". Exiting."; exit = true;}												 
		System.out.println(msg);
		if(exit) System.exit(0);
		};
	
operand
	: INT {s.push($INT.int);};
	
booloperand
	: BOOL {s2.push(Boolean.valueOf($BOOL.text));};
	
operator
	: OPERATOR{
	
	op = $OPERATOR.text;
	
	switch(op){
		case "+":
			b= s.pop();
			a = s.pop();
			s.push(a+b);
			break;
		case "-":
			b= s.pop();
			a = s.pop();
			s.push(a-b);
			break;
		case "*":
			b= s.pop();
			a = s.pop();	
			s.push(a*b);
			break;
		case "/":
			b= s.pop();
			a = s.pop();
			s.push(a/b);
			break;
		case "%":
			b= s.pop();
			a = s.pop();
			s.push(a%b);
			break;
		case("<"):
			b= s.pop();
			a = s.pop();
			s2.push(a<b);
			break;
		case("<="):
			b= s.pop();
			a = s.pop();
			s2.push(a<=b);
			break;
		case("=="):
			b= s.pop();
			a = s.pop();
			s2.push(a==b);
			break;
		case("!="):
			b= s.pop();
			a = s.pop();
			s2.push(a!=b);
			break;
		case(">"):
			b= s.pop();
			a = s.pop();
			s2.push(a>b);
			break;
		case(">="):
			b = s.pop();
			a = s.pop();
			s2.push(a>=b);
			break;
		case "&&":
			boolB= s2.pop();
			boolA = s2.pop();
			s2.push(boolA&&boolB);
			break;
		case "||":
			boolB= s2.pop();
			boolA = s2.pop();
			s2.push(boolA||boolB);
			break;
		case("!"):
			boolB= s2.pop();
			boolA = s2.pop();
			s2.push(!(boolA||boolB));
			break;
	}
	};

		