package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import org.antlr.v4.runtime.Token;

import ast.MethodNode;
import ast.STentry;
import exception.NotDefinedClass;
import exception.NotDefinedFunction;
import exception.NotDefinedVar;
import exception.RedeclaredClass;
import exception.RedeclaredField;
import exception.RedeclaredFunction;
import exception.RedeclaredMethod;
import exception.RedeclaredVar;
import type.ClassType;
import type.FunctionType;
import type.IType;
import type.IntType;

public class Environment {

	private int offset = 0;
	private ArrayList<HashMap<String, STentry>> symTable = new ArrayList<>();
	private HashMap<String, ClassType> symTableClass;
	private HashMap<String, ArrayList<DispatchTableEntry>> dispatchTablesClass;

	public HashMap<String, ArrayList<DispatchTableEntry>> getDispatchTablesClass() {
		return dispatchTablesClass;
	}

	public ArrayList<DispatchTableEntry> getDispatchTableClassByName(String name) {

		return getDispatchTablesClass().get(name);

	}

	public Environment(ArrayList<HashMap<String, STentry>> symTable) {

		this.offset = 0;
		this.symTable = symTable;

	}

	public Environment() {
		this.setSymTable(new ArrayList<>());
		this.setSymTableClass(new HashMap<>());
		this.setDispatchTablesClass(new HashMap<>());
	}

	public ClassType getClassByToken(Token tok) {

		ClassType t = getSymTableClass().get(tok.getText());

		if (t == null) {
			throw new NotDefinedClass(tok);
		}

		return t;

	}

	public void setDispatchTablesClass(HashMap<String, ArrayList<DispatchTableEntry>> dispatchTablesClass) {
		this.dispatchTablesClass = dispatchTablesClass;
	}

	public void addToDispatchTablesClass(String s, ArrayList<DispatchTableEntry> d) {
		/*
		 * qui non e' necessario controllare che l'inserimento all' interno della hash
		 * table non restituisca null in quanto la chiave s che rappresenta il nome
		 * della classe sara' sempre unica in quanto viene gia' controlloto che non ci
		 * siano classi con lo stesso nome nel metodo addClassType, che viene
		 * cronologicamente prima di addToDispatchTablesClass
		 * 
		 */
		dispatchTablesClass.put(s, d);

	}

	public int getNestingLevel() {

		return this.symTable.size() - 1;

	}

	public void setOffset(int offset) {

		this.offset = offset;

	}

	public int decOffset() {
		int ris = (this.offset)--;
		return ris;
	}

	public int incOffset() {

		int ris = (this.offset)++;
		return ris;
	}

	public Environment addEntry(Token NameToken, IType type, int off) {
		String id = NameToken.getText();
		STentry newEntry = new STentry(getNestingLevel(), type, off);

		STentry checkEntry = symTable.get(symTable.size() - 1).put(id, newEntry);

		if (checkEntry != null && (checkEntry.getType() instanceof FunctionType)) {

			throw new RedeclaredFunction(NameToken);
		}

		if (checkEntry != null) {
			throw new RedeclaredVar(NameToken);
		}

		return this;
	}

	public Environment addEntry(Token NameToken, IType type, int off, boolean isField) {
		String id = NameToken.getText();
		STentry newEntry = new STentry(getNestingLevel(), type, off, isField);

		STentry checkEntry = symTable.get(symTable.size() - 1).put(id, newEntry);

		if (checkEntry != null && (checkEntry.getType() instanceof FunctionType)) {

			throw new RedeclaredFunction(NameToken);
		}

		if (checkEntry != null) {

			if (isField) {

				throw new RedeclaredField(NameToken);

			} else {
				throw new RedeclaredVar(NameToken);
			}
		}

		return this;
	}

	public Environment addEntry(Token NameToken, IType type, int off, boolean isField, String name) {
		String id = NameToken.getText();
		STentry newEntry = new STentry(getNestingLevel(), type, off, isField);

		STentry checkEntry = symTable.get(symTable.size() - 1).put(id, newEntry);

		if (checkEntry != null && ((checkEntry.getType() instanceof FunctionType))) {

			if (isField) {

				throw new RedeclaredMethod(NameToken, name);

			} else {
				throw new RedeclaredFunction(NameToken);

			}
		}

		if (checkEntry != null) {

			if (isField) {

				throw new RedeclaredField(NameToken);

			} else {
				throw new RedeclaredVar(NameToken);
			}
		}

		return this;
	}

