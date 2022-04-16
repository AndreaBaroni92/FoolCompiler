package ast;

import java.util.ArrayList;

import exception.TypeException;
import type.IType;
import type.VoidType;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;

public class ProgLetInNode implements Node {

	// LetNode
	private Node letPart;
	// according to the grammar we can either have exp or stast
	private Node exp;

	private ArrayList<Node> stm;

	public ProgLetInNode(Node letPart, Node exp, ArrayList<Node> stm) {

		this.letPart = letPart;

		this.exp = exp;

		this.stm = stm;

	}

	@Override
	public IType typeCheck() throws TypeException {

		letPart.typeCheck();

		if (exp != null) {

			return exp.typeCheck();
		} else {
			for (Node s : stm) {
				s.typeCheck();
			}
			return new VoidType();
		}

	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) { // TODO CONTROLLARE BENE!!!!!

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		env.addHashTable();

		env.setOffset(-2);

		// env.offset = -2;

		res.addAll(letPart.checkSemantics(env));
		// TODO in seguito ci saranno anche gli statement da controllare
		if (exp != null) {

			res.addAll(exp.checkSemantics(env));

		} else {// se non c'e' un espressione da definizione di grammatica devono essere
				// presenti degli statements

			for (Node s : stm) {
				res.addAll(s.checkSemantics(env));
			}

		}

		// exiting the scope
		env.removeLastHashMap();
		return res;

	}

	@Override
	public String codeGeneration() {
		String code = letPart.codeGeneration();

		if (exp != null)
			code += exp.codeGeneration() + "halt\n";
		else {
			StringBuilder statsCode = new StringBuilder();

			for (Node stat : stm) {
				statsCode.append(stat.codeGeneration());
			}

			code += statsCode.toString() + "halt\n";
		}

		return code + FOOLlib.getCode();
	}

}
