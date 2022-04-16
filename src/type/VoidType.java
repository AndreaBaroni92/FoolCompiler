package type;

public class VoidType implements IType {

	@Override
	public boolean isSubtypeOf(IType type) {
		return this.getClass().equals(type.getClass());
	}

	@Override
	public String toString() {
		return "void";
	}
}
