package exception;

import org.antlr.v4.runtime.Token;

public class RedeclaredMethod extends RedeclaredFunction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String NameOfClass;// nome della classe a cui il metodo appartiene

	public RedeclaredMethod(Token input, String NameOfClass) {
		super(input);

		this.NameOfClass = NameOfClass;

	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + " Method: " + this.getName()
				+ " already defined or doesn't override correctly a method defined in one of the ancestor classes. \n"
				+ "Error detected in the class with name: " + this.NameOfClass;

		return ris;

	}

}
