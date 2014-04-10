package com.jhsf.traffic;

import com.jhsf.mina.client.MinaTrafficClient;

public class TrafficService {
	
	public void execute(Object[] objs,String method,String className){
		TrafficDO trafficDO = new TrafficDO(objs, method, className);
		MinaTrafficClient.send(trafficDO);
	}
	
}
