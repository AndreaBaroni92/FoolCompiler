// Generated from .\FOOL.g4 by ANTLR 4.7.1
package parser;

    import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FOOLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FOOLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleExp(FOOLParser.SingleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letInExp}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetInExp(FOOLParser.LetInExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDefinition}
	 * labeled alternative in {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefinition(FOOLParser.ClassDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#let}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(FOOLParser.LetContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#letfun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetfun(FOOLParser.LetfunContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#vardec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardec(FOOLParser.VardecContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(FOOLParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#varasm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarasm(FOOLParser.VarasmContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#fun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFun(FOOLParser.FunContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#fbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFbody(FOOLParser.FbodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varAssignment}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssignment(FOOLParser.VarAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funDeclaration}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDeclaration(FOOLParser.FunDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(FOOLParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code testop}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestop(FOOLParser.TestopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndexp(FOOLParser.AndexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objinst}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjinst(FOOLParser.ObjinstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code truexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruexp(FOOLParser.TruexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code metcall}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetcall(FOOLParser.MetcallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timediv}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimediv(FOOLParser.TimedivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotexp(FOOLParser.NotexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullexp(FOOLParser.NullexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinexp(FOOLParser.MinexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falsexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalsexp(FOOLParser.FalsexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdexp(FOOLParser.IdexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code voidexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidexp(FOOLParser.VoidexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfexp(FOOLParser.IfexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParexp(FOOLParser.ParexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addminus}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddminus(FOOLParser.AddminusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcallexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncallexp(FOOLParser.FuncallexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intexp}
	 * labeled alternative in {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntexp(FOOLParser.IntexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#stms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStms(FOOLParser.StmsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmAssignment}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmAssignment(FOOLParser.StmAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStm(FOOLParser.IfStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fieldmodstm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldmodstm(FOOLParser.FieldmodstmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStm(FOOLParser.PrintStmContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#classdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdef(FOOLParser.ClassdefContext ctx);
}