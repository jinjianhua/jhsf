package com.asm.test;

import java.util.List;

public class HsfServiceImpl {

	public void add(){
		System.out.println("aaa");
	}
	
	public void update(String str){
		System.out.println(str);
	}
	
	public void delete(Integer i ,String s,List<String> strs){
		System.out.println(i + " "+ s+" "+strs.size());
	}
	
}
