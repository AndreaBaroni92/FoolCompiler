package exception;

import org.antlr.v4.runtime.Token;

public class ForbiddenFieldMod extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForbiddenFieldMod(Token input) {
		super(input);
	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn()
				+ " Impossible to modify a field of a variable with name:  " + this.getName() + " that is not an object ";

		return ris;

	}

}
