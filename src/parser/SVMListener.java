// Generated from .\SVM.g4 by ANTLR 4.7.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SVMParser}.
 */
public interface SVMListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SVMParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SVMParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SVMParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SVMParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pushNumbInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterPushNumbInstr(SVMParser.PushNumbInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pushNumbInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitPushNumbInstr(SVMParser.PushNumbInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pushLabInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterPushLabInstr(SVMParser.PushLabInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pushLabInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitPushLabInstr(SVMParser.PushLabInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code popInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterPopInstr(SVMParser.PopInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code popInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitPopInstr(SVMParser.PopInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterAddInstr(SVMParser.AddInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitAddInstr(SVMParser.AddInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterSubInstr(SVMParser.SubInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitSubInstr(SVMParser.SubInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterMultInstr(SVMParser.MultInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitMultInstr(SVMParser.MultInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterDivInstr(SVMParser.DivInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitDivInstr(SVMParser.DivInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code swInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterSwInstr(SVMParser.SwInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code swInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitSwInstr(SVMParser.SwInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lwInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterLwInstr(SVMParser.LwInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lwInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitLwInstr(SVMParser.LwInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code labelInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterLabelInstr(SVMParser.LabelInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code labelInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitLabelInstr(SVMParser.LabelInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code onlyLabelInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterOnlyLabelInstr(SVMParser.OnlyLabelInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code onlyLabelInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitOnlyLabelInstr(SVMParser.OnlyLabelInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code branchLabInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterBranchLabInstr(SVMParser.BranchLabInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code branchLabInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitBranchLabInstr(SVMParser.BranchLabInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code brancEqInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterBrancEqInstr(SVMParser.BrancEqInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code brancEqInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitBrancEqInstr(SVMParser.BrancEqInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code brancLEqInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterBrancLEqInstr(SVMParser.BrancLEqInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code brancLEqInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitBrancLEqInstr(SVMParser.BrancLEqInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterJsInstr(SVMParser.JsInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitJsInstr(SVMParser.JsInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lraInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterLraInstr(SVMParser.LraInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lraInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitLraInstr(SVMParser.LraInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sraInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterSraInstr(SVMParser.SraInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sraInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitSraInstr(SVMParser.SraInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lrvInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterLrvInstr(SVMParser.LrvInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lrvInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitLrvInstr(SVMParser.LrvInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code srvInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterSrvInstr(SVMParser.SrvInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code srvInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitSrvInstr(SVMParser.SrvInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lfpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterLfpInstr(SVMParser.LfpInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lfpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitLfpInstr(SVMParser.LfpInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sfpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterSfpInstr(SVMParser.SfpInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sfpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitSfpInstr(SVMParser.SfpInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cfpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterCfpInstr(SVMParser.CfpInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cfpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitCfpInstr(SVMParser.CfpInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lhpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterLhpInstr(SVMParser.LhpInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lhpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitLhpInstr(SVMParser.LhpInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterShpInstr(SVMParser.ShpInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shpInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitShpInstr(SVMParser.ShpInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printInst}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterPrintInst(SVMParser.PrintInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printInst}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitPrintInst(SVMParser.PrintInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code haltInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterHaltInstr(SVMParser.HaltInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code haltInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitHaltInstr(SVMParser.HaltInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterNewInstr(SVMParser.NewInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitNewInstr(SVMParser.NewInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lamInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterLamInstr(SVMParser.LamInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lamInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitLamInstr(SVMParser.LamInstrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cfvInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void enterCfvInstr(SVMParser.CfvInstrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cfvInstr}
	 * labeled alternative in {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 */
	void exitCfvInstr(SVMParser.CfvInstrContext ctx);
}