grammar Jets;

prog
  : cmd + EOF
  ;

cmd
  : fd
  | bk
  ;

fd
   : ('fd' | 'forward')  number
   ;

bk
   : ('bk' | 'backward') number
   ;

number
   : NUMBER
   ;

NUMBER
   : [0-9] +
   ;

WS : [ \n\r\t\u000B\u000C\u0000]+				-> channel(HIDDEN) ;

EOL
   : '\r'? '\n'
   ;