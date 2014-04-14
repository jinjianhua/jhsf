package com.jhsf.traffic;

import java.io.Serializable;

import com.jhsf.asm.model.ParamDO;

public class TrafficDO implements Serializable{

	private static final long serialVersionUID = 4859994044700175705L;
	
	private ParamDO[] params;
	
	private String method;
	
	private String className;
	
	public TrafficDO(){}
	
	public TrafficDO(ParamDO[] objs,String method,String className){
		this.className = className;
		this.params = objs;
		this.method = method;
	}

	public ParamDO[] getParams() {
		return params;
	}

	public void setParams(ParamDO[] params) {
		this.params = params;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