	public HashMap<String, ClassType> getSymTableClass() {
		return symTableClass;
	}

	public ArrayList<HashMap<String, STentry>> getSymTable() {
		return symTable;
	}

	public void setSymTable(ArrayList<HashMap<String, STentry>> symTable) {
		this.symTable = symTable;
	}

	public void setSymTableClass(HashMap<String, ClassType> symTableClass) {
		this.symTableClass = symTableClass;
	}

	public void addClassType(Token name, ClassType classType) throws RedeclaredClass {
		ClassType current = symTableClass.put(name.getText(), classType);

		if (current != null)
			throw new RedeclaredClass(name);
	}

	public void addClassType(Token name, Token classParent, ClassType classType)
			throws RedeclaredClass, NotDefinedClass {

		ClassType checkParent = getSymTableClass().get(classParent.getText());

		if (checkParent == null) {
			throw new NotDefinedClass(classParent);
		}

		ClassType current = symTableClass.put(name.getText(), classType);

		if (current != null)
			throw new RedeclaredClass(name);
	}

	public STentry getId(Token variable) { // per recuperare le variabili scorrendo a ritroso la simble table

		String name = variable.getText();

		ListIterator<HashMap<String, STentry>> li = this.symTable.listIterator(this.symTable.size());
		STentry entry = null;

		while (li.hasPrevious()) {
			HashMap<String, STentry> map = li.previous();

			/*
			 * la variabile e' definita e non e' una funzione
			 */
			if (((entry = map.get(name)) != null) && ((entry.getType() instanceof FunctionType) == false))
				break;
		}

		if ((entry == null) || (entry.getType() instanceof FunctionType)) {
			throw new NotDefinedVar(variable);
		}

		return entry;

	}

	public STentry getFunInd(Token token) throws NotDefinedFunction {
		String id = token.getText();
		ListIterator<HashMap<String, STentry>> li = this.symTable.listIterator(this.symTable.size());
		STentry entry = null;

		while (li.hasPrevious()) {
			HashMap<String, STentry> map = li.previous();

			if (((entry = map.get(id)) != null) && (entry.getType() instanceof FunctionType))
				break;
		}

		if (entry == null || !(entry.getType() instanceof FunctionType))
			throw new NotDefinedFunction(token);

		return entry;
	}
	/*
	 * public Environment addEntry(String id, IType type, int off) throws
	 * RedeclaredVar { //String id = variableNameToken.getText(); STentry newEntry =
	 * new STentry(getNestingLevel(), type, off); STentry checkEntry =
	 * symTable.get(symTable.size() - 1).put(id, newEntry); if (checkEntry != null)
	 * { // TODO: inserire l'eccezzione System.out.println("Variabile " +id+
	 * " già definita"); System.exit(0); }
	 * 
	 * return this; }
	 */

	public Environment addHashTable() {
		HashMap<String, STentry> hm = new HashMap<String, STentry>();
		this.symTable.add(hm);

		return this;
	}

	/**
	 * Pops the latest hashmap added
	 * 
	 * @return this - the currently modified Environment
	 */
	public Environment removeLastHashMap() {
		this.symTable.remove(this.symTable.size() - 1);
		return this;
	}

	public boolean isEmpty() {

		return (this.getNestingLevel() == (-1) ? true : false);

	}

	public int getOffset() {
		return offset;

	}

	public int getMethodOffset(String className, String methodName) {

		int ris = 0;
		for (int i = 0; i < this.getDispatchTableClassByName(className).size(); i++) {
			DispatchTableEntry entry = this.getDispatchTableClassByName(className).get(i);

			if (entry.getMethodID().equals(methodName)) {
				ris = i;
				break;
			}

		}

		return ris;

	}

	public String generateFunctionAndDispatchTablesCode() {

		String ris = "";
		ris = ris.concat(FOOLlib.getCode());

		for (Map.Entry<String, ArrayList<DispatchTableEntry>> dt : dispatchTablesClass.entrySet()) {

			String className = dt.getKey();

			ris = ris.concat("class").concat(className).concat(":\n");

			ArrayList<DispatchTableEntry> listOfDispatchTableEntries = dt.getValue();

			for (DispatchTableEntry e : listOfDispatchTableEntries) {
				ris = ris.concat(e.getMethodLabel()).concat("\n");
			}

		}

		return ris;
	}

}
