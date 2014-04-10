package com.jhsf.traffic;

import java.io.Serializable;

public class TrafficDO implements Serializable{

	private static final long serialVersionUID = 4859994044700175705L;
	
	private Object[] params;
	
	private String method;
	
	private String className;
	
	public TrafficDO(){}
	
	public TrafficDO(Object[] objs,String method,String className){
		this.className = className;
		this.params = objs;
		this.method = method;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
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
