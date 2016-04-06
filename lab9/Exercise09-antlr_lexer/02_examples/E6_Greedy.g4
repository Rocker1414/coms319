lexer grammar E6_Greedy;


COMMENT: '/*' .*? '*/' {System.out.println("comment: "+getText());};


WS: [ \r\n\t]+         {skip();} ;   
