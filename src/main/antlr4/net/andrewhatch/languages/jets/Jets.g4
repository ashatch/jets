grammar Jets;

parse : cmd + EOF;

cmd :
  (   declaration |
      assignment  |
      modifier    |
      echo
  ) ';';

declaration : 'String' variable=Var OP_ASSIGNMENT assignedValue=STRING;
assignment : variable=Var OP_ASSIGNMENT assignedValue=STRING;
modifier : variable=Var operator operand;
echo : 'echo' variable=Var;

number : NUMBER ;
operator : OP_CONCATENATE;
operand : STRING;

Var : IDENTIFIER;

STRING : '"' (~ ["\\])* '"' ;
IDENTIFIER : [A-Za-z]+ ;
NUMBER : [0-9]+ ;

OP_CONCATENATE: '+=' ;
OP_ASSIGNMENT: '=' ;

WS : [ \n\r\t\u000B\u000C\u0000]+    -> channel(HIDDEN) ;
EOL : '\r'? '\n' ;