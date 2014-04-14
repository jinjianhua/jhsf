package com.jhsf.asm.server;

import com.jhsf.asm.server.proxy.ServerAsmProxyCreate;
import com.jhsf.asm.server.proxy.ServerProxyCreate;
import com.jhsf.test.TestService;
import com.jhsf.test.TestServiceImpl2;
import com.jhsf.traffic.TrafficDO;

public class ExecuteService {

	public static void  execute(TrafficDO  trafficDO){
		ServerProxyCreate  serverProxyCreate = new ServerAsmProxyCreate();
		Hservice s = (Hservice)serverProxyCreate.createImpl(trafficDO);
		TestService   testService = new TestServiceImpl2();
		s.execute(testService);
	}
	
}
