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

fragment PHONE_ONE: (\d{3}\-\d{3}\-\d{3});  // ###-###-####
fragment PHONE_TWO: (\(\d{3}\)\d{3}\-\d{3}); // (###)###-###
fragment PHONE_THREE: (\d{3}\s\d{3}\s\d{3}); // ### ### ###
fragment PHONE_FOUR: (\d{3}\.\d{3}\.\d{3});  // ###.###.###

PHONE: PHONE_ONE | PHONE_TWO | PHONE_THREE | PHONE_FOUR {System.out.println("Phone Number:" + getText())};


fragment VISA: ('4'\d{15}) | ('4'\d{12}); //start with 4, 16 digits or 13 digits
fragment MASTER: ([51-55]\d{14}); //51-55, 16 digits
fragment AMERICAN_EXPRESS: (('34' | '37')/d{13});  //34 or 37, 15 digits
fragment DINERS_CLUB: ([300-305]\d{11}) | (('36' | '38')\d{12});  //300-305, 36 or 38, 14 digits. TODO master card extra
fragment DISCOVER: ('6011'\d{12}) | ('65'\d{14});  //6011 or 65, 16 digits
fragment JCB: (('2131' | '1800')\d{11}) | ('35'\d{14});  //2131 or 1800 and 15 digits, 35 and 16 digits
CARD: VISA | MASTER | AMERICAN_EXPRESS | DINERS_CLUB | DISCOVER | JCB {System.out.println("Credit Card:" + getText())};

// Ignore new line characters
WS : [\r\n\t]+{skip();};