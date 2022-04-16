package exception;

import org.antlr.v4.runtime.Token;

public class RedeclaredFunction extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedeclaredFunction(Token input) {
		super(input);

	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + " Function " + this.getName() + " already defined";

		return ris;

	}

}
