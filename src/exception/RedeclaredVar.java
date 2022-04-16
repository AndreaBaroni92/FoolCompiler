package exception;

import org.antlr.v4.runtime.Token;

public class RedeclaredVar extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedeclaredVar(Token e) {
		super(e);
	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + "  Variable " + this.getName() + " already defined";

		return ris;

	}

}
