package type;

public class NullType implements IType {

	@Override
	public boolean isSubtypeOf(IType type) {
		
		return (type instanceof ClassType);
	}

}
