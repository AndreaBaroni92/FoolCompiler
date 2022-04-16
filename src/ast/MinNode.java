
/*classe che gestisce il segno meno unario*/
package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.MinexpContext;
import type.IType;
import type.IntType;
import util.Environment;
import util.SemanticError;
import util.TypeErrorMsg;

public class MinNode implements Node {

	private Node exp;
	private MinexpContext ctx;

	public MinNode(Node exp, MinexpContext ctx) {
		this.exp = exp;
		this.ctx = ctx;
	}

	@Override
	public IType typeCheck() throws TypeException {
		IType check = exp.typeCheck();

		if (check.isSubtypeOf(new IntType()) == false) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, "unary minus allowed only for int type");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);
		}

		return new IntType();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		err.addAll(this.exp.checkSemantics(env));
		return err;
	}

	@Override
	public String codeGeneration() {

		return exp.codeGeneration() + "push -1" + "\n" + "mult" + "\n";
	}

}
