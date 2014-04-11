package com.asm.test;

import java.util.ArrayList;
import java.util.List;

import com.jhsf.asm.model.ParamDO;

public class HsfServiceImpl extends BaseService{
	
	
	public void add(){
		System.out.println("aaa");
	}
	
	public void update(String str){
		System.out.println(str);
	}
	
	public void delete(Integer arg1 ,String arg2,List<String> arg3){
		ParamDO[]  params = new ParamDO[7];
		params[1] = new ParamDO();
		params[2] = new ParamDO();
		params[3] = new ParamDO();
		params[2] = new ParamDO();
		this.execute(params, "","");
	}
	
	public static void main(String[] args) {
		Integer arg1 = 1;
		String arg2 = "";
		List<String> ids = new ArrayList<String>();
		System.out.println(ids.getClass().getName());
	}
	
}
