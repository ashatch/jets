grammar Jets;

prog
  : cmd + EOF
  ;

cmd
  : (fd
  | bk
  | declaration) ';'
  ;

fd
   : ('fd' | 'forward') number
   ;

bk
   : ('bk' | 'backward') number
   ;

declaration : 'String' variable=Var '=' assignedValue=STRING;

number
   : NUMBER
   ;

Var
   : IDENTIFIER
   ;

STRING
   : '"' (~ ["\\])* '"'
   ;

IDENTIFIER
   : [A-Za-z] +
   ;

NUMBER
   : [0-9] +
   ;

WS : [ \n\r\t\u000B\u000C\u0000]+				-> channel(HIDDEN) ;

EOL
   : '\r'? '\n'
   ;