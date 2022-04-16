package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.PrintStmContext;
import type.IType;
import type.VoidType;
import util.Environment;
import util.SemanticError;
import util.TypeErrorMsg;

public class PrintNode implements Node {

	private PrintStmContext ctx;

	private Node exp;

	public PrintNode(PrintStmContext ctx, Node exp) {
		this.ctx = ctx;
		this.exp = exp;
	}

	@Override
	public IType typeCheck() throws TypeException {

		IType t = exp.typeCheck();

		// se l'espressione ha tipo void risulta impossibile farne una stampa

		if (t.isSubtypeOf(new VoidType())) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, "impossible to print an expression returning void");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);
		}
		// come da specifica lo statement print restituisce void
		return new VoidType();
	}

	@Override
	public String codeGeneration() {

		return exp.codeGeneration() + "print\n" +"pop" + "\n";

	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> err = new ArrayList<>();

		err.addAll(exp.checkSemantics(env));

		return err;
	}

}
