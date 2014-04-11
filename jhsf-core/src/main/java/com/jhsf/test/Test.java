package com.jhsf.test;

import com.jhsf.asm.AsmProxyCreate;

public class Test {

	public static void main(String[] args) {
		AsmProxyCreate  proxyCreate = new AsmProxyCreate();
		Object o =proxyCreate.createImpl("com/jhsf/test/TestService");
		TestService  testService = (TestService)o;
		testService.add("aa");
	}

}
