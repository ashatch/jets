grammar Jets;

prog
  : cmd + EOF
  ;

cmd
  : (fd
  | bk
  | declaration
  | modifier
  | echo) ';'
  ;

fd
   : ('fd' | 'forward') number
   ;

bk
   : ('bk' | 'backward') number
   ;

declaration : 'String' variable=Var '=' assignedValue=STRING;

modifier : variable=Var operator operand;

echo : 'echo' variable=Var;

number
   : NUMBER
   ;

operator : '+=';
operand : STRING;

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