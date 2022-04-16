package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.IntexpContext;
import type.IType;
import type.IntType;
import util.Environment;
import util.SemanticError;

public class IntNode implements Node {

	private Integer val;
	private IntexpContext ctx;

	public IntNode(Integer n, IntexpContext ctx) {
		this.val = n;
		this.ctx = ctx;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		return res;
	}

	@Override
	public IType typeCheck() throws TypeException {
		return new IntType();
	}

	@Override
	public String codeGeneration() {
		return "push " + val + "\n";
	}

}
