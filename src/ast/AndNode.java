package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.AndexpContext;
import type.BoolType;
import type.IType;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;
import util.TypeErrorMsg;

public class AndNode implements Node {

	private AndexpContext ctx;

	private Node left;

	private Node right;

	public AndNode(AndexpContext ctx, Node left, Node right) {

		this.ctx = ctx;

		this.left = left;

		this.right = right;

	}

	@Override
	public IType typeCheck() throws TypeException {

		boolean cond1 = left.typeCheck().isSubtypeOf(new BoolType());

		boolean cond2 = right.typeCheck().isSubtypeOf(new BoolType());

		if ((cond1 && cond2) == false) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, "boolean types is required in logical conjunction");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);

		}

		return new BoolType();
	}

	@Override
	public String codeGeneration() {
        String test = FOOLlib.freshLabel();
        String exit = FOOLlib.freshLabel();
        String tt = FOOLlib.freshLabel();
        return  left.codeGeneration()
                + right.codeGeneration()
                + "beq " + test + "\n"
                + "push 0\n"
                + "b " + exit + "\n"
                + test + ":\n"
                + left.codeGeneration()
                + "push 1\n"
                + "beq " + tt + "\n" // se la condizione e' vera salta alla label tt e mette 1 sullo stack
                + "push 0\n"
                + "b " + exit + "\n"
                + tt + ":\n"
                + "push 1\n"
                + exit + ":\n";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		err.addAll(this.left.checkSemantics(env));
		err.addAll(this.right.checkSemantics(env));

		return err;
	}

}
