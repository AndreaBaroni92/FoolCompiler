package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.TestopContext;
import type.BoolType;
import type.IType;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;
import util.TypeErrorMsg;

public class OrNode implements Node {

	private TestopContext ctx;

	private Node left;

	private Node right;

	public OrNode(TestopContext ctx, Node left, Node right) {
		this.ctx = ctx;

		this.left = left;

		this.right = right;
	}

	@Override
	public IType typeCheck() throws TypeException {
		boolean cond1 = left.typeCheck().isSubtypeOf(new BoolType());

		boolean cond2 = right.typeCheck().isSubtypeOf(new BoolType());

		if ((cond1 && cond2) == false) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx,
					"boolean types is required in logical disjunction");

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
                "push 1\n" +
                "beq " + tt + "\n" + // se la parte di sinistra e' vera l'or risulta vero e si salta alla fine
                right.codeGeneration() +
                "push 1\n" +
                "beq " + tt + "\n" +// altrimenti si controlla la parte destra 
                "push 0\n" + "b " + //ne' la parte sinistra ne' quella destra risultano vere e quindi si esce
                exit + "\n"
                + tt + ":\n" +
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
