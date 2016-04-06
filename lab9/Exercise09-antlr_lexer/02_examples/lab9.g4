// Ian Baer - Chris Rogers
// Lab 9

lexer grammar lab9;

fragment ELEMENT_OPEN: '<';
fragment ELEMENT_CLOSE: '>';
ELEMENT : (~'<')+ {System.out.println("Element:" + getText());};

// Ignore new line characters
WS: [ \r\n\t]+         {skip();} ;   
