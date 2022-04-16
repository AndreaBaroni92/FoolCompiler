package visitor;

import java.util.HashMap;

import ast.Node;
import parser.SVMBaseVisitor;
import parser.SVMLexer;
import parser.SVMParser.AddInstrContext;
import parser.SVMParser.AssemblyContext;
import parser.SVMParser.BrancEqInstrContext;
import parser.SVMParser.BrancLEqInstrContext;
import parser.SVMParser.BranchLabInstrContext;
import parser.SVMParser.CfpInstrContext;
import parser.SVMParser.CfvInstrContext;
import parser.SVMParser.DivInstrContext;
import parser.SVMParser.HaltInstrContext;
import parser.SVMParser.JsInstrContext;
import parser.SVMParser.LabelInstrContext;
import parser.SVMParser.LamInstrContext;
import parser.SVMParser.LfpInstrContext;
import parser.SVMParser.LhpInstrContext;
import parser.SVMParser.LraInstrContext;
import parser.SVMParser.LrvInstrContext;
import parser.SVMParser.LwInstrContext;
import parser.SVMParser.MultInstrContext;
import parser.SVMParser.NewInstrContext;
import parser.SVMParser.OnlyLabelInstrContext;
import parser.SVMParser.PopInstrContext;
import parser.SVMParser.PrintInstContext;
import parser.SVMParser.ProgramContext;
import parser.SVMParser.PushLabInstrContext;
import parser.SVMParser.PushNumbInstrContext;
import parser.SVMParser.SfpInstrContext;
import parser.SVMParser.ShpInstrContext;
import parser.SVMParser.SraInstrContext;
import parser.SVMParser.SrvInstrContext;
import parser.SVMParser.SubInstrContext;
import parser.SVMParser.SwInstrContext;
import util.VmInfo;

public class MySvmVisitor extends SVMBaseVisitor<Node> {

	private int[] code = new int[VmInfo.codesize];
	private int i = 0;
	private HashMap<String, Integer> labelAdd = new HashMap<String, Integer>();
	private HashMap<Integer, String> labelRef = new HashMap<Integer, String>();

	@Override
	public Node visitProgram(ProgramContext ctx) {

		for (AssemblyContext n : ctx.assembly()) {

			visit(n);

		}

		return null;
	}

	@Override
	public Node visitPushNumbInstr(PushNumbInstrContext ctx) {

		code[i] = SVMLexer.PUSH;
		i++;
		code[i] = Integer.parseInt(ctx.n.getText());
		i++;

		return null;
	}

	@Override
	public Node visitPushLabInstr(PushLabInstrContext ctx) {

		code[i] = SVMLexer.PUSH;
		i++;
		labelRef.put(i, ctx.l.getText());
		i++;

		return null;
	}

	@Override
	public Node visitPopInstr(PopInstrContext ctx) {

		code[i] = SVMLexer.POP;
		i++;
		return null;

	}

	@Override
	public Node visitAddInstr(AddInstrContext ctx) {

		code[i] = SVMLexer.ADD;
		i++;

		return null;
	}

	@Override
	public Node visitSubInstr(SubInstrContext ctx) {

		code[i] = SVMLexer.SUB;
		i++;
		return null;
	}

	@Override
	public Node visitMultInstr(MultInstrContext ctx) {
		code[i] = SVMLexer.MULT;
		i++;
		return null;
	}

	@Override
	public Node visitDivInstr(DivInstrContext ctx) {
		code[i] = SVMLexer.DIV;
		i++;
		return null;
	}

	@Override
	public Node visitSwInstr(SwInstrContext ctx) {
		code[i] = SVMLexer.STOREW;
		i++;
		return null;
	}

	@Override
	public Node visitLwInstr(LwInstrContext ctx) {
		code[i] = SVMLexer.LOADW;
		i++;
		return null;
	}

	@Override
	public Node visitLabelInstr(LabelInstrContext ctx) {// quando e' presente una label non viene aggiunta nessuna
														// istruzione al codice , si deve solo tenere traccia
														// dell'indirizzo della prossima istruzione
		labelAdd.put(ctx.l.getText(), i);
		return null;
	}

	@Override
	public Node visitOnlyLabelInstr(OnlyLabelInstrContext ctx) {
		labelRef.put(i, ctx.l.getText());
		i++;
		return null;
	}

	@Override
	public Node visitBranchLabInstr(BranchLabInstrContext ctx) {

		code[i] = SVMLexer.BRANCH;
		i++;
		labelRef.put(i, ctx.l.getText());
		i++;
		return null;
	}

	@Override
	public Node visitBrancEqInstr(BrancEqInstrContext ctx) {

		code[i] = SVMLexer.BRANCHEQ;
		i++;
		labelRef.put(i, ctx.l.getText());
		i++;

		return null;
	}

	@Override
	public Node visitBrancLEqInstr(BrancLEqInstrContext ctx) {

		code[i] = SVMLexer.BRANCHLESSEQ;
		i++;
		labelRef.put(i, ctx.l.getText());
		i++;
		return null;
	}

	@Override
	public Node visitJsInstr(JsInstrContext ctx) {
		code[i] = SVMLexer.JS;
		i++;
		return null;
	}

	@Override
	public Node visitLraInstr(LraInstrContext ctx) {
		code[i] = SVMLexer.LOADRA;
		i++;
		return null;
	}

	@Override
	public Node visitSraInstr(SraInstrContext ctx) {
		code[i] = SVMLexer.STORERA;
		i++;
		return null;
	}

	@Override
	public Node visitLrvInstr(LrvInstrContext ctx) {
		code[i] = SVMLexer.LOADRV;
		i++;
		return null;
	}

	@Override
	public Node visitSrvInstr(SrvInstrContext ctx) {
		code[i] = SVMLexer.STORERV;
		i++;
		return null;
	}

	@Override
	public Node visitLfpInstr(LfpInstrContext ctx) {
		code[i] = SVMLexer.LOADFP;
		i++;
		return null;
	}

	@Override
	public Node visitSfpInstr(SfpInstrContext ctx) {
		code[i] = SVMLexer.STOREFP;
		i++;
		return null;
	}

	@Override
	public Node visitCfpInstr(CfpInstrContext ctx) {
		code[i] = SVMLexer.COPYFP;
		i++;
		return null;
	}

	@Override
	public Node visitLhpInstr(LhpInstrContext ctx) {
		code[i] = SVMLexer.LOADHP;
		i++;
		return null;
	}

	@Override
	public Node visitShpInstr(ShpInstrContext ctx) {
		code[i] = SVMLexer.STOREHP;
		i++;
		return null;
	}

	@Override
	public Node visitPrintInst(PrintInstContext ctx) {
		
		code[i] = SVMLexer.PRINT;
		i++;
		return null;
	}

	@Override
	public Node visitHaltInstr(HaltInstrContext ctx) {
		code[i] = SVMLexer.HALT;
		i++;
		return null;
	}

	@Override
	public Node visitNewInstr(NewInstrContext ctx) {
		code[i] = SVMLexer.NEW;
		i++;
		return null;
	}

	@Override
	public Node visitLamInstr(LamInstrContext ctx) {
		code[i] = SVMLexer.LAM;
		i++;
		return null;
	}

	@Override
	public Node visitCfvInstr(CfvInstrContext ctx) {
		code[i] = SVMLexer.CFV;
		i++;
		return null;
	}
	
	public int[] getCode() {
		for (Integer refAdd : labelRef.keySet()) {
			
			code[refAdd] = labelAdd.get(labelRef.get(refAdd));
		}
		return code;
	}
}
