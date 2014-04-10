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
		opcodesMap.put(1, Opcodes.ICONST_1);
		opcodesMap.put(2, Opcodes.ICONST_2);
		opcodesMap.put(3, Opcodes.ICONST_3);
		opcodesMap.put(4, Opcodes.ICONST_4);
		opcodesMap.put(5, Opcodes.ICONST_5);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T createImpl(Class<T> clazz) {
		ClassByteCodeDO classByteCodeDO =  ClassPrinter.getMethodByteCodeDO(clazz);
		classByteCodeDO.setExtendsName("com/jhsf/asm/traffic/TrafficService");//TODO 继承那个类
		Object object = newClassImpl(classByteCodeDO);
		if(null == object)
			return null;
		return (T)object;
	}
	
	private Object   newClassImpl(ClassByteCodeDO classByteCodeDO){
		try{
			ClassWriter cw = new ClassWriter(0);
			cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, classByteCodeDO.getImplName(), null, classByteCodeDO.getExtendsName(), 
				 new String[] { classByteCodeDO.getName() });
			createInit(cw);
			createMethods(cw, classByteCodeDO);
			byte[] code = cw.toByteArray();
			MyClassLoader loader = new MyClassLoader();
			Class<?> exampleClass = loader.defineClass(classByteCodeDO.getImplNameV2(), code);
			Object o = exampleClass.newInstance();
			return o;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	private void createMethods(ClassWriter cw,ClassByteCodeDO classByteCodeDO){
		if(CollectionUtils.isEmpty(classByteCodeDO.getMethodByteCodeDOs()))
			return;
		for(MethodByteCodeDO  methodByteCodeDO : classByteCodeDO.getMethodByteCodeDOs()){
			MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
					methodByteCodeDO.getName(),methodByteCodeDO.getDesc(),null,null);
			
			mw.visitInsn(Opcodes.ICONST_3); //TODO 暂时只考虑小于6位参数
			mw.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
			mw.visitVarInsn(Opcodes.ASTORE,4);  
			
			for(int i =1;i <= methodByteCodeDO.getCount();i++){
				 mw.visitVarInsn(Opcodes.ALOAD,4);  
				 mw.visitInsn(opcodesMap.get(i));  
				 mw.visitVarInsn(Opcodes.ALOAD,i);  
				 mw.visitInsn(Opcodes.AASTORE);  
			}
			
			mw.visitVarInsn(Opcodes.ALOAD,0);  
			mw.visitVarInsn(Opcodes.ALOAD,4); 
			mw.visitLdcInsn(methodByteCodeDO.getName());
			mw.visitLdcInsn(classByteCodeDO.getName());
			mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
					    "com/asm/test/HsfServiceImpl",
					    methodByteCodeDO.getName(),
					    "([Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V");
			mw.visitInsn(Opcodes.RETURN);
			mw.visitMaxs(3, 5);//TODO 这个要改
		}
	}
	
	/**
	 * //生成默认的构造方法
	 * @param cw
	 */
	private void createInit(ClassWriter cw) {
		  MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
		    "<init>",
		    "()V",
		    null,
		    null);
		  mw.visitVarInsn(Opcodes.ALOAD, 0);
		  mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(1, 1);
		  mw.visitEnd();
	}
	

}
