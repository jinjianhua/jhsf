package com.jhsf.mina.client;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ClientCodecFactory implements ProtocolCodecFactory {
	
	private ProtocolEncoder encoder;
	private ProtocolDecoder decoder;

	public ClientCodecFactory(){
		encoder = new ClientProtocolEncoder();
		decoder = new ClientProtocolDecoder();
	}
	
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
