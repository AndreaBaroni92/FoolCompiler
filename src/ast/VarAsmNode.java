package ast;

import java.util.ArrayList;
import java.util.HashMap;

import exception.NotDefinedClass;
import exception.RedeclaredVar;
import exception.TypeException;
import parser.FOOLParser;
import type.ClassType;
import type.IType;
import type.IntType;
import type.VoidType;
import util.Environment;
import util.SemanticError;
import util.TypeErrorMsg;

public class VarAsmNode implements Node {

	private TypeNode type;// serve nella simble table e nel typecheck
	// private String VariableName;
	private Node exp;
	private FOOLParser.VarasmContext ctx;
	private Environment env;

	public VarAsmNode(TypeNode type, Node exp, FOOLParser.VarasmContext ctx) {
		this.type = type;
		// this.VariableName = VariableName;
		this.exp = exp;
		this.ctx = ctx;
	}

	@Override
	public IType typeCheck() throws TypeException {

		if (exp != null) {

			IType leftType = this.type.typeCheck();// tipo dichiarato
			IType rightType = this.exp.typeCheck();

			boolean test = rightType.isSubtypeOf(leftType);// il tipo di sinistra deve essere sottotipo del tipo di
															// destra

			if (test == false) {

				String msg = " Error during an assignment, the right-hand-side type isn't a subtype of left-hand-side ";

				TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, msg);

				String TypeMsg = typerr.getTypeErrorMsg();

				throw new TypeException(TypeMsg);

			}

		}

		return new VoidType();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> res = new ArrayList<>();

		res.addAll(this.type.checkSemantics(env));// bisogna controllare che il tipo effettivamente esista

		if (res.isEmpty() == false) {
			return res;
		}

		IType tipoassegnato = type.getType();
		
		if (ctx.exp() != null) {

			res.addAll(exp.checkSemantics(env));

		}
		try {
			
			
			env.addEntry(ctx.vardec().ID().getSymbol(), tipoassegnato, env.decOffset());
		
		}

		catch (RedeclaredVar| NotDefinedClass e) {
			res.add(new SemanticError(e.msg()));
		}

		return res;
	}

	@Override
	public String codeGeneration() {
		if (exp != null) {
			return exp.codeGeneration();
		} else {
			return "push 0" + "\n"; // valore di default in caso di variabile non inizializzata
		}

	}
	
	
	
	

}
