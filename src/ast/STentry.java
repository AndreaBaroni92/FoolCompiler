package ast;

import type.IType;

public class STentry {

	private int nl;// nesting level
	private IType type;
	private int offset;
	private boolean isInsideClass;


	public STentry(int n, int os) {
		nl = n;
		offset = os;
		
	}

	public STentry(int n, IType t, int os) {
		nl = n;
		type = t;
		offset = os;
		
	}

	public STentry(int n, IType t, int os, boolean isInsideClass) {
		nl = n;
		type = t;
		offset = os;
		this.setInsideClass(isInsideClass);
		
	}

	public void addType(IType t) {
		type = t;
	}

	public IType getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}

	public int getNestinglevel() {
		return nl;
	}

	public boolean isInsideClass() {
		return isInsideClass;
	}

	public void setInsideClass(boolean isInsideClass) {
		this.isInsideClass = isInsideClass;
	}

	public int getNl() {
		return nl;
	}

	public void setNl(int nl) {
		this.nl = nl;
	}



	public void setType(IType type) {
		this.type = type;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
