package ast;

import java.util.ArrayList;

import exception.TypeException;
import type.IType;
import util.Environment;
import util.SemanticError;

public interface Node {

//	String toPrint(String indent);

	IType typeCheck() throws TypeException;

	String codeGeneration();

	ArrayList<SemanticError> checkSemantics(Environment env);

}
