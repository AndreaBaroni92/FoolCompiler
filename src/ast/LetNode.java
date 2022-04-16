package ast;

import java.util.ArrayList;

import exception.TypeException;
import type.IType;
import type.VoidType;
import util.Environment;
import util.SemanticError;

public class LetNode implements Node {

	private ArrayList<Node> declist;

	public LetNode(ArrayList<Node> dec) {

		this.declist = dec;

	}

	@Override
	public IType typeCheck() throws TypeException {

		for (Node n : declist) {// scorre tutta la lista delle dichiarazioni in cerca di eventuali errori
			n.typeCheck();
		}

		return new VoidType(); // se non ha trovato errori restituisce void
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> errors = new ArrayList<SemanticError>();

		for (Node dc : declist) {

			errors.addAll(dc.checkSemantics(env));

		}

		return errors;
	}

	@Override
	public String codeGeneration() {
		String dlc = "push 0" + "\n";
		for (Node n : declist) {
			dlc = dlc.concat(n.codeGeneration());
		}
		return dlc;
	}

}
