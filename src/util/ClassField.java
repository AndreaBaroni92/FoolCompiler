package util;

import org.antlr.v4.runtime.ParserRuleContext;

import type.IType;

public class ClassField {

	private String nameField;
	private IType type;
	private ParserRuleContext ctx;

	public ClassField(String name, IType type, ParserRuleContext ctx) {

		this.nameField = name;
		this.type = type;
		this.ctx = ctx;

	}

	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

	public IType getType() {
		return type;
	}

	public void setType(IType type) {
		this.type = type;
	}

	public ParserRuleContext getCtx() {
		return ctx;
	}

	public void setCtx(ParserRuleContext ctx) {
		this.ctx = ctx;
	}

}
