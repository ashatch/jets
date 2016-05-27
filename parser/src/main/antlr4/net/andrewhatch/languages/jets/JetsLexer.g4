lexer grammar JetsLexer;

SEMI_COLON: ';';
OP_EQUALS: '=';
OP_CONCATENATE: '+=';
CMD_ECHO: 'echo';

STRING_DELIMITER: '"';



Type : TYPE_STRING | TYPE_INTEGER;

TYPE_STRING: 'String';
TYPE_INTEGER: 'Integer';

Var: IDENTIFIER;

IDENTIFIER: [A-Za-z] +;
VALUE: NUMBER | STRING;
NUMBER: [0-9] +;
STRING: STRING_DELIMITER (~ ["\\])* STRING_DELIMITER;

WS: [ \n\r\t\u000B\u000C\u0000]+				-> skip ;