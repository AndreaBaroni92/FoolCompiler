package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.SingleExpContext;
import type.IType;
import util.Environment;
import util.SemanticError;

public class SingleExpNode implements Node {

	private Node exp;

	public SingleExpNode(Node exp) {
		this.exp = exp;
	}

	@Override
	public IType typeCheck() throws TypeException {

		return (this.exp.typeCheck());
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		err.addAll(exp.checkSemantics(env));
		return err;
	}

	@Override
	public String codeGeneration() {

		return exp.codeGeneration() + "halt" + "\n";
	}

}
