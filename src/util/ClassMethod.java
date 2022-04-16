package util;

import type.FunctionType;

public class ClassMethod {

	private String method;
	private FunctionType type;

	public ClassMethod(String method, FunctionType type) {
		this.method = method;
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public FunctionType getType() {
		return type;
	}

	public void setType(FunctionType type) {
		this.type = type;
	}

}
