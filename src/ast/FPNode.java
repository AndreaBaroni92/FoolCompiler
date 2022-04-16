package ast;

import java.util.ArrayList;

import exception.RedeclaredField;
import exception.RedeclaredVar;
import exception.TypeException;
import parser.FOOLParser.VardecContext;
import type.IType;
import util.Environment;
import util.SemanticError;

/* questa classe viene adoperata per i parametri formali */

public class FPNode implements Node {

	private VardecContext ctx;

	private TypeNode tn;

	private IType type;

	private boolean isField;

	private String name;

	public FPNode(VardecContext ctx, TypeNode tn) {
		this.ctx = ctx;

		this.tn = tn;

		isField = false;

		name = ctx.ID().getSymbol().getText();

		type = tn.getType();
	}

	public FPNode(VardecContext ctx, TypeNode tn, boolean isField) {
		this.ctx = ctx;

		this.tn = tn;

		this.isField = isField;

		name = ctx.ID().getSymbol().getText();

		type = tn.getType();
	}

	@Override
	public IType typeCheck() throws TypeException {

		return type;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> err = new ArrayList<>();

		err.addAll(this.tn.checkSemantics(env)); // viene qui controllato il fatto che se non si ha un tipo primitivo
													// quindi o int o void o bool, si deve controllare che il tipo
													// classe
													// corrisponda a una classe che e' stata definita altrimenti si ha
													// un errore

		if (err.isEmpty() == false) {
			return err;
		}

		type = tn.getType();

		try {

			/*
			 * l'offset per i parametri formali di una funzione viene aumentato
			 */
			// System.out.println(ctx.ID().getText());
			env.addEntry(ctx.ID().getSymbol(), type, env.incOffset(), isField);

		}
		/*
		 * RedeclaredField e' una sottoclasse di RedeclaredVar quindi viene posta prima
		 */
		catch (RedeclaredField e) {
			err.add(new SemanticError(e.msg()));
		}

		catch (RedeclaredVar e) {

			err.add(new SemanticError(e.msg()));

		}
		return err;
	}

	@Override
	public String codeGeneration() {

		return "";
	}

	public String getName() {
		return name;
	}

	public IType getType() {
		return type;
	}

	public VardecContext getCtx() {
		return ctx;
	}
}
