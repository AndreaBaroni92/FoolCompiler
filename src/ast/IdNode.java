package ast;

import java.util.ArrayList;

import org.antlr.v4.runtime.Token;

import exception.NotDefinedVar;
import exception.TypeException;
import parser.FOOLParser.IdexpContext;
import type.IType;
import util.Environment;
import util.SemanticError;

public class IdNode implements Node {

	private IdexpContext ctx;

	private STentry entry = null;
	 private int nestinglevel;

	public IdNode(IdexpContext ctx) {

		this.ctx = ctx;

	}

	@Override
	public IType typeCheck() throws TypeException {

		return entry.getType();
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> res = new ArrayList<SemanticError>();

		try {

			Token ris = ctx.ID().getSymbol();

			this.entry = env.getId(ris);
			this.nestinglevel= env.getNestingLevel();

		}

		catch (NotDefinedVar e) {

			res.add(new SemanticError(e.msg()));

		}

		return res;
	}

	@Override
	public String codeGeneration() {
	      String getAR="";
		  for (int i=0; i<nestinglevel-entry.getNestinglevel(); i++) 
		    	 getAR+="lw\n";
		    return "push "+entry.getOffset()+"\n"+ //metto offset sullo stack
			       "lfp\n"+getAR+ //risalgo la catena statica
				   "add\n"+ 
	               "lw\n"; //carico sullo stack il valore all'indirizzo ottenuto
	}

}
