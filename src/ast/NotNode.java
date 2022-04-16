/*
 * classe impiegata per gestire il not unario
 */

package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.NotexpContext;
import type.BoolType;
import type.IType;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;
import util.TypeErrorMsg;

public class NotNode implements Node {

	private NotexpContext ctx;

	private Node exp;

	public NotNode(NotexpContext ctx, Node exp) {
		this.ctx = ctx;
		this.exp = exp;
	}

	@Override
	public IType typeCheck() throws TypeException {
		IType check = exp.typeCheck();

		if (check.isSubtypeOf(new BoolType()) == false) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, "unary not must precede a boolean type ");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);
		}

		return new BoolType();
	}

	@Override
	public String codeGeneration() {
		
		String test1 = FOOLlib.freshLabel();
		String exit = FOOLlib.freshLabel();;
		
		
		return exp.codeGeneration() +
				"push 1" +      "\n"+
				"beq "   + test1 + "\n"+
				"push 1" +      "\n"+
			    "b "+ exit+      "\n"+
				test1+ " :"  +      "\n"+
			    "push 0" +      "\n"+
				exit +" :" +      "\n"
				;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		err.addAll(this.exp.checkSemantics(env));
		return err;
	}

}
