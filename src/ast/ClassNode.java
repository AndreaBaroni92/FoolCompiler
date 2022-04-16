package ast;

import java.util.ArrayList;
import java.util.HashMap;

import exception.RedeclaredField;
import exception.RedeclaredMethod;
import exception.TypeException;
import parser.FOOLParser.ClassdefContext;
import type.ClassType;
import type.FunctionType;
import type.IType;
import util.Environment;
import util.SemanticError;

public class ClassNode implements Node {

	private ClassType classType;
	private String parent;// nome della classe genitore
	private String name;// nome della classe(this)
	private ClassdefContext ctx;
	private ArrayList<FPNode> fields;
	private ArrayList<MethodNode> methods;
	private HashMap<String, String> idToLabel;

	public ClassNode(ClassType classType, String parent, ClassdefContext ctx, ArrayList<FPNode> fields,
			ArrayList<MethodNode> methods) {

		this.classType = classType;
		this.setParent(parent);
		this.setCtx(ctx);
		this.setFields(fields);
		this.setMethods(methods);
		this.setName(ctx.son.getText());

	}

	@Override
	public IType typeCheck() throws TypeException {

		/*
		 * per tutti i metodi della classe faccio un controllo dei tipi
		 */
		for (MethodNode m : methods) {
			m.typeCheck();
		}

		return classType;
	}

	@Override
	public String codeGeneration() {
		
		for (MethodNode m : methods) {
			m.codeGeneration();
		}
		
		return "";
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> err = new ArrayList<>();

		try {

			env.addHashTable();// viene aggiunta una nuova hash table in quanto si entra in un nuovo scope

			env.setOffset(1);

			/*
			 * viene eseguita l'analisi semantica per quanto riguarda i campi della classe
			 */
			for (FPNode n : fields) {
				err.addAll(n.checkSemantics(env));
			}

			/*
			 * per supportare la mutua ricorsione fra i metodi , in analogia con quanto
			 * fatto con le funzioni attraverso l'uso di un visitor di nome
			 * DetectFunctionVisitor recupero tutti i metodi poi si fara l'analisi semantica
			 * su tutti i metodi.
			 */
			for (MethodNode m : methods) {

				ArrayList<IType> parametertypes = new ArrayList<>();

				m.setClasOwner(classType);

				for (Node n : m.getFormalArguments()) {

					FPNode fp = (FPNode) n;

					parametertypes.add(fp.getType());

				}

				FunctionType ft = new FunctionType(m.getRetType(), parametertypes);

				env.addEntry(m.getCtx().nameFun, ft, env.incOffset(), true, name);

			}

			for (MethodNode m : methods) {
				err.addAll(m.checkSemantics(env));
			}

			env.removeLastHashMap();

		}

		catch (RedeclaredMethod | RedeclaredField e) {
			err.add(new SemanticError(e.msg()));
		}

		return err;
	}

	public ClassType getClassType() {
		return classType;
	}

	public ClassdefContext getCtx() {
		return ctx;
	}

	public void setCtx(ClassdefContext ctx) {
		this.ctx = ctx;
	}

	public ArrayList<FPNode> getFields() {
		return fields;
	}

	public void setFields(ArrayList<FPNode> fields) {
		this.fields = fields;
	}

	public ArrayList<MethodNode> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<MethodNode> methods) {
		this.methods = methods;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getIdToLabel() {
		return idToLabel;
	}

	public void setIdToLabel(HashMap<String, String> idToLabel) {
		this.idToLabel = idToLabel;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

}
