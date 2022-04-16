package ast;

import java.util.ArrayList;

import exception.TypeException;
import type.IType;
import util.Environment;
import util.SemanticError;

public class ArgsNode implements Node {

	private ArrayList<Node> formalParameters;

	public ArgsNode(ArrayList<Node> formalParameters) {
		this.formalParameters = formalParameters;

	}


	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();

		for (Node par : formalParameters) {

			err.addAll(par.checkSemantics(env));

		}
		return err;
	}
	
	public ArrayList<Node> getArgsNode(){
		return this.formalParameters;
		
	}


	@Override
	public IType typeCheck() throws TypeException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

}
