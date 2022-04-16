package type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.Token;

import util.ClassField;
import util.ClassMethod;

public class ClassType implements IType {

	private String className;
	private ClassType parent;
	private Map<String, ClassField> fields;
	private Map<String, ClassMethod> methods;
	private Token classToken;

	private ArrayList<ClassField> orderedFields;

	public ArrayList<ClassField> getOrderedFields() {
		return orderedFields;
	}

	public void setOrderedFields(ArrayList<ClassField> orderedFields) {
		this.orderedFields = orderedFields;
	}

	public ClassType(String className, Token classToken) {

		this.className = className;
		this.parent = null;
		this.setFields(new HashMap<>());
		this.setMethods(new HashMap<>());
		this.classToken = classToken;
	}

	@Override
	public boolean isSubtypeOf(IType type) {

		if (type instanceof ClassType) {

			ClassType argType = (ClassType) type;

			if (this.getClassName().equals(argType.getClassName())) { // type e this sono la stessa classe
				return true;
			}

			if (this.parent != null) {// si controllano ricorsivamente tutti gli antenati di this
				return this.parent.getClassName().equals(argType.getClassName()) || this.parent.isSubtypeOf(type);
			}
		}

		return false;
	}

	public String getClassName() {
		return className;
	}

	public Map<String, ClassField> getFields() {
		return fields;
	}

	public void setFields(Map<String, ClassField> fields) {
		this.fields = fields;
	}

	public Map<String, ClassMethod> getMethods() {
		return methods;
	}

	public void setMethods(Map<String, ClassMethod> methods) {
		this.methods = methods;
	}

	public ClassType getParent() {
		return parent;
	}

	public void setParent(ClassType parent) {
		this.parent = parent;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return getClassName();
	}

	public Token getClassToken() {
		return classToken;
	}

	public void setClassToken(Token classToken) {
		this.classToken = classToken;
	}

}
