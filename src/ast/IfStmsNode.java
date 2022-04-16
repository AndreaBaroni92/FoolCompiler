package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.IfStmContext;
import type.BoolType;
import type.IType;
import type.VoidType;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;
import util.TypeErrorMsg;

public class IfStmsNode implements Node {

	private IfStmContext ctx;

	private Node exp;

	private ArrayList<Node> thenStms;

	private ArrayList<Node> elseStms;

	public IfStmsNode(IfStmContext ctx, Node exp, ArrayList<Node> thenStms, ArrayList<Node> elseStms) {
		this.ctx = ctx;
		this.exp = exp;
		this.thenStms = thenStms;
		this.elseStms = elseStms;
	}

	@Override
	public IType typeCheck() throws TypeException {

		if (exp.typeCheck().isSubtypeOf(new BoolType()) == false) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, "non boolean condition in if");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);
		}

		/*
		 * viene controllato il fatto che negli statements presenti nel ramo then e nel
		 * ramo else non ci siano erroti di tipo
		 */
		for (Node n : thenStms) {
			n.typeCheck();

		}

		for (Node n : elseStms) {
			n.typeCheck();

		}

		return new VoidType();
	}

	@Override
	public String codeGeneration() {

		String tt = FOOLlib.freshLabel();
		String exit = FOOLlib.freshLabel();
		String thenS = "";
		String elseS = "";

		for (Node n : thenStms) {
			thenS = thenS.concat(n.codeGeneration());
		}

		for (Node n : elseStms) {
			elseS = elseS.concat(n.codeGeneration());
		}

		return  exp.codeGeneration() +
				"push 1" + "\n" +
				"beq " + tt + "\n" +
				elseS +
				"b " + exit + "\n" +
				tt + " :" + "\n" +
				thenS +
				exit + " :" + "\n"	
				;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();

		err.addAll(exp.checkSemantics(env));

		for (Node n : thenStms) {
			err.addAll(n.checkSemantics(env));

		}

		for (Node n : elseStms) {
			err.addAll(n.checkSemantics(env));

		}

		return err;
	}

}
