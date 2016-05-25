parser grammar JetsParser;
options { tokenVocab = JetsLexer; }

parse
  : cmd +
  ;

cmd
  : (
  | declaration
  | assignment
  | modifier
  | echo
  | comment) SEMI_COLON
  ;

declaration : type=Type variable=Var OP_EQUALS assignedValue=VALUE;
assignment : variable=Var OP_EQUALS assignedValue=VALUE;
modifier : variable=Var OP_CONCATENATE operandValue=VALUE;
echo : CMD_ECHO variable=Var;
comment : DOUBLE_SLASHED_LINE | MULTI_LINE_COMMENT;




