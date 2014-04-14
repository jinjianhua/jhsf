package com.jhsf.asm;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.jhsf.asm.classloader.MyClassLoader;
import com.jhsf.asm.model.ClassByteCodeDO;
import com.jhsf.asm.model.MethodByteCodeDO;

public class AsmProxyCreate implements ProxyCreate{
	
	public static Map<Integer,Integer>   opcodesMap  = new HashMap<Integer,Integer>();
	
	static{
		opcodesMap.put(0, Opcodes.ICONST_0);
		opcodesMap.put(1, Opcodes.ICONST_1);
		opcodesMap.put(2, Opcodes.ICONST_2);
		opcodesMap.put(3, Opcodes.ICONST_3);
		opcodesMap.put(4, Opcodes.ICONST_4);
		opcodesMap.put(5, Opcodes.ICONST_5);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object createImpl(String className) {
		ClassByteCodeDO classByteCodeDO =  ClassPrinter.getMethodByteCodeDO(className);
		classByteCodeDO.setExtendsName("com/jhsf/traffic/TrafficService");//TODO 继承那个类
		Object object = newClassImpl(classByteCodeDO);
		if(null == object)
			return null;
		return object;
	}
	
	private Object   newClassImpl(ClassByteCodeDO classByteCodeDO){
		try{
			ClassWriter cw = new ClassWriter(0);
			cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, classByteCodeDO.getImplName(), null, classByteCodeDO.getExtendsName(), 
				 new String[] { classByteCodeDO.getName() });
			createInit(cw,classByteCodeDO);
			createMethodsV2(cw, classByteCodeDO);
			byte[] code = cw.toByteArray();
			MyClassLoader loader = new MyClassLoader();
			Class<?> exampleClass = loader.defineClass(classByteCodeDO.getImplNameV2(), code);
			Object o = exampleClass.newInstance();
			return o;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	private void createMethods(ClassWriter cw,ClassByteCodeDO classByteCodeDO){
		if(CollectionUtils.isEmpty(classByteCodeDO.getMethodByteCodeDOs()))
			return;
		for(MethodByteCodeDO  methodByteCodeDO : classByteCodeDO.getMethodByteCodeDOs()){
			MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
					methodByteCodeDO.getName(),methodByteCodeDO.getDesc(),null,null);
			
			mw.visitInsn(opcodesMap.get(methodByteCodeDO.getCount())); //TODO 暂时只考虑小于6位参数
			mw.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
			mw.visitVarInsn(Opcodes.ASTORE,methodByteCodeDO.getCount() + 1);  
			
			for(int i =1;i <= methodByteCodeDO.getCount();i++){
				 mw.visitVarInsn(Opcodes.ALOAD,methodByteCodeDO.getCount() + 1);  
				 mw.visitInsn(opcodesMap.get(i - 1));  
				 mw.visitVarInsn(Opcodes.ALOAD,i);  
				 mw.visitInsn(Opcodes.AASTORE);  
			}
			
			mw.visitVarInsn(Opcodes.ALOAD,0);  
			mw.visitVarInsn(Opcodes.ALOAD,methodByteCodeDO.getCount() + 1); 
			mw.visitLdcInsn(methodByteCodeDO.getName());
			mw.visitLdcInsn(classByteCodeDO.getName());
			mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
					classByteCodeDO.getImplName(),
					    "execute",
					    "([Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V");
			mw.visitInsn(Opcodes.RETURN);
			mw.visitMaxs(30, 50);//TODO 这个要改
			mw.visitEnd();
		}
	}
	
	
	private void createMethodsV2(ClassWriter cw,ClassByteCodeDO classByteCodeDO){
		if(CollectionUtils.isEmpty(classByteCodeDO.getMethodByteCodeDOs()))
			return;
		for(MethodByteCodeDO  methodByteCodeDO : classByteCodeDO.getMethodByteCodeDOs()){
			MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
					methodByteCodeDO.getName(),methodByteCodeDO.getDesc(),null,null);
			
			mw.visitInsn(opcodesMap.get(methodByteCodeDO.getCount())); //TODO 暂时只考虑小于6位参数
			mw.visitTypeInsn(Opcodes.ANEWARRAY, "com/jhsf/asm/model/ParamDO");
			mw.visitVarInsn(Opcodes.ASTORE,methodByteCodeDO.getCount() + 1);  
			
			for(int i =1;i <= methodByteCodeDO.getCount();i++){
				 mw.visitVarInsn(Opcodes.ALOAD,methodByteCodeDO.getCount() + 1);  
				 mw.visitInsn(opcodesMap.get(i - 1));  
				 mw.visitTypeInsn(Opcodes.NEW, "com/jhsf/asm/model/ParamDO");
				  mw.visitInsn(Opcodes.DUP);
				  mw.visitVarInsn(Opcodes.ALOAD,1);  
				  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
							"java/lang/Object",
							    "getClass",
							    "()Ljava/lang/Class;");
				  
				  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
							"java/lang/Class",
							    "getName",
							    "()Ljava/lang/String;");
				 mw.visitVarInsn(Opcodes.ALOAD,i); 
				 mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/jhsf/asm/model/ParamDO", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V");
				 mw.visitInsn(Opcodes.AASTORE);  
			}
			
			mw.visitVarInsn(Opcodes.ALOAD,0);  
			mw.visitVarInsn(Opcodes.ALOAD,methodByteCodeDO.getCount() + 1); 
			mw.visitLdcInsn(methodByteCodeDO.getName());
			mw.visitLdcInsn(classByteCodeDO.getName());
			mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
					classByteCodeDO.getImplName(),
					    "execute",
					    "([Lcom/jhsf/asm/model/ParamDO;Ljava/lang/String;Ljava/lang/String;)V");
			mw.visitInsn(Opcodes.RETURN);
			mw.visitMaxs(30, 50);//TODO 这个要改
			mw.visitEnd();
		}
	}
	
	/**
	 * //生成默认的构造方法
	 * @param cw
	 */
	private void createInit(ClassWriter cw,ClassByteCodeDO classByteCodeDO) {
		  MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
		    "<init>",
		    "()V",
		    null,
		    null);
		  mw.visitVarInsn(Opcodes.ALOAD, 0);
		  mw.visitMethodInsn(Opcodes.INVOKESPECIAL, classByteCodeDO.getExtendsName(), "<init>", "()V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(1, 1);
		  mw.visitEnd(); 
	}
	
		
}
