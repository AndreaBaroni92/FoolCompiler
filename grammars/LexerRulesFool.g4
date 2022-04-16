lexer grammar LexerRulesFool;


@header {
    import java.util.ArrayList;
}

@lexer::members {
   public ArrayList<String> errors = new ArrayList<>();
}

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
DOT : '.';
SEMIC  : ';' ;
COLON  : ':' ;
COMMA  : ',' ;
EQ     : '==' ;
ASM    : '=' ;
PLUS   : '+' ;
MINUS  : '-' ;
TIMES  : '*' ;
DIV    : '/' ;
TRUE   : 'true' ;
FALSE  : 'false' ;
LPAR   : '(' ;
RPAR   : ')' ;
CLPAR  : '{' ;
CRPAR  : '}' ;
IF        : 'if' ;
THEN   : 'then' ;
ELSE   : 'else' ;
PRINT : 'print' ; 
LET    : 'let' ;
IN     : 'in' ;
VAR    : 'var' ;
FUN    : 'fun' ;
INT    : 'int' ;
BOOL   : 'bool' ;
VOID   : 'void' ;
NOT    : ('not'|'!') ;
CLASS : 'class' ;
EXTENDS: 'extends';
NEW : 'new';
LEQ : '<=' ;
GEQ: '>=' ;
OR : ('||' | 'or' ) ;
AND :('&&'| 'and') ;
NULL:'null';

//Numbers
fragment DIGIT : '0'..'9';    
INTEGER       : DIGIT+;

//IDs
fragment CHAR  : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPED SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMMENTS : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'
BLOCKCOMMENTS : '/*' .*? '*/' -> skip ; // Match "/*" stuff "*/"

//Error management
ERR     : . { errors.add("Invalid char: " + getText());} -> channel(HIDDEN) ;
