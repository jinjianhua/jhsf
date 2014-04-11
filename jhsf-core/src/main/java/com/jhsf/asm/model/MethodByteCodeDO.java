package com.jhsf.asm.model;

public class MethodByteCodeDO {

	private String name;
	
	private String desc;
	
	public MethodByteCodeDO(String name,String desc){
		this.name = name;
		this.desc = desc;
	}
	
	public int getCount(){
		String[] strs =desc.split(";");
		return strs.length - 1;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
