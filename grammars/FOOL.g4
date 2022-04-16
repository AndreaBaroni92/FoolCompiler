grammar FOOL;
import LexerRulesFool;

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
  
prog   : exp SEMIC                 			 	 #singleExp
       | let (exp SEMIC | stms )             	 #letInExp
       
       |(classdef ) + (let)? (exp SEMIC  | stms )  #classDefinition
       ;

let       : LET (dec )+ IN ;

letfun : LET (varasm )+ IN ; // per evitare che si dicharino funzioni annidate

vardec  : type nameVar=ID ;

args :( vardec ( COMMA vardec)* ) ;

varasm     : vardec (ASM exp)? SEMIC ;//ES : int a = 43

fun    : type nameFun=ID LPAR (args) ? RPAR fbody ;

fbody: (letfun)?  ( exp SEMIC | stms) ;

dec   : varasm           #varAssignment
      | fun              #funDeclaration
     
      ;
         

type   : INT  
        | BOOL 
        | VOID
        |nameofhypotheticalclass= ID
      ;  
 /* exp    :  ('-')? left=term ((PLUS | MINUS) right=exp)?
      ;
   
term   : left=factor ((TIMES | DIV) right=term)?
      ;
   
factor : left=value ((EQ | MIEQ| GREQ| OR | AND) right=value)?
      ;     
   
value  :  INTEGER                           #intVal
      | NOT? ( TRUE | FALSE )                   #boolVal
      | NOT? LPAR exp RPAR                      #baseExp
      | IF cond=exp THEN CLPAR thenBranch=exp CRPAR ELSE CLPAR elseBranch=exp CRPAR  #ifExp
      |  NOT? ID                                             #varExp
      | ID ( LPAR (exp (COMMA exp)* )? RPAR )?         #funExp
      | ID DOT ID LPAR (exp (COMMA exp)* )? RPAR       #metCallValue
      | NEW ID LPAR (exp (COMMA exp)* )? RPAR          #idClass
      
      ; */   

exp : 	MINUS exp #minexp
	|  left= exp op= (TIMES | DIV) right= exp 		#timediv
    |  left= exp op=(PLUS | MINUS) right= exp		#addminus
    |  left= exp op= (GEQ|OR|LEQ|EQ)right= exp #testop
    |	NOT exp #notexp
    |	left=exp op=AND right=exp #andexp
    |	IF cond=exp THEN CLPAR thenBranch=exp CRPAR ELSE CLPAR elseBranch=exp  CRPAR #ifexp
    |	ID  LPAR (exp (COMMA exp)* )? RPAR   #funcallexp
    | 	NEW nameClass=ID LPAR (exp (COMMA exp)* )? RPAR  #objinst
    |	objectName=ID '.' methodName=ID LPAR (exp (COMMA exp)*)? RPAR #metcall 
    |	LPAR exp RPAR  #parexp
    |	INTEGER #intexp
    |	VOID #voidexp
    |	TRUE #truexp
    |	FALSE #falsexp
    |   NULL  #nullexp
    |	ID	#idexp
    ;



stms : stm+   ;  

 
stm : ID ASM exp SEMIC #stmAssignment
	| IF conds= exp THEN CLPAR  thenBranchs=stms CRPAR ELSE CLPAR elseBranchs=stms CRPAR SEMIC #ifStm
	| objID=ID DOT fieID=ID ASM exp SEMIC  #fieldmodstm // i campi sono modificabili
	| PRINT LPAR exp RPAR SEMIC #printStm
	 ;

classdef : CLASS son=ID (EXTENDS parent=ID)? LPAR (args)? RPAR  CLPAR (fun )* CRPAR ;

