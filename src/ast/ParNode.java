package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.ParexpContext;
import type.IType;
import util.Environment;
import util.SemanticError;

public class ParNode implements Node {

	private ParexpContext ctx;

	private Node exp;

	public ParNode(ParexpContext ctx, Node exp) {
		this.ctx = ctx;
		this.exp = exp;
	}

	@Override
	public IType typeCheck() throws TypeException {

		return exp.typeCheck();
	}

	@Override
	public String codeGeneration() {
		String ris = exp.codeGeneration();
		return ris;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		err.addAll(this.exp.checkSemantics(env));
		return err;
	}

}
