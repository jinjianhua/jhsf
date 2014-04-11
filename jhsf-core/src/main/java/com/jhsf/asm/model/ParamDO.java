package com.jhsf.asm.model;

import java.io.Serializable;

public class ParamDO implements Serializable{

	private static final long serialVersionUID = 2474062750248243150L;

	private Type  type;
	
	private Object value;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
