grammar SVM;

import LexerRulesSVM;


/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
 
program: ( assembly )*;
 
assembly :

	  PUSH n = NUMBER # pushNumbInstr
	| PUSH l = LABEL # pushLabInstr
	| POP # popInstr
	| ADD # addInstr
	| SUB # subInstr
	| MULT #multInstr
	| DIV #divInstr
	| STOREW #swInstr
	| LOADW  #lwInstr
	| l = LABEL COL #labelInstr
	| l = LABEL #onlyLabelInstr // per descrivere i metodi nella simble table
	| BRANCH l = LABEL #branchLabInstr
	| BRANCHEQ l = LABEL #brancEqInstr
	| BRANCHLESSEQ l = LABEL #brancLEqInstr
	| JS #jsInstr
	| LOADRA #lraInstr
	| STORERA #sraInstr
	| LOADRV #lrvInstr
	| STORERV #srvInstr
	| LOADFP #lfpInstr
	| STOREFP #sfpInstr
	| COPYFP #cfpInstr
	| LOADHP #lhpInstr
	| STOREHP #shpInstr
	| PRINT #printInst
	| HALT #haltInstr
	| NEW #newInstr
	| LAM #lamInstr
	| CFV #cfvInstr
;
