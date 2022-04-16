package type;

import java.util.ArrayList;
import java.util.List;

import ast.ArgsNode;

public class FunctionType implements IType {

	private IType outType;

	private ArrayList<IType> argType;

	public FunctionType(IType outType, ArrayList<IType> argType) {

		this.outType = outType;

		this.argType = argType;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see type.IType#isSubtypeOf(type.IType)
	 * 
	 * La funzione definita qui sotto viene impiegata per controllare se il tipo di
	 * una funzione f1 e' sottotipo del tipo di una funzione f2, ovvero se il tipo
	 * restituito da f1 e' sottotipo del tipo restituito da f2, se hanno il medesimo
	 * numero di parametri , e se ogni tipo di parametro di f1 e' sopratipo del
	 * corrispondente tipo di parametro di f2.
	 */

	@Override
	public boolean isSubtypeOf(IType type) {

		// se type non e' una funzione si restituisce false
		if ((this.getClass().equals(type.getClass())) == false) {

			return false;
		}

		FunctionType ft = (FunctionType) type;

		if ((this.getReturnType().isSubtypeOf(ft.getReturnType())) == false) {

			return false;

		}

		int size = this.getListArgType().size();

		if (size != ft.getListArgType().size()) {
			return false;
		}

		ArrayList<IType> thisArgs = this.getListArgType();
		ArrayList<IType> args = ft.getListArgType();

		for (int i = 0; i < size; i++) {

			if (args.get(i).isSubtypeOf(thisArgs.get(i)) == false) {
				return false;
			}

		}

		return true;
	}

	public ArrayList<IType> getListArgType() {
		return this.argType;
	}

	public IType getReturnType() {

		return this.outType;

	}

}
