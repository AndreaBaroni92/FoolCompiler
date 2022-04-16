package ast;

import java.util.ArrayList;

import exception.TypeException;
import parser.FOOLParser.FunContext;
import type.FunctionType;
import type.IType;
import type.VoidType;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;
import util.TypeErrorMsg;

public class FunctionNode implements Node {

	// private ArgsNode formalArguments;

	private String name;

	private ArrayList<Node> formalArguments = new ArrayList<>();// parametri formali

	private Node exp; // espressione all' interno del body della funzione
	// TODO trattare anche qui il caso degli statements (dovrei averlo fatto)

	private ArrayList<Node> statements = new ArrayList<>();

	private FunContext ctx;

	private ArrayList<Node> declaration;// lista di dichiarazioni all'interno del body

	private FunctionType ftype;// viene costruito nel metodo checkSemantics

	private IType retType; // tipo di ritorno della funzione dichiarato, nel metodo typecheck verra' poi
							// controllato se il tipo del body della funzione coinidera' con retType

	private TypeNode tn;

	public FunctionNode(FunctionNode f) {// questo costruttore serve quando nel visitor si visitano i metodi
		this.name = f.getCtx().nameFun.getText();
		this.formalArguments = f.formalArguments;
		this.exp = f.exp;
		this.statements = f.statements;
		this.ctx = f.ctx;
		this.declaration = f.declaration;
		ftype = null;
		retType = f.tn.getType();// verra' poi controllato nel metodo checksemantic se esiste tale metodo( nel
									// caso sia una classe)
		this.tn = f.tn;
	}

	public FunctionNode(ArrayList<Node> formalArguments, Node exp, ArrayList<Node> statements, FunContext ctx,
			ArrayList<Node> declaration, TypeNode tn) {
		this.formalArguments = formalArguments;
		this.exp = exp;
		this.statements = statements;
		this.ctx = ctx;
		this.declaration = declaration;
		ftype = null;
		// this.retType = retType;
		this.retType = tn.getType();
		this.tn = tn;
		this.name = ctx.nameFun.getText();
	}

	public String getName() {

		return name;

	}

	@Override
	public IType typeCheck() throws TypeException {

		ArrayList<IType> parametersType = new ArrayList<>();

		if ((this.declaration.isEmpty()) == false) {

			for (Node n : this.declaration) {
				n.typeCheck();
			}

		}

		if (exp != null) {

			IType bodyExpType = exp.typeCheck();

			if (bodyExpType.isSubtypeOf(retType) == false) {
				TypeErrorMsg typerr = new TypeErrorMsg(this.ctx,
						"the return type of function's body is incompatible with the declared return type");

				String TypeMsg = typerr.getTypeErrorMsg();

				throw new TypeException(TypeMsg);

			}

		}

		/*
		 * se non sono presenti espressioni ci devono essere degli statement come
		 * specificato da grammatica e in questo caso il tipo di ritorno della funzione
		 * deve per forza essere void, percio' controllo che il tipo di ritorno sia void
		 * ed inoltre si effettua un controllo di tipo per quanto riguarda gli
		 * statements
		 * 
		 */

		else {

			if (retType.isSubtypeOf(new VoidType()) == false) {
				TypeErrorMsg typerr = new TypeErrorMsg(this.ctx,
						"the return type of this function must be void because the body is composed of statements");

				String TypeMsg = typerr.getTypeErrorMsg();

				throw new TypeException(TypeMsg);

			}

			for (Node n : statements) {
				n.typeCheck();
			}

		}
		/*
		 * Aggiungo all'array i tipi dei parametri formali infatti la chiamata
		 * n.typecheck restituisce il tipo ( vedere il metodo typecheck in FPNode)
		 */
		for (Node n : formalArguments) {
			parametersType.add(n.typeCheck());
		}

		return new FunctionType(retType, parametersType);
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> err = new ArrayList<>();

		err.addAll(this.tn.checkSemantics(env));// il tipo deve esistere, ovvero, in questa riga si controlla che il
												// tipo di ritorno della funzione, se non e' di dipo int, void, bool ,
												// corrisponda a una classe che veramente esista

		if (err.isEmpty() == false) {
			return err;
		}

		this.retType = tn.getType();

		ArrayList<IType> formalParametersType = new ArrayList<>();

		try {

			for (Node ty : formalArguments) {

				formalParametersType.add(ty.typeCheck());
				/*
				 * viene richiamato il metodo typecheck nel nodo FPNode che resituisce il tipo
				 */

			}

		}

		catch (TypeException e) {

		}

		this.ftype = new FunctionType(retType, formalParametersType);

		env.addHashTable();

		int savedOff = env.getOffset();

		env.setOffset(1);

		// err.addAll(formalArguments.checkSemantics(env));

		for (Node n : formalArguments) {
			err.addAll(n.checkSemantics(env));
		}

		env.setOffset(-2);

		// Variabili locali
		for (Node dec : declaration) {
			err.addAll(dec.checkSemantics(env));
		}
		// Body della funzione

		if (exp != null) { // o ci sonoo le espressioni
			err.addAll(exp.checkSemantics(env));

		}

		else { // o ci sono gli statements
			for (Node s : statements) {
				err.addAll(s.checkSemantics(env));
			}
		}
		env.removeLastHashMap();

		env.setOffset(--savedOff); // TODO controllare che questo offset sia giusto

		return err;

	}

