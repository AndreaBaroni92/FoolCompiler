// Generated from .\FOOL.g4 by ANTLR 4.7.1
package parser;

    import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FOOLParser}.
 */
public interface FOOLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterSingleExp(FOOLParser.SingleExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitSingleExp(FOOLParser.SingleExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letInExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterLetInExp(FOOLParser.LetInExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letInExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitLetInExp(FOOLParser.LetInExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classDefinition}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterClassDefinition(FOOLParser.ClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classDefinition}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitClassDefinition(FOOLParser.ClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#let}.
	 * @param ctx the parse tree
	 */
	void enterLet(FOOLParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#let}.
	 * @param ctx the parse tree
	 */
	void exitLet(FOOLParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#letfun}.
	 * @param ctx the parse tree
	 */
	void enterLetfun(FOOLParser.LetfunContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#letfun}.
	 * @param ctx the parse tree
	 */
	void exitLetfun(FOOLParser.LetfunContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#vardec}.
	 * @param ctx the parse tree
	 */
	void enterVardec(FOOLParser.VardecContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#vardec}.
	 * @param ctx the parse tree
	 */
	void exitVardec(FOOLParser.VardecContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(FOOLParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(FOOLParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#varasm}.
	 * @param ctx the parse tree
	 */
	void enterVarasm(FOOLParser.VarasmContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#varasm}.
	 * @param ctx the parse tree
	 */
	void exitVarasm(FOOLParser.VarasmContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(FOOLParser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(FOOLParser.FunContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#fbody}.
	 * @param ctx the parse tree
	 */
	void enterFbody(FOOLParser.FbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#fbody}.
	 * @param ctx the parse tree
	 */
	void exitFbody(FOOLParser.FbodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varAssignment}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterVarAssignment(FOOLParser.VarAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varAssignment}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitVarAssignment(FOOLParser.VarAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funDeclaration}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterFunDeclaration(FOOLParser.FunDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funDeclaration}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitFunDeclaration(FOOLParser.FunDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(FOOLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(FOOLParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code testop}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterTestop(FOOLParser.TestopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code testop}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitTestop(FOOLParser.TestopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAndexp(FOOLParser.AndexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAndexp(FOOLParser.AndexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objinst}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterObjinst(FOOLParser.ObjinstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objinst}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitObjinst(FOOLParser.ObjinstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code truexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterTruexp(FOOLParser.TruexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code truexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitTruexp(FOOLParser.TruexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code metcall}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMetcall(FOOLParser.MetcallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code metcall}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMetcall(FOOLParser.MetcallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timediv}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterTimediv(FOOLParser.TimedivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timediv}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitTimediv(FOOLParser.TimedivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNotexp(FOOLParser.NotexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNotexp(FOOLParser.NotexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNullexp(FOOLParser.NullexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNullexp(FOOLParser.NullexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMinexp(FOOLParser.MinexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMinexp(FOOLParser.MinexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falsexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFalsexp(FOOLParser.FalsexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falsexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFalsexp(FOOLParser.FalsexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIdexp(FOOLParser.IdexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIdexp(FOOLParser.IdexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code voidexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterVoidexp(FOOLParser.VoidexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code voidexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitVoidexp(FOOLParser.VoidexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIfexp(FOOLParser.IfexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIfexp(FOOLParser.IfexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterParexp(FOOLParser.ParexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitParexp(FOOLParser.ParexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addminus}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAddminus(FOOLParser.AddminusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addminus}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAddminus(FOOLParser.AddminusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcallexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterFuncallexp(FOOLParser.FuncallexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcallexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitFuncallexp(FOOLParser.FuncallexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIntexp(FOOLParser.IntexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIntexp(FOOLParser.IntexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#stms}.
	 * @param ctx the parse tree
	 */
	void enterStms(FOOLParser.StmsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#stms}.
	 * @param ctx the parse tree
	 */
	void exitStms(FOOLParser.StmsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmAssignment}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterStmAssignment(FOOLParser.StmAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmAssignment}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitStmAssignment(FOOLParser.StmAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterIfStm(FOOLParser.IfStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitIfStm(FOOLParser.IfStmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fieldmodstm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterFieldmodstm(FOOLParser.FieldmodstmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fieldmodstm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitFieldmodstm(FOOLParser.FieldmodstmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterPrintStm(FOOLParser.PrintStmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitPrintStm(FOOLParser.PrintStmContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOOLParser#classdef}.
	 * @param ctx the parse tree
	 */
	void enterClassdef(FOOLParser.ClassdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOOLParser#classdef}.
	 * @param ctx the parse tree
	 */
	void exitClassdef(FOOLParser.ClassdefContext ctx);
}