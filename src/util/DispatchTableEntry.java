package util;

import parser.FOOLParser.FunContext;

public class DispatchTableEntry {

	private String methodID;
	private String methodLabel;
	private FunContext ctx;

	public DispatchTableEntry(String methodID, String methodLabel, FunContext ctx) {
		this.methodID = methodID;
		this.methodLabel = methodLabel;
		this.setCtx(ctx);
	}

	public String getMethodID() {
		return methodID;
	}

	public String getMethodLabel() {
		return methodLabel;
	}

	public FunContext getCtx() {
		return ctx;
	}

	public void setCtx(FunContext ctx) {
		this.ctx = ctx;
	}

	public void setMethodID(String methodID) {
		this.methodID = methodID;
	}

	public void setMethodLabel(String methodLabel) {
		this.methodLabel = methodLabel;
	}

}