	public TypeNode getTn() {
		return tn;
	}

	public void setTn(TypeNode tn) {
		this.tn = tn;
	}

	@Override
	public String codeGeneration() {

		String declCode = "";
		String popDecl = "";
		String assemblyCode = "";
		if (declaration != null) {

			for (Node dec : declaration) {
				declCode += dec.codeGeneration();
				popDecl += "pop\n";
			}

		}

		String popParl = "";
		for (Node dec : formalArguments) {
			popParl += "pop\n";
		}
		String funl = FOOLlib.freshFunLabel();

		if (exp != null) {

			assemblyCode = funl + ":\n" + "cfp\n" + // setta $fp a $sp
					"lra\n" + // inserimento return address
					declCode + // inserimento dichiarazioni locali
					exp.codeGeneration() + "srv\n" + // pop del return value
					popDecl + "sra\n" + // pop del return address
					"pop\n" + // pop di AL
					popParl + "sfp\n" + // setto $fp a valore del CL
					"lrv\n" + // risultato della funzione sullo stack
					"lra\n" + "js\n"; // salta a $ra

		}

		else {

			String stmCode = "";

			for (Node n : statements) {
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

	public FunctionType getType() {

		ArrayList<IType> inputParam = new ArrayList<>();

		for (Node n : getFormalArguments()) {

			FPNode f = (FPNode) n;

			inputParam.add(f.getType());

		}

		return new FunctionType(retType, inputParam);
	}

	public FunContext getCtx() {
		return ctx;
	}

	public ArrayList<Node> getFormalArguments() {
		return formalArguments;
	}

	public Node getExp() {
		return exp;
	}

	public void setExp(Node exp) {
		this.exp = exp;
	}

	public ArrayList<Node> getStatements() {
		return statements;
	}

	public void setStatements(ArrayList<Node> statements) {
		this.statements = statements;
	}

	public ArrayList<Node> getDeclaration() {
		return declaration;
	}

	public void setDeclaration(ArrayList<Node> declaration) {
		this.declaration = declaration;
	}

	public FunctionType getFtype() {
		return ftype;
	}

	public void setFtype(FunctionType ftype) {
		this.ftype = ftype;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFormalArguments(ArrayList<Node> formalArguments) {
		this.formalArguments = formalArguments;
	}

	public void setCtx(FunContext ctx) {
		this.ctx = ctx;
	}

	public void setRetType(IType retType) {
		this.retType = retType;
	}

	public IType getRetType() {
		return retType;
	}

}
