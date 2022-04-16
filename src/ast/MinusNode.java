/*
 * classe che gestisce l'operatore binario della sottrazione fra due numeri interi
 */
package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.AddminusContext;
import type.IType;
import type.IntType;
import util.Environment;
import util.SemanticError;
import util.TypeErrorMsg;

public class MinusNode implements Node {

	private AddminusContext ctx;

	private Node left;

	private Node right;

	public MinusNode(AddminusContext ctx, Node left, Node right) {

		this.ctx = ctx;

		this.left = left;

		this.right = right;

	}

	@Override
	public IType typeCheck() throws TypeException {

		boolean cond1 = left.typeCheck().isSubtypeOf(new IntType());
		boolean cond2 = right.typeCheck().isSubtypeOf(new IntType());

		if ((cond1 && cond2) == false) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, "non integers in subtraction");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);

		}

		return new IntType();		

	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		err.addAll(this.left.checkSemantics(env));
		err.addAll(this.right.checkSemantics(env));

		return err;
	}

	@Override
	public String codeGeneration() {

		return left.codeGeneration() + right.codeGeneration() + "sub" + "\n";
	}

}
