package ast;

import java.util.ArrayList;

import exception.TypeException;
import type.BoolType;
import type.IType;
import util.Environment;
import util.SemanticError;

public class BFNode implements Node {

	@Override
	public IType typeCheck() throws TypeException {
		return new BoolType();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		return err;
	}

	@Override
	public String codeGeneration() {
		String ris = "push 0";
		return ris + "\n";
	}

}
