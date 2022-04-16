package exception;

import org.antlr.v4.runtime.Token;

public class LackField extends TokenException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nameField;

	private int option;

	public LackField(Token input, String nameField) {
		super(input);
		this.setNameField(nameField);
		option = 0;
	}

	public LackField(Token input, String nameField, int option) {
		super(input);
		this.setNameField(nameField);
		this.option = option;
	}

	@Override
	public String msg() {

		String ris = "";
		if (option == 0) {

			ris = this.getLine() + ": " + this.getColumn() + " Missing field " + this.getNameField()
					+ " in class with name: " + this.getName();
		}

		else {
			ris = this.getLine() + ": " + this.getColumn()
					+ " Error during an attempt of assignment,\nthere is no field with name: " + this.getNameField()
					+ " in class: " + this.getName();
		}
		return ris;

	}

	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

}
