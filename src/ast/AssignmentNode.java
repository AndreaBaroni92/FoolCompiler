/*
 * classe deputata a gesire l'assegnamento negli statements
 */

package ast;

import java.util.ArrayList;

import exception.NotDefinedVar;
import exception.TypeException;
import parser.FOOLParser.StmAssignmentContext;
import type.ClassType;
import type.IType;
import type.VoidType;
import util.Environment;
import util.SemanticError;
import util.TypeErrorMsg;

public class AssignmentNode implements Node {

	private StmAssignmentContext ctx;
	private Node exp;
	private STentry entry;
	private int nl;

	public AssignmentNode(StmAssignmentContext ctx, Node exp) {
		this.ctx = ctx;
		this.exp = exp;
		entry = null;
	}

	@Override
	public IType typeCheck() throws TypeException {
		IType lhsType = entry.getType();

		IType rhsType = exp.typeCheck();

		if ((rhsType.isSubtypeOf(lhsType)) == false) {

			String msg = " Error during an assignment in a statement, the right-hand-side type isn't a subtype of left-hand-side ";

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, msg);

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);

		}

		return new VoidType();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> err = new ArrayList<>();

		try {

			this.entry = env.getId(this.ctx.ID().getSymbol());
			nl = env.getNestingLevel();

			

			err.addAll(exp.checkSemantics(env));

		}

		catch (NotDefinedVar e) {

			err.add(new SemanticError(e.msg()));

		}

		return err;
	}

	@Override
	public String codeGeneration() {
		String getAR = "";
		for (int i = 0; i < nl - entry.getNestinglevel(); i++)
			getAR += "lw\n";
		return exp.codeGeneration() + "push " + entry.getOffset() + "\n" + // metto offset sullo stack
				"lfp\n" + getAR + // risalgo la catena statica
				"add\n" + "sw\n"; // carico sullo stack il valore all'indirizzo ottenuto
	}

}
