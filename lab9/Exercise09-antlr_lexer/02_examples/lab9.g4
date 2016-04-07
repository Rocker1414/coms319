// Ian Baer - Chris Rogers
// Lab 9

lexer grammar lab9;

fragment LETTER : [a-zA-Z];
fragment DIGIT : ('0' .. '9');

// (3.1) [Need to check for element names starting with 'xml']
OPENELEMENT : '<' ELSTART ELCHAR+ '>' {
	System.out.println("Opening Element Tag: " + getText());
};
CLOSEELEMENT : '</' ELSTART ELCHAR+ '>' {
	System.out.println("Closing Element Tag: " + getText());
};

fragment ELSTART : LETTER | '_';
fragment ELCHAR : ELSTART | DIGIT | [-.] ;

// (3.2) [Need to check for consecutive '..' character]
EMAIL : ~('.') LOCAL+ ('@' DOMAIN+){
	System.out.println("Email: " + getText());
};
fragment LOCAL: LETTER | DIGIT | [-_~!$&'()*+,;=:.];
fragment DOMAIN: LETTER | DIGIT | [-.]; 

// (3.3) [DONE]
DATE : DAY ('/'MONTH+) ('/'YEAR+){
	System.out.println("Date: " + getText());
};
fragment DAY : ('0' ('1' .. '9')) | ('1' ('0' .. '9')) | ('2' ('0' .. '9')) | ('3' ('0' .. '1'));
fragment MONTH : ('0' ('1' .. '9')) | ('1' ('0' .. '2'));
fragment YEAR : '2' ('0' .. '9') ('0' .. '9') ('0' .. '9');

// Ignore new line characters
WS : [\r\n\t]+{skip();};