package com.jhsf.asm.server.proxy;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.jhsf.asm.classloader.MyClassLoader;
import com.jhsf.asm.model.ParamDO;
import com.jhsf.traffic.TrafficDO;

public class ServerAsmProxyCreate implements ServerProxyCreate{
	
	private  String classImplName = "HsfServiceImpl";

	@Override
	public Object createImpl(TrafficDO trafficDO) {
		try{
			ClassWriter cw = new ClassWriter(0);
			 cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, classImplName, null, "java/lang/Object", 
					 new String[] { "com/jhsf/asm/server/Hservice" });
			 createInit(cw);
			 createMethods(cw, trafficDO);
			 byte[] code = cw.toByteArray();
			 MyClassLoader loader = new MyClassLoader();
			 Class<?> exampleClass = loader.defineClass(classImplName, code);
			Object o = exampleClass.newInstance();
			return o;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	private void createMethods(ClassWriter cw,TrafficDO  trafficDO){
		trafficDO.setClassName("com/jhsf/test/TestServiceImpl2");//TODO  这里必须要实现类
		
		String classDesc = "(L"+trafficDO.getClassName()+";)V";
		
		String methodDesc = "L";
		
		for(ParamDO  paramDO : trafficDO.getParams()){
			methodDesc = methodDesc + paramDO.getType()+";";
		}
		
		methodDesc = methodDesc.replaceAll("\\.", "/");
		
		MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "execute",
				classDesc, null, null);
		mw.visitVarInsn(Opcodes.ALOAD, 1);
		mw.visitLdcInsn("");
		mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL, trafficDO.getClassName(),
				trafficDO.getMethod(), "("+methodDesc+")V");//TODO  参数要改

		mw.visitMaxs(2, 2);
		mw.visitInsn(Opcodes.RETURN);
		mw.visitEnd();
		
		mw = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_VOLATILE+ Opcodes.ACC_SYNTHETIC + Opcodes.ACC_BRIDGE, "execute",
				"(Ljava/lang/Object;)V", null, null);
		mw.visitVarInsn(Opcodes.ALOAD, 0);
		mw.visitVarInsn(Opcodes.ALOAD, 1);
		mw.visitTypeInsn(Opcodes.CHECKCAST, trafficDO.getClassName());
		mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL, classImplName, "execute",
				classDesc);
		mw.visitMaxs(4, 4);
		mw.visitInsn(Opcodes.RETURN);
		mw.visitEnd();
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
