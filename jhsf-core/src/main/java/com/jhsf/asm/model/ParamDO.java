package com.jhsf.asm.model;

import java.io.Serializable;

public class ParamDO implements Serializable{

	private static final long serialVersionUID = 2474062750248243150L;

	private String  type;
	
	private Object value;
	
	public ParamDO(){}

	public ParamDO(String  type,Object value){
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
