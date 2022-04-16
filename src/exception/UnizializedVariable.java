package exception;

import org.antlr.v4.runtime.Token;

public class UnizializedVariable extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnizializedVariable(Token input) {
		super(input);

	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + "  Variable " + this.getName()
				+ " may not have been inizialized ";

		return ris;

	}

}
