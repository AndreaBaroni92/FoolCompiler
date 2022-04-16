package ast;

import java.util.ArrayList;

import exception.NotDefinedClass;
import exception.TypeException;
import parser.FOOLParser.ObjinstContext;
import type.ClassType;
import type.IType;
import util.ClassField;
import util.Environment;
import util.SemanticError;
import util.TypeErrorMsg;

public class NewNode implements Node {

	private ArrayList<Node> actualParam;
	private String nameOfClass;
	private ObjinstContext ctx;
	private ClassType ct;

	public NewNode(ArrayList<Node> actualParam, String nameOfClass, ObjinstContext ctx) {
		setActualParam(actualParam);
		setNameOfClass(nameOfClass);
		setCtx(ctx);
	}

	@Override
	public IType typeCheck() throws TypeException {

		for (Node n : actualParam) {
			n.typeCheck();
		}

		ArrayList<ClassField> ordered = ct.getOrderedFields();

		for (int i = 0; i < actualParam.size(); i++) {

			Node nod = actualParam.get(i);

			if (nod.typeCheck().isSubtypeOf(ordered.get(i).getType()) == false) {
				TypeErrorMsg typerr = new TypeErrorMsg(this.ctx, "Argument " + (i + 1) + " has an incorrect type.");

				String TypeMsg = typerr.getTypeErrorMsg();

				throw new TypeException(TypeMsg);
			}

		}

		return ct;
	}

	@Override
	public String codeGeneration() {
		String ris = "";
		String toAppend = "";
		// generazione codice parametri attuali
		for (Node n : actualParam) {
			ris = ris.concat(n.codeGeneration());
		}

		toAppend = "push " + Integer.toString(actualParam.size()) + "\n" +//numero dei parametri
				"push class" + nameOfClass + "\n" + 
				"new" + "\n";
		;

		return ris.concat(toAppend);
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> err = new ArrayList<>();

		/*
		 * viene recuperata la classe e eventualmente viene sollevata un eccezzzione nel
		 * caso la classe non esista
		 */

		try {

			setCt(env.getClassByToken(ctx.nameClass));

		}

		catch (NotDefinedClass e) {
			err.add(new SemanticError(e.msg()));
			return err;
		}

		if (actualParam.size() != ct.getFields().size()) {
			int line = ctx.nameClass.getLine();
			int column = ctx.nameClass.getCharPositionInLine();
			String msg = "line :" + String.valueOf(line) + " column: " + String.valueOf(column)
					+ " incorrect numbers of actual parameters for instantiate the class with name: "
					+ getNameOfClass();

			err.add(new SemanticError(msg));
		}

		else {

			for (Node n : getActualParam()) {
				err.addAll(n.checkSemantics(env));
			}

		}

		return err;
	}

	public ClassType getCt() {
		return ct;
	}

	public void setCt(ClassType ct) {
		this.ct = ct;
	}

	public ArrayList<Node> getActualParam() {
		return actualParam;
	}

	public void setActualParam(ArrayList<Node> actualParam) {
		this.actualParam = actualParam;
	}

	public String getNameOfClass() {
		return nameOfClass;
	}

	public void setNameOfClass(String nameOfClass) {
		this.nameOfClass = nameOfClass;
	}

	public ObjinstContext getCtx() {
		return ctx;
	}

	public void setCtx(ObjinstContext ctx) {
		this.ctx = ctx;
	}

}
