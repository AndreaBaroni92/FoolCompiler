package exception;

import org.antlr.v4.runtime.Token;

public class NotDefinedVar extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotDefinedVar(Token input) {
		super(input);

	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + "  Variable " + this.getName() + " not defined";

		return ris;

	}

}
