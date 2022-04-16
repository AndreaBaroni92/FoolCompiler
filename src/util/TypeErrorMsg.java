package util;

import org.antlr.v4.runtime.ParserRuleContext;

public class TypeErrorMsg {

	private ParserRuleContext ctx;

	private String msg;

	public TypeErrorMsg(ParserRuleContext ctx, String msg) {
		this.ctx = ctx;
		this.msg = msg;
	}

	public String getTypeErrorMsg() {
		String line = String.valueOf(ctx.start.getLine());
		String column = String.valueOf(ctx.start.getCharPositionInLine());

		String ris = "Type Error: \" " + this.msg + "\" at line: " + line + " at column: " + column + "\n";
		return ris;
	}

}
