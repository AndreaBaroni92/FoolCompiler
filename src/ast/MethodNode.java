package ast;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.Token;

import exception.TypeException;
import parser.FOOLParser.FunContext;
import type.ClassType;
import type.FunctionType;
import type.IType;
import type.VoidType;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;
import util.TypeErrorMsg;

public class MethodNode extends FunctionNode {

	private ClassType clasOwner;

	private Token methodToken;

	private String label;
	
	public MethodNode(ArrayList<Node> formalArguments, Node exp, ArrayList<Node> statements, FunContext ctx,
			ArrayList<Node> declaration, TypeNode tn) {
		super(formalArguments, exp, statements, ctx, declaration, tn);
		this.methodToken = ctx.nameFun;
	}

	public MethodNode(FunctionNode f) {
		super(f);
		this.methodToken = f.getCtx().nameFun;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ast.FunctionNode#typeCheck()
	 * 
	 * e' uguale al type check per le funzioni solo che i messaggi di errore sono
	 * diversi
	 */
	@Override
	public IType typeCheck() throws TypeException {
		ArrayList<IType> parametersType = new ArrayList<>();

		if ((this.getDeclaration().isEmpty()) == false) {

			for (Node n : this.getDeclaration()) {
				n.typeCheck();
			}

		}

		if (getExp() != null) {

			IType bodyExpType = getExp().typeCheck();

			if (bodyExpType.isSubtypeOf(getRetType()) == false) {
				TypeErrorMsg typerr = new TypeErrorMsg(this.getCtx(),
						"the return type of method's body is incompatible with the declared return type");

				String TypeMsg = typerr.getTypeErrorMsg();

				throw new TypeException(TypeMsg);

			}

		}

		/*
		 * se non sono presenti espressioni ci devono essere degli statement come
		 * specificato da grammatica e in questo caso il tipo di ritorno del metodo deve
		 * per forza essere void, percio' controllo che il tipo di ritorno sia void ed
		 * inoltre si effettua un controllo di tipo per quanto riguarda gli statements
		 * 
		 */

		else {

			if (getRetType().isSubtypeOf(new VoidType()) == false) {
				TypeErrorMsg typerr = new TypeErrorMsg(this.getCtx(),
						"the return type of this method must be void because the body is composed of statements");

				String TypeMsg = typerr.getTypeErrorMsg();

				throw new TypeException(TypeMsg);

			}

			for (Node n : getStatements()) {
				n.typeCheck();
			}

		}
		/*
		 * Aggiungo all'array i tipi dei parametri formali infatti la chiamata
		 * n.typecheck restituisce il tipo ( vedere il metodo typecheck in FPNode)
		 */
		for (Node n : getFormalArguments()) {
			parametersType.add(n.typeCheck());
		}

		return new FunctionType(getRetType(), parametersType);
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> err = new ArrayList<>();

		err.addAll(this.getTn().checkSemantics(env));// il tipo di ritorno deve esistere

		if (err.isEmpty() == false) {
			return err;
		}

		this.setRetType(getTn().getType());

		ArrayList<IType> formalParType = new ArrayList<>();

		for (Node n : getFormalArguments()) {

			FPNode fn = (FPNode) n;

			formalParType.add(fn.getType());

		}

		FunctionType functionType = new FunctionType(this.getRetType(), formalParType);

		this.setFtype(functionType);

		env.addHashTable();

		int savedOff = env.getOffset();

		env.setOffset(1);

		ArrayList<HashMap<String, STentry>> symTable = new ArrayList<>();

		symTable = env.getSymTable();

		int size = symTable.size() - 1;

		STentry newEntry = new STentry(env.getNestingLevel(), this.clasOwner, 0, true);

	//	System.out.println("!!! inserted this !!!");

		STentry check = symTable.get(size).put("this", newEntry);

		env.setSymTable(symTable);

		if (check != null) {

			err.add(new SemanticError(" Variable this already defined "));

		}

		// err.addAll(formalArguments.checkSemantics(env));

		for (Node n : getFormalArguments()) {
			err.addAll(n.checkSemantics(env));
		}

		env.setOffset(-2);

		// Variabili locali
		for (Node dec : getDeclaration()) {
			err.addAll(dec.checkSemantics(env));
		}
		// Body della funzione

		if (getExp() != null) { // o ci sonoo le espressioni
			err.addAll(getExp().checkSemantics(env));

		}

		else { // o ci sono gli statements
			for (Node s : getStatements()) {
				err.addAll(s.checkSemantics(env));
			}
		}
		env.removeLastHashMap();

		env.setOffset(--savedOff); // TODO controllare che questo offset sia giusto

		return err;

	}
	
	@Override
	public String codeGeneration() {
		

		String declCode = "";
		String popDecl = "";
		String assemblyCode = "";
		if (getDeclaration() != null) {

			for (Node dec : getDeclaration()) {
				declCode += dec.codeGeneration();
				popDecl += "pop\n";
			}

		}

		String popParl = "";
		for (Node dec : getFormalArguments()) {
			popParl += "pop\n";
		}
		String funl = getLabel();

		if (getExp() != null) {

			assemblyCode = funl + ":\n" + "cfp\n" + // setta $fp a $sp
					"lra\n" + // inserimento return address
					declCode + // inserimento dichiarazioni locali
					getExp().codeGeneration() + "srv\n" + // pop del return value
					popDecl + "sra\n" + // pop del return address
					"pop\n" + // pop di AL
					popParl + "sfp\n" + // setto $fp a valore del CL
					"lrv\n" + // risultato della funzione sullo stack
					"lra\n" + "js\n"; // salta a $ra

		}

		else {

			String stmCode = "";

			for (Node n : getStatements()) {
				stmCode = stmCode.concat(n.codeGeneration());
			}

			assemblyCode = funl + ":\n" + "cfp\n" + // setta $fp a $sp
					"lra\n" + // inserimento return address
					declCode + // inserimento dichiarazioni locali
					stmCode +
					// "srv\n"+ gli statement non mettono nulla sullo stack
					popDecl + "sra\n" + // pop del return address
					"pop\n" + // pop di AL
					popParl + "sfp\n" + // setto $fp a valore del CL
					// "lrv\n"+ gli statement non hanno bisogno di questa funzione
					"lra\n" + "js\n"; // salta a $ra

		}

		FOOLlib.putCode(assemblyCode);

		return "push " + funl + "\n";
		
		
	}

	public ClassType getClasOwner() {
		return clasOwner;
	}

	public void setClasOwner(ClassType clasOwner) {
		this.clasOwner = clasOwner;
	}

	public Token getMethodToken() {
		return methodToken;
	}

	public void setMethodToken(Token methodToken) {
		this.methodToken = methodToken;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
