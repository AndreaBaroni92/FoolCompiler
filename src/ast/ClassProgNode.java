package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import exception.FieldForbiddenOverriding;
import exception.LackField;
import exception.NotDefinedClass;
import exception.RedeclaredClass;
import exception.RedeclaredMethod;
import exception.TypeException;
import exception.WrongOrderOfFields;
import type.ClassType;
import type.IType;
import type.VoidType;
import util.ClassField;
import util.ClassMethod;
import util.DispatchTableEntry;
import util.Environment;
import util.FOOLlib;
import util.SemanticError;

public class ClassProgNode implements Node {

	private ArrayList<ClassNode> listOfClass; // lista delle classi

	private Node letPart; // lista delle dichiarazioni di variabile e funzioni

	private Node exp; // eventuale espressione dopo let

	private ArrayList<Node> stm; // eventuali statements dopo let

	

	private Environment env;// usato nella code generation

	public ClassProgNode(ArrayList<ClassNode> listOfClass, Node letPart, Node exp, ArrayList<Node> stm) {

		this.listOfClass = listOfClass;
		this.letPart = letPart;
		this.exp = exp;
		this.stm = stm;
		
	}

	@Override
	public IType typeCheck() throws TypeException {

		for (ClassNode c : listOfClass) {
			c.typeCheck();
		}

		if (letPart != null) {
			letPart.typeCheck();
		}

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
	public String codeGeneration() {

		for (ClassNode c : listOfClass) {
			c.codeGeneration();
		}

		String code = "";

		if (letPart != null) {

			code = code.concat(letPart.codeGeneration());

		}

		if (exp != null) {// o ci sono le espressioni
			code = code.concat(exp.codeGeneration());
		}

		else {// o gli statements

			for (Node n : stm) {
				code = code.concat(n.codeGeneration());
			}

		}

		return code.concat("halt\n").concat(env.generateFunctionAndDispatchTablesCode());
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		ArrayList<SemanticError> err = new ArrayList<>();

		try {
			for (ClassNode cn : listOfClass) {

				ClassType ct = cn.getClassType();

				/*
				 * recupero i campi, la classe FPNode ha un campo di nome isField per indicare
				 * che non e' un parametro formale di una funzione, ma un campo di una classe.
				 */

				HashMap<String, ClassField> fields = new HashMap<>();

				for (FPNode fpn : cn.getFields()) {

					// System.out.println("tipo campo "+fpn.getType().toString());

					fields.put(fpn.getName(), new ClassField(fpn.getName(), fpn.getType(), fpn.getCtx()));

				}

				ct.setFields(fields);

				/*
				 * recupero i metodi
				 */

				HashMap<String, ClassMethod> methods = new HashMap<>();

				for (MethodNode mn : cn.getMethods()) {

					methods.put(mn.getName(), new ClassMethod(mn.getName(), mn.getType()));

				}

				ct.setMethods(methods);

				if (cn.getParent() == null) {
					/*
					 * aggiunge la classe corrente nella lista delle classi presenti, nel caso
					 * lancia un' eccezzione se si verificasse il fatto che un' altra classe con lo
					 * stesso nome sia gia' presente
					 */
					env.addClassType(cn.getCtx().son, ct);
					createDispatchTable(ct, new ArrayList<>(), cn.getMethods(), null, env);

				}

				else {
					

					ClassType parent = null;

					parent = env.getClassByToken(cn.getCtx().parent); // viene sollevata un' eccezzione nel caso la
																		// classe genitore non esista
					ct.setParent(parent);

					ClassType cp = ct.getParent();

					ArrayList<MethodNode> currentMethods = new ArrayList<>();

					currentMethods.addAll(cn.getMethods());

					ArrayList<MethodNode> overridingMethods = new ArrayList<>();

					/*
					 * viene controllato se nell'insieme dei campi della classe cn e' presente il
					 * campo recuperabile da parentField ( parentField.getNameField) ; parentField
					 * rappresenta un indice per scorrere tutti i campi della classe genitore.
					 * 
					 * cp rappresenta class parent
					 */
					for (ClassField parentField : cp.getFields().values()) {

						if (containsField(cn, parentField) == false) {
							throw new LackField(cn.getCtx().son, parentField.getNameField());
						}

						else {

							ClassField sonFied = ct.getFields().get(parentField.getNameField());

							/*
							 * l'overriding dei campi e' proibito , e qui si controlla questo fatto
							 */

							if (sonFied.getType().toString().equals(parentField.getType().toString()) == false) {

								throw new FieldForbiddenOverriding(cn.getCtx().son);

							}

						}

					}

					/*
					 * bisogna anche controllare che nella classe figlio il costruttore prenda in
					 * input i campi esattamente nell' ordine con il quale sono stati definiti nella
					 * classe genitore
					 */

					for (int i = 0; i < parent.getOrderedFields().size(); i++) {

						if ((parent.getOrderedFields().get(i).getNameField()
								.equals(cn.getFields().get(i).getName()) == false)) {

							throw new WrongOrderOfFields(cn.getCtx().son);

						}

					}

					/*
					 * vengono "filtrati" tutti i metodi della classe corrente in modo da recuperare
					 * quelli che sono stati riscritti( overriden), e questi ultimi verranno
					 * restituiti come output e tenere sono quelli che invece sono stati definiti ex
					 * novo in currentMethods .
					 */
					overridingMethods.addAll(getOverridingMethodsAndRemoveFromInput(ct, parent, currentMethods));

					ArrayList<String> overridingMethodToStringList = new ArrayList<>();

					for (MethodNode mn : overridingMethods) {

						overridingMethodToStringList.add(mn.getName());

					}

					/*
					 * nella classe figlio vengono inseriti tutti i metodi della classe genitore che
					 * non sono stati sovrascritti ( overriding).
					 */

					for (ClassMethod cm : parent.getMethods().values()) {

						if (overridingMethodToStringList.contains(cm.getMethod()) == false) {

							methods.put(cm.getMethod(), cm);
						}

					}

					ct.setMethods(methods);

					env.addClassType(cn.getCtx().son, cn.getCtx().parent, ct);

					createDispatchTable(ct, overridingMethods, currentMethods, parent, env);

					
				}

				/*
				 * per fare si che un'ipotetica classe figlio istanzi i campi di una classe
				 * genitore nell'ordine esatto , la classe figlio deve sapere esattamente questo
				 * ordine ed e' per questo che i campi devono essere salvati in una struttura in
				 * modo ordinato
				 */
				ArrayList<ClassField> straightField = new ArrayList<>();

				for (FPNode cf : cn.getFields()) {
					straightField.add(new ClassField(cf.getName(), cf.getType(), cf.getCtx()));
				}

				ct.setOrderedFields(straightField);

			}

		}

		catch (RedeclaredClass | FieldForbiddenOverriding | LackField | NotDefinedClass | RedeclaredMethod e) {
			err.add(new SemanticError(e.msg()));
		}

		if (err.isEmpty() == false) {
			return err;
		}

		/*
		 * creata la dispatch table si passa a fale l'analisi semantica delle classi e
		 * dei campi e metodi di ogni classe , e della parte let in analogia a quanto
		 * fatto nella classe ProgLetInNode
		 */

		for (ClassNode c : listOfClass) {
			err.addAll(c.checkSemantics(env));
		}

		if (err.isEmpty() == false) {
			return err;
		}

		env.addHashTable();

		env.setOffset(-2);

		// env.offset = -2;

		err.addAll(letPart.checkSemantics(env));

		if (exp != null) {

			err.addAll(exp.checkSemantics(env));

		} else {// se non c'e' un espressione da definizione di grammatica devono essere
				// presenti degli statements

			for (Node s : stm) {
				err.addAll(s.checkSemantics(env));
			}

		}

		// exiting the scope
		env.removeLastHashMap();
		this.env = env;
		return err;
	}

	private void createDispatchTable(ClassType currentClass, ArrayList<MethodNode> overriddenMethods,
			ArrayList<MethodNode> currentMethods, ClassType parent, Environment env) throws RedeclaredMethod {

		ArrayList<String> listOfMethods = new ArrayList<>(); // per controllare che non ci siano piu' metodi con lo
																// stesso nome

		ArrayList<DispatchTableEntry> dtable = new ArrayList<>(); // lista di Dispatch table entry, che contiene il nome
																	// e la label per ogni metodo di una specifica
																	// classe

		String nameOfCurrentClass = currentClass.getClassName();

		if (parent == null) {

			for (MethodNode mn : currentMethods) {

				if (listOfMethods.contains(mn.getName())) {// e' gia presente un metodo con lo stesso nome l'overloading
															// non viene supportato

					throw new RedeclaredMethod(mn.getMethodToken(), nameOfCurrentClass);

				}

				else {
					listOfMethods.add(mn.getName());
				}

				String label = FOOLlib.freshFunLabel();
				DispatchTableEntry e = new DispatchTableEntry(mn.getName(), label, mn.getCtx());
				dtable.add(e);
				mn.setLabel(label);
				

			}

		}

		else {// questo ramo tratta il caso in cui una classe abbia un genitore

			ArrayList<String> overridenMethodToString = new ArrayList<>();

			for (MethodNode mn : overriddenMethods) {

				overridenMethodToString.add(mn.getName());

			}

			String parentClassName = parent.getClassName();

			ArrayList<DispatchTableEntry> dispatchTableOfParent = env.getDispatchTableClassByName(parentClassName);

			for (DispatchTableEntry de : dispatchTableOfParent) {

				/*
				 * qui consideriamo i meodi del genitore che non sono stati sovrascritti
				 */
				if (overridenMethodToString.contains(de.getMethodID()) == false) {

					if (listOfMethods.contains(de.getMethodID())) {

						throw new RedeclaredMethod(de.getCtx().nameFun, nameOfCurrentClass);
					}

					else {

						listOfMethods.add(de.getMethodID());
					}

					dtable.add(de);

				}

				/*
				 * qui invece consideriamo i metodi (overriding) che la classe figlio riscrive
				 */
				else {

					if (listOfMethods.contains(de.getMethodID())) {

						throw new RedeclaredMethod(de.getCtx().nameFun, nameOfCurrentClass);
					}

					else {

						listOfMethods.add(de.getMethodID());
					}

					int cursor = overridenMethodToString.indexOf(de.getMethodID());
					MethodNode mn = overriddenMethods.get(cursor);

					String label = FOOLlib.freshFunLabel();

					DispatchTableEntry newDispEntry = new DispatchTableEntry(mn.getName(), label, mn.getCtx());
					dtable.add(newDispEntry);
					mn.setLabel(label);
					

				}

			}

			/*
			 * Qui vengono considerati i membri definiti ex novo nella classe
			 */

			for (MethodNode m : currentMethods) {

				if (listOfMethods.contains(m.getName())) {
					// qui possono succedere due casi: 1) una classe riscrive in modo errato una
					// classe genitore o 2) l'insieme dei nuovi metodi definiti nella classe
					// contiene un duplicato
					throw new RedeclaredMethod(m.getCtx().nameFun, nameOfCurrentClass);
				}

				else {
					listOfMethods.add(m.getName());
				}

				String label = FOOLlib.freshFunLabel();
				DispatchTableEntry newDispEntry = new DispatchTableEntry(m.getName(), label, m.getCtx());

				dtable.add(newDispEntry);
				m.setLabel(label);
				
			}

		}

		env.addToDispatchTablesClass(nameOfCurrentClass, dtable);

	}

	/*
	 * restituisce true se la classe cn contien un campo rappresentato da pm
	 */
	private boolean containsField(ClassNode cn, ClassField pm) {

		for (FPNode f : cn.getFields()) {

			if (f.getName().equals(pm.getNameField())) {
				return true;
			}

		}

		return false;
	}

	public ArrayList<MethodNode> getOverridingMethodsAndRemoveFromInput(ClassType current, ClassType parent,
			ArrayList<MethodNode> input) {

		ArrayList<MethodNode> output = new ArrayList<>();

		ListIterator<MethodNode> index = input.listIterator();

		while (index.hasNext()) {

			MethodNode candidate = index.next();

			String methodName = candidate.getName();

			ClassMethod sonMet = current.getMethods().get(methodName);
			ClassMethod parentMet = parent.getMethods().get(methodName);

			if ((parentMet != null) && sonMet.getType().isSubtypeOf(parentMet.getType())) {

				output.add(candidate);

				index.remove();

			}

		}

		return output;

	}

}
