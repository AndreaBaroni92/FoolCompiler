package ast;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;

import exception.NonExixtentObject;
import exception.NotDefinedMethod;
import exception.NotDefinedVar;
import exception.TypeException;
import parser.FOOLParser;
import parser.FOOLParser.MetcallContext;
import type.ClassType;
import type.FunctionType;
import type.IType;
import util.ClassMethod;
import util.Environment;
import util.SemanticError;
import util.TypeErrorMsg;

public class MethodCallNode implements Node {

	private boolean insideClass;

	private MetcallContext ctx;

	private ArrayList<Node> actualParam;

	private FunctionType metType;

	private int nl;

	private int objOff;

	private int objNl;

	private int metOff;

	private String ObjName;
	private String MethodName;

	public MethodCallNode(MetcallContext ctx, ArrayList<Node> actualParam) {
		setInsideClass(false);
		setObjName(ctx.objectName.getText());
		setMethodName(ctx.methodName.getText());
		setCtx(ctx);
		setActualParam(actualParam);
		setMetOff(0);

	}

	@Override
	public IType typeCheck() throws TypeException {

		for (Node n : actualParam) { // controllo sui parametri attuali
			n.typeCheck();
		}

		if (actualParam.size() != metType.getListArgType().size()) {
			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, " Method called with wrong number of parameters");

			String TypeMsg = typerr.getTypeErrorMsg();
			throw new TypeException(TypeMsg);
		}

		for (int i = 0; i < actualParam.size(); i++) {

			Node nod = actualParam.get(i);

			if (nod.typeCheck().isSubtypeOf(metType.getListArgType().get(i)) == false) {

				TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, "Argument " + (i + 1) + " has an incorrect type.");

				String TypeMsg = typerr.getTypeErrorMsg();

				throw new TypeException(TypeMsg);

			}

		}

		return metType.getReturnType();
	}

	@Override
	public String codeGeneration() {

		String actualParamcode = "";

		for (int i = actualParam.size() - 1; i >= 0; i--) {
			actualParamcode = actualParamcode.concat(actualParam.get(i).codeGeneration());
		}

		String getAr = "";

		int bias = this.insideMethos(ctx) ? 1 : 0;

		for (int i = 0; i < nl + bias - objNl; i++) {
			getAr = getAr.concat("lw\n");
		}

		String str = ""; // questa stringa serve per mettere sullo stack l'indirizzo dell'oggetto nello
							// heap su cui si vuole chiamare il metodo

		str = "push " + objOff + "\n" 
		+ "lfp\n" + getAr 
		+ "add\n" 
		+ "lw\n";
		
		

		return "lfp\n"
				+actualParamcode
				+str
				+str
				+"lw\n"
				+"push "+metOff+"\n"
				+"add\n"
				+"lam\n"
				+"js\n"
				;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();

		nl = env.getNestingLevel();

		try {
			ClassType classType = null;
			if (ObjName.equals("this")) {
			
				STentry entry = env.getId(ctx.objectName);

				IType objectType = entry.getType(); // guardo di che tipo e'

				if ((objectType instanceof ClassType) == false) {
					throw new NonExixtentObject(ctx.objectName);
				}

				else {
					ClassType ct = (ClassType) objectType;
					classType = env.getClassByToken(ct.getClassToken());
					objOff=0;
					objNl=3;
					
				}

			}

			else {

				STentry entry = env.getId(ctx.objectName);// controllo che l'oggetto esista

				IType objectType = entry.getType(); // guardo di che tipo e'
				objOff = entry.getOffset();
				objNl = entry.getNestinglevel();

				STentry e = env.getSymTable().get(env.getSymTable().size() - 1).get("this");

				if (e != null) {
					nl--;
				}

				if ((objectType instanceof ClassType) == false) {
					throw new NonExixtentObject(ctx.objectName);
				}

				else {
					ClassType ct = (ClassType) objectType;
					classType = env.getClassByToken(ct.getClassToken());
				}

			}

			setMetOff(env.getMethodOffset(classType.getClassName(), getMethodName()));

			ClassMethod cm = classType.getMethods().get(MethodName);

			if (cm == null) {// il metodo non e' stato trovato

				throw new NotDefinedMethod(ctx.objectName, MethodName);

			}

			else {// il metodo e' stato trovato
				metType = cm.getType();
			}

			for (Node n : actualParam) {
				err.addAll(n.checkSemantics(env));

			}

		}

		catch (NonExixtentObject | NotDefinedMethod | NotDefinedVar e) {
			err.add(new SemanticError(e.msg()));
		}
		return err;
	}

	private boolean insideMethos(MetcallContext ctx) {

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

	public boolean isInsideClass() {
		return insideClass;
	}

	public void setInsideClass(boolean insideClass) {
		this.insideClass = insideClass;
	}

	public MetcallContext getCtx() {
		return ctx;
	}

	public void setCtx(MetcallContext ctx) {
		this.ctx = ctx;
	}

	public ArrayList<Node> getActualParam() {
		return actualParam;
	}

	public void setActualParam(ArrayList<Node> actualParam) {
		this.actualParam = actualParam;
	}

	public String getObjName() {
		return ObjName;
	}

	public void setObjName(String objName) {
		ObjName = objName;
	}

	public String getMethodName() {
		return MethodName;
	}

	public void setMethodName(String methodName) {
		MethodName = methodName;
	}

	public FunctionType getMetType() {
		return metType;
	}

	public void setMetType(FunctionType metType) {
		this.metType = metType;
	}

	public int getNl() {
		return nl;
	}

	public void setNl(int nl) {
		this.nl = nl;
	}

	public int getObjOff() {
		return objOff;
	}

	public void setObjOff(int objOff) {
		this.objOff = objOff;
	}

	public int getObjNl() {
		return objNl;
	}

	public void setObjNl(int objNl) {
		this.objNl = objNl;
	}

	public int getMetOff() {
		return metOff;
	}

	public void setMetOff(int metOff) {
		this.metOff = metOff;
	}

}
