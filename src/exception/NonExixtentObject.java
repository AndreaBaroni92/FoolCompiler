package exception;

import org.antlr.v4.runtime.Token;

public class NonExixtentObject extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonExixtentObject(Token input) {
		super(input);
	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn()
				+ " Impossible to call a method on a variable, with name:  " + this.getName()
				+ " that is not an instance of a class ";

		return ris;

	}

}
