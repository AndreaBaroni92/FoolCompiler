package exception;

import org.antlr.v4.runtime.Token;

public class NotDefinedFunction extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotDefinedFunction(Token input) {
		super(input);

	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + " Function " + this.getName() + " not defined";

		return ris;

	}

}
