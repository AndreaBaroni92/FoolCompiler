package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.IfexpContext;
import type.BoolType;
import type.IType;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;
import util.TypeErrorMsg;

public class IfExpNode implements Node {

	private IfexpContext ctx;

	private Node exp;

	private Node thenExp;

	private Node elseExp;

	public IfExpNode(IfexpContext ctx, Node exp, Node thenExp, Node elseExp) {
		this.ctx = ctx;
		this.exp = exp;
		this.thenExp = thenExp;
		this.elseExp = elseExp;
	}

	@Override
	public IType typeCheck() throws TypeException{

		IType condType = exp.typeCheck();

		IType thenType = thenExp.typeCheck();

		IType elseType = elseExp.typeCheck();

		if (condType.isSubtypeOf(new BoolType()) == false) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, " non boolean condition in if");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);
		}

		if (thenType.isSubtypeOf(elseType)) {

			return thenType;

		}

		else if (elseType.isSubtypeOf(thenType)) {

			return elseType;

		}

		else {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, " incompatible types in then else branches");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);
		}

	}

	@Override
	public String codeGeneration() {
		
		String tt = FOOLlib.freshLabel();
		String exit = FOOLlib.freshLabel();

		return  exp.codeGeneration() +
				"push 1" + "\n" +
				"beq " + tt + "\n" +
				elseExp.codeGeneration() +
				"b " + exit + "\n" +
				tt + " :" + "\n" +
				thenExp.codeGeneration() +
				exit + " :" + "\n"	
				;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();

		err.addAll(exp.checkSemantics(env));
		err.addAll(thenExp.checkSemantics(env));
		err.addAll(elseExp.checkSemantics(env));

		return err;
	}

}
