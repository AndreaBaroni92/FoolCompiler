package ast;

import java.util.ArrayList;

import exception.TypeException;
import type.IType;
import type.VoidType;
import util.Environment;
import util.SemanticError;

public class VoidNode implements Node {

	@Override
	public IType typeCheck() throws TypeException{
		
		return new VoidType();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError>  err = new ArrayList<>();
		return err;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

}
