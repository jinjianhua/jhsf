package com.jhsf.asm.classloader;

public class MyClassLoader extends ClassLoader {
	
	public Class defineClass(String name, byte[] bs) {
	        return defineClass(name, bs, 0, bs.length);
	    }
	 
}
