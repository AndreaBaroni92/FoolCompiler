package util;

import java.util.ArrayList;

public class ConcatSemanticErrorMsgs {

	private ArrayList<SemanticError> errors;

	public ConcatSemanticErrorMsgs(ArrayList<SemanticError> input) {
		this.errors = input;
	}

	public String getConcatErrorMsgs() {

		String ris = new String();

		for (SemanticError er : errors) {

			ris = ris.concat(er.returnMsg());
			ris= ris.concat("\n");

		}
		
		return ris;

	}

}
