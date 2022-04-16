package exception;

import org.antlr.v4.runtime.Token;

public class FieldForbiddenOverriding extends TokenException {

	public FieldForbiddenOverriding(Token input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String msg() {

		String ris = this.getLine() + ": " + this.getColumn()
				+ " It's forbidden to overwrite a fied of a class -- , error occured in the class with name :"
				+ this.getName();

		return ris;

	}
}
