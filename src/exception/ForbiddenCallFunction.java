package exception;

import org.antlr.v4.runtime.Token;

public class ForbiddenCallFunction extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForbiddenCallFunction(Token input) {
		super(input);
	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn()
				+ " Error, please specify an object for the method with name " + this.getName() + " ";

		return ris;

	}


}
