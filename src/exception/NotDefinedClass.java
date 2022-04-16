package exception;

import org.antlr.v4.runtime.Token;

public class NotDefinedClass extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotDefinedClass(Token input) {
		super(input);
	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + " The Class with name: " + this.getName()
				+ " doesn't exist ";

		return ris;

	}

}
