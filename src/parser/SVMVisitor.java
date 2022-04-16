// Generated from .\SVM.g4 by ANTLR 4.7.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SVMParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SVMVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SVMParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SVMParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pushNumbInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPushNumbInstr(SVMParser.PushNumbInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pushLabInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPushLabInstr(SVMParser.PushLabInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code popInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPopInstr(SVMParser.PopInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddInstr(SVMParser.AddInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubInstr(SVMParser.SubInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultInstr(SVMParser.MultInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivInstr(SVMParser.DivInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code swInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwInstr(SVMParser.SwInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lwInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLwInstr(SVMParser.LwInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code labelInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelInstr(SVMParser.LabelInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code onlyLabelInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnlyLabelInstr(SVMParser.OnlyLabelInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code branchLabInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchLabInstr(SVMParser.BranchLabInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brancEqInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrancEqInstr(SVMParser.BrancEqInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code brancLEqInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrancLEqInstr(SVMParser.BrancLEqInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jsInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsInstr(SVMParser.JsInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lraInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLraInstr(SVMParser.LraInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sraInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSraInstr(SVMParser.SraInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lrvInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLrvInstr(SVMParser.LrvInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code srvInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSrvInstr(SVMParser.SrvInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lfpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLfpInstr(SVMParser.LfpInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sfpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSfpInstr(SVMParser.SfpInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cfpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCfpInstr(SVMParser.CfpInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lhpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLhpInstr(SVMParser.LhpInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code shpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShpInstr(SVMParser.ShpInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printInst}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintInst(SVMParser.PrintInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code haltInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHaltInstr(SVMParser.HaltInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewInstr(SVMParser.NewInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lamInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLamInstr(SVMParser.LamInstrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cfvInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCfvInstr(SVMParser.CfvInstrContext ctx);
}