package com.jhsf.asm;


public interface ProxyCreate {

	public <T> T createImpl(Class<T> clazz);
	
}
