package com.jhsf.asm.model;

import java.util.ArrayList;
import java.util.List;

public class ClassByteCodeDO {

	private List<MethodByteCodeDO>  methodByteCodeDOs = new ArrayList<MethodByteCodeDO>();
	
	private String name;
	
	private String extendsName ="java/lang/Object";
	
	public String getExtendsName() {
		return extendsName;
	}

	public void setExtendsName(String extendsName) {
		this.extendsName = extendsName;
	}

	public ClassByteCodeDO(){}
	
	public void clear(){
		name = null;
		methodByteCodeDOs.clear();
	}
	
	public String getImplName(){
		return name+"Impl";
	}
	
	public String getImplNameV2(){
		return getImplName().replaceAll("/", ".");
	}
	
	public ClassByteCodeDO(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MethodByteCodeDO> getMethodByteCodeDOs() {
		return methodByteCodeDOs;
	}

	public void setMethodByteCodeDOs(List<MethodByteCodeDO> methodByteCodeDOs) {
		this.methodByteCodeDOs = methodByteCodeDOs;
	}

	public void add(MethodByteCodeDO methodByteCodeDO) {
		methodByteCodeDOs.add(methodByteCodeDO);
	}
	
}
