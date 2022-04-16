/*
 * Classe usate per gestire la chiamata di funzione
 */
package ast;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import exception.ForbiddenCallFunction;
import exception.NotDefinedFunction;
import exception.TypeException;
import parser.FOOLParser;
import parser.FOOLParser.FuncallexpContext;
import type.FunctionType;
import type.IType;
import util.Environment;
import util.SemanticError;
import util.TypeErrorMsg;

public class CallNode implements Node {

	private Token token;

	private ArrayList<Node> actualparam;// parametri attuali passati alla funzione

	private FuncallexpContext ctx;

	private STentry entry; // valore di ritorno da geFunId

	private int nl; // actual nesting level useful for code generation

	public CallNode(Token token, ArrayList<Node> actualparam, FuncallexpContext ctx) {

		this.token = token;

		this.actualparam = actualparam;

		this.ctx = ctx;

		this.entry = null;
	}

	@Override
	public IType typeCheck() throws TypeException {

		if ((entry.getType() instanceof FunctionType) == false) {
			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx,
					"call to an object " + token.getText() + " that is not a function ");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);
		}

		else {

			FunctionType fType = (FunctionType) entry.getType();

			for (Node n : actualparam) {
				n.typeCheck();
			}

			if (actualparam.size() != fType.getListArgType().size()) {
				TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, " Function called with wrong number of parameters");

				String TypeMsg = typerr.getTypeErrorMsg();
				throw new TypeException(TypeMsg);
			}
			try {
				for (int i = 0; i < actualparam.size(); i++) {

					Node nod = actualparam.get(i);

					if (nod.typeCheck().isSubtypeOf(fType.getListArgType().get(i)) == false) {

						TypeErrorMsg typerr = new TypeErrorMsg(this.ctx,
								"Argument " + (i + 1) + " has an incorrect type.");

						String TypeMsg = typerr.getTypeErrorMsg();

						throw new TypeException(TypeMsg);

					}

				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.exit(0);

			}

			return fType.getReturnType();

		}

	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();

		try {
			if (insideMethos(ctx)) {
				throw new ForbiddenCallFunction(token);
			}
			entry = env.getFunInd(token);

		}

		catch (NotDefinedFunction | ForbiddenCallFunction e) {
			err.add(new SemanticError(e.msg()));
		}

		nl = env.getNestingLevel();// for code gen
		/*
		 * controlla eventuali errori semantici nei parametri attuali passati alla
		 * funzione
		 */
		for (Node n : actualparam) {

			err.addAll(n.checkSemantics(env));
		}

		return err;
	}

	@Override
	public String codeGeneration() {

		/*
		 * nella striga codeActualPar viene aggiunto il codice per i parametri attuali
		 * passati alla funzione
		 */
		String codeActualPar = "";

		for (int i = actualparam.size() - 1; i >= 0; i--) {
			codeActualPar = codeActualPar.concat(actualparam.get(i).codeGeneration());
		}

		String getAR = "";
		// System.out.println("nl : "+nl+" entry.getNestinglevel
		// "+entry.getNestinglevel() );
		for (int i = 0; i < nl - entry.getNestinglevel() - 1; i++) {
			getAR = getAR.concat("lw\n");
		}

		return "lfp\n" + // CL
				codeActualPar + "lfp\n" + getAR + // setto AL risalendo la catena statica
				// ora recupero l'indirizzo a cui saltare e lo metto sullo stack
				"push " + entry.getOffset() + "\n" + // metto offset sullo stack
				"lfp\n" + getAR + // risalgo la catena statica
				"add\n" + "lw\n" + // carico sullo stack il valore all'indirizzo ottenuto
				"js\n";
	}

	private boolean insideMethos(FuncallexpContext ctx) {

		boolean ris = false;
		ParserRuleContext index = ctx;

		while (!ris && index != null) {

			if (FOOLParser.ruleNames[index.getRuleIndex()].equals(FOOLParser.ruleNames[FOOLParser.RULE_classdef]))
				ris = true;

			else
				index = index.getParent();
		}

		return ris;

	}

}
