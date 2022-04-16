package type;

public class IntType implements IType {

	@Override
	public boolean isSubtypeOf(IType type) {

		return this.getClass().equals(type.getClass());

	}

	@Override
	public String toString() {
		return "int";
	}

}
