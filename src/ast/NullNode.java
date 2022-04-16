package ast;

import java.util.ArrayList;

import exception.TypeException;
import type.IType;
import type.NullType;
import util.Environment;
import util.SemanticError;

public class NullNode implements Node {

	@Override
	public IType typeCheck() throws TypeException {
		
		return new NullType();
	}

	@Override
	public String codeGeneration() {
		int value = Integer.MAX_VALUE;
		return "push " +Integer.toString(value)+"\n";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		return err;
	}
	@Override
	public String toString() {
		
		return "null";
	}

}
