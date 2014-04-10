package com.jhsf.mina.client;

public class MinaTrafficClient {
	
	private static TcpClient topCLient = new TcpClient();

	public static void send(Object obj){
		topCLient.send(obj);
	}
	
}
