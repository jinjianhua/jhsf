package com.asm.test;

import java.util.List;

public class HsfServiceImpl extends BaseService{
	
	

    public void add(){
		System.out.println("aaa");
	}
	
	public void update(String str){
		System.out.println(str);
	}
	
	public void delete(Integer arg1 ,String arg2,List<String> arg3){
		Object[]  params = new Object[7];
		params[1] = arg1;
		params[2] = arg2;
		params[3] = arg3;
		params[2] = arg2;
		this.execute(params, "","");
	}
	
}
