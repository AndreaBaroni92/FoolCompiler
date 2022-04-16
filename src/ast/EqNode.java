package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.TestopContext;
import type.BoolType;
import type.IType;
import type.IntType;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;
import util.TypeErrorMsg;

public class EqNode implements Node {

	private TestopContext ctx;

	private Node left;

	private Node right;

	public EqNode(TestopContext ctx, Node left, Node right) {
		this.ctx = ctx;

		this.left = left;

		this.right = right;
	}

	@Override
	public IType typeCheck() throws TypeException{
		boolean cond1 = left.typeCheck().isSubtypeOf(new IntType());

		boolean cond2 = right.typeCheck().isSubtypeOf(new IntType());

		if ((cond1 && cond2) == false) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, " integer type is required for equal comparison ");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);

		}

		return new BoolType();
	}

	@Override
	public String codeGeneration() {
        String tt = FOOLlib.freshLabel();
        String exit = FOOLlib.freshLabel();
        return left.codeGeneration() +
                right.codeGeneration() +
                "beq " + tt + "\n" +
                "push 0\n" +
                "b " + exit + "\n" +
                tt + ":\n" +
                "push 1\n" +
                exit + ":\n";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		err.addAll(this.left.checkSemantics(env));
		err.addAll(this.right.checkSemantics(env));

		return err;
	}

}
