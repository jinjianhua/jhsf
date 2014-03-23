package com.jhsf.mina.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ClientProtocolDecoder implements ProtocolDecoder{
	
	 private final AttributeKey BUFFER = new AttributeKey(getClass(), "buffer");

	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)throws Exception {
		System.out.println("decode");
	    
//	    IoBuffer buf = (IoBuffer) session.getAttribute(BUFFER);
//	    if(null != buf){
//	    	 buf.flip();
//             IoBuffer newBuf = IoBuffer.allocate(buf.remaining() + in.remaining()).setAutoExpand(true);
//             newBuf.order(buf.order());
//             newBuf.put(buf);
//             newBuf.put(in);
//             newBuf.flip();
//	    	
//	    	aaa(session,newBuf);
//	    }else{
//			aaa(session, in);
//	    }
	    
		out.write(in);
		return;
	}

//	private void aaa(IoSession session, IoBuffer in) throws IOException,
//			ClassNotFoundException {
//		int limit = in.limit();
//		if (limit > 9) {
//			byte[] byte9 = new byte[9];
//			in.get(byte9);
//			int size = getSize(byte9);
//			if(limit - 9 == size){
//				byte[] bytes = new byte[size];
//				in.get(bytes);
//				InputStream is= new ByteArrayInputStream(bytes);
//			    ObjectInputStream ois=new ObjectInputStream(is);
//			    Tbean tbean =  (Tbean)ois.readObject();
//				System.out.println(tbean.getName());
//			}else if(limit - 9 <size){
//				in.position(0);
//				session.setAttribute(BUFFER,in);
//			}else if(limit - 9 >size){
//				byte[] bytes = new byte[size];
//				in.get(bytes);
//				InputStream is= new ByteArrayInputStream(bytes);
//			    ObjectInputStream ois=new ObjectInputStream(is);
//			    Tbean tbean =  (Tbean)ois.readObject();
//				System.out.println(tbean.getName());
//				session.setAttribute(BUFFER,in);
//			}
//		}else{
//			 session.setAttribute(BUFFER,in);
//		}
//	}

	private int getSize(byte[] byte9) {
		List<Byte>  byteList = new ArrayList<Byte>();
		for(Byte b:byte9){
			if(0 == b)
				continue;
			byteList.add(b);
		}
		
		byte[]  bs = new byte[byteList.size()];
		
		for(int i = 0 ;i<byteList.size();i++){
			bs[i] = byteList.get(i);
		}

		return Integer.valueOf(new String(bs));
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out)throws Exception {
		
		
	}

	public void dispose(IoSession session) throws Exception {
		
		
	}

}
