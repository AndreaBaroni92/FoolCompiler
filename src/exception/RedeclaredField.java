package exception;

import org.antlr.v4.runtime.Token;

public class RedeclaredField extends RedeclaredVar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedeclaredField(Token e) {
		super(e);
		
	}
	
	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn() + "  Field " + this.getName() + " already defined";

		return ris;

	}

	

}
