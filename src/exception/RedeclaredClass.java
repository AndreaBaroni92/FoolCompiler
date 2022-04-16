package exception;

import org.antlr.v4.runtime.Token;

public class RedeclaredClass extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedeclaredClass(Token input) {
		super(input);
	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + "  Class " + this.getName() + " already defined";

		return ris;

	}

}
