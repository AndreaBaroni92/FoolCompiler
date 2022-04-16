package ast;

import java.util.ArrayList;

import org.antlr.v4.runtime.Token;

import exception.NotDefinedClass;
import exception.TypeException;
import parser.FOOLParser.TypeContext;
import type.BoolType;
import type.ClassType;
import type.IType;
import type.IntType;
import type.VoidType;
import util.Environment;
import util.SemanticError;

//TODO aggiornare in quanto ora si potrebbe avere il tipo di dato classe oltre a int void e bool
public class TypeNode implements Node {

	private String RealType;
	private IType type;
	private Token tok;

	public TypeNode(String TypeToCheck, TypeContext ctx) {

		this.RealType = TypeToCheck;

		if (RealType.equals("int")) {

			this.type = new IntType();

		}

		else if (RealType.equals("void")) {
			this.type = new VoidType();
		}

		else if (RealType.equals("bool")) {
			this.type = new BoolType();
		}

		else {
			// System.out.println( "Il tipo "+this.RealType +" non e' int o void o bool");
			// System.exit(0);
			// res.add(new SemanticError("Il tipo " + this.RealType + " non e' int o void o
			// bool"));

			this.type = new ClassType(this.RealType, ctx.nameofhypotheticalclass);
			this.setTok(ctx.nameofhypotheticalclass);

		}

	}

	public IType getType() {
		return this.type;
	}

	@Override
	public IType typeCheck() throws TypeException {

		return this.type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.Node#checkSemantics(util.Environment)
	 * 
	 * controllare il fatto che se si ha un tipo di dato di tipo classe la clase sia
	 * stata definita
	 */
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> err = new ArrayList<>();
		try {
			if (this.type instanceof ClassType) {
				this.type = env.getClassByToken(tok);
			}
		}

		catch (NotDefinedClass e) {
			err.add(new SemanticError(e.msg()));
		}

		return err;

	}

	@Override
	public String codeGeneration() {

		return "";
	}

	public Token getTok() {
		return tok;
	}

	public void setTok(Token tok) {
		this.tok = tok;
	}

}
