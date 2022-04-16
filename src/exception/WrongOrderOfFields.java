package exception;

import org.antlr.v4.runtime.Token;

public class WrongOrderOfFields extends TokenException {

	public WrongOrderOfFields(Token input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String msg() {
		String ris = this.getLine() + ": " + this.getColumn() + "  The fields of this son class named: "
				+ this.getName() + " ,doesn't respect the order of fields of its parent class ";
		return ris;
	}
}
