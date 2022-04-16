package exception;

import org.antlr.v4.runtime.Token;

public class NotDefinedMethod extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MethodName;

	public NotDefinedMethod(Token input, String MethodName) {
		super(input);
		this.MethodName = MethodName;
	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + " The Object " + this.getName()
				+ " doesn't have a method with name: " + MethodName;

		return ris;

	}

}
