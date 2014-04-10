/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package com.jhsf.mina.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.caucho.hessian.io.HessianOutput;
import com.jhsf.mina.server.Tbean;
import com.jhsf.mina.server.TcpServer;

/**
 * An UDP client taht just send thousands of small messages to a UdpServer. 
 * 
 * This class is used for performance test purposes. It does nothing at all, but send a message
 * repetitly to a server.
 * 
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class TcpClient extends IoHandlerAdapter {
    /** The connector */
    private IoConnector connector;

    /** The session */
    private static IoSession session;

    private boolean received = false;

    /**
     * Create the UdpClient's instance
     */
    public TcpClient() {
        connector = new NioSocketConnector();

        connector.setHandler(this);
        SocketSessionConfig dcfg = (SocketSessionConfig) connector.getSessionConfig();
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ClientCodecFactory()));
        ConnectFuture connFuture = connector.connect(new InetSocketAddress("localhost", TcpServer.PORT));

        connFuture.awaitUninterruptibly();

        session = connFuture.getSession();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        received = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
    }

    /**
     * The main method : instanciates a client, and send N messages. We sleep 
     * between each K messages sent, to avoid the server saturation.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
		TcpClient client = new TcpClient();
		for (int i = 0; i < 9; i++) {
			byte[] bytes = getBytesByHassian(null);
			IoBuffer buffer = IoBuffer.allocate(bytes.length);
			buffer.put(bytes);
			buffer.flip();
			WriteFuture future = session.write(buffer);
		}
		client.connector.dispose(true);
//    	System.out.println(getBytes().length);
//    	System.out.println(getBytesByHassian().length);
    }
    
    public void send(Object  obj){
    	byte[] bytes = getBytesByHassian(obj);
    	IoBuffer buffer = IoBuffer.allocate(bytes.length);
		buffer.put(bytes);
		buffer.flip();
		WriteFuture future = session.write(buffer);
		this.connector.dispose(true);
    }
    
    
    
    public static  byte[]  getBytes() throws Exception{
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();  
    	ObjectOutputStream oos = new ObjectOutputStream(bos);       
    	Tbean  obj = new Tbean();
    	obj.setName("aaaa");
    	oos.writeObject(obj);
    	byte[] byte1 = bos.toByteArray();
    	String count = byte1.length+"";
    	byte[] byte2 = count.getBytes();
    	byte[] byte9 = new byte[9];//前面9位便是后面数据大小
    	for(int i=0;i<byte2.length;i++){
    		byte9[i] = byte2[i];
    	}
    	byte[] tbyte = new byte[9+byte1.length];
    	for(int i=0;i<9;i++){
    		tbyte[i] = byte9[i];
    	}
    	for(int i=9;i<tbyte.length;i++){
    		tbyte[i] = byte1[i-9];
    	}
    	return tbyte;
    }
    
    
    public static byte[] getBytesByHassian(Object obj){
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			HessianOutput oos = new HessianOutput(bos);
			// Tbean obj = new Tbean();
			// obj.setName("aaaa");
			oos.writeObject(obj);
			byte[] byte1 = bos.toByteArray();
			String count = byte1.length + "";
			byte[] byte2 = count.getBytes();
			byte[] byte9 = new byte[9];// 前面9位便是后面数据大小
			for (int i = 0; i < byte2.length; i++) {
				byte9[i] = byte2[i];
			}
			byte[] tbyte = new byte[9 + byte1.length];
			for (int i = 0; i < 9; i++) {
				tbyte[i] = byte9[i];
			}
			for (int i = 9; i < tbyte.length; i++) {
				tbyte[i] = byte1[i - 9];
			}
			return tbyte;
		} catch (Exception e) {
			return null;
		}
    }
    
}
