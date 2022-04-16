package exception;

import org.antlr.v4.runtime.Token;

public class TokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String line;
	
	private String column;
	
	private String name;
	
	public TokenException(Token input) {
		
		this.line = String.valueOf(input.getLine());
		
		this.column = String.valueOf(input.getCharPositionInLine()); 
		
		this.name = input.getText();
		
	}
	
	public String getLine() {
		return this.line;
	}
	
	public String getColumn() {
		return this.column;
	}
	
	public String getName() {
		return this.name;
	}

	public String msg() {
		return "Exception !";
	}
	

}
