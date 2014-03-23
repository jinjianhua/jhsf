package com.jhsf.mina.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ServerCodecFactory implements ProtocolCodecFactory {
	
	private ProtocolEncoder encoder;
	private ProtocolDecoder decoder;

	public ServerCodecFactory(){
		decoder  = new ServerProtocolDecoder();
		encoder = new ServerProtocolEncoder();
	}
	
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
