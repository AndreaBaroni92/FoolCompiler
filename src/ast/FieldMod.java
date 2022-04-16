package ast;

import java.util.ArrayList;

import exception.ForbiddenFieldMod;
import exception.LackField;
import exception.NotDefinedVar;
import exception.TypeException;
import parser.FOOLParser.FieldmodstmContext;
import type.ClassType;
import type.IType;
import type.VoidType;
import util.ClassField;
import util.Environment;
import util.SemanticError;
import util.TypeErrorMsg;

public class FieldMod implements Node {

	private FieldmodstmContext ctx;

	private Node exp;

	private ClassField currentField;
	
	private int nl;
	
	private int objNl;
	
	private int objOff;
	
	int index;

	public FieldMod(FieldmodstmContext ctx, Node exp) {
		setCtx(ctx);
		setExp(exp);
	}

	@Override
	public IType typeCheck() throws TypeException {
		if (exp.typeCheck().isSubtypeOf(currentField.getType()) == false) {

			TypeErrorMsg typerr = new TypeErrorMsg(this.ctx,
					" Error during an assignment that involved a field of a class"
							+ ", the right-hand-side type isn't a subtype of left-hand-side");

			String TypeMsg = typerr.getTypeErrorMsg();

			throw new TypeException(TypeMsg);

		}
		return new VoidType();
	}

	@Override
	public String codeGeneration() {
		String ris = "";
		
		String getAR="";
		
		for ( int i =0 ; i < nl-objNl; i ++) {
			getAR +="lw\n";
		}
		
		ris = "push "+index+"\n"+
			   "push "+objOff +"\n"+
				"lfp\n"+getAR+
				"add\n"+
				"lw\n"+
				exp.codeGeneration()+
				"cfv\n"
				;
		
		
		
		return ris;
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		ArrayList<SemanticError> err = new ArrayList<>();
		try {
			STentry entryObj = env.getId(this.ctx.objID);

			IType objectType = entryObj.getType();

			if ((objectType instanceof ClassType) == false) {

				throw new ForbiddenFieldMod(this.ctx.objID);

			}

			ClassType ct = (ClassType) objectType;

			 index = getIndex(ct.getOrderedFields(), ctx.fieID.getText());

			if (index == -1) {
				throw new LackField(this.ctx.objID, ctx.fieID.getText(), 1);
			}

			currentField = ct.getFields().get(ctx.fieID.getText());
			
			nl= env.getNestingLevel();
			
			objNl = entryObj.getNestinglevel();
			
			objOff = entryObj.getOffset();

			err.addAll(exp.checkSemantics(env));

		}

		catch (NotDefinedVar | ForbiddenFieldMod | LackField e) {
			err.add(new SemanticError(e.msg()));
		}

		return err;
	}

	public Node getExp() {
		return exp;
	}

	public void setExp(Node exp) {
		this.exp = exp;
	}

	public FieldmodstmContext getCtx() {
		return ctx;
	}

	public void setCtx(FieldmodstmContext ctx) {
		this.ctx = ctx;
	}

	private int getIndex(ArrayList<ClassField> input, String toSearch) {

		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).getNameField().equals(toSearch)) {

				return i;

			}
		}

		return -1;

	}

}
