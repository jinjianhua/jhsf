package com.asm.test;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.jhsf.asm.classloader.MyClassLoader;
import com.jhsf.test.TestService;

public class AsmTestV4 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		 ClassWriter cw = new ClassWriter(0);
		 cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "TestServiceImpl", null, "com/jhsf/traffic/TrafficService", 
				 new String[] { "com/jhsf/test/TestService" });
		 
		 //生成默认的构造方法
		  MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
		    "<init>",
		    "()V",
		    null,
		    null);

		  //生成构造方法的字节码指令
		  mw.visitVarInsn(Opcodes.ALOAD, 0);
		  mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/jhsf/traffic/TrafficService", "<init>", "()V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(1, 1);
		  mw.visitEnd();
		  
		  
		  //生成main方法
		  mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
		    "add",
		    "(Ljava/lang/String;)V",
		    null,
		    null);

		  mw.visitInsn(Opcodes.ICONST_1);
		  mw.visitTypeInsn(Opcodes.ANEWARRAY, "com/jhsf/asm/model/ParamDO");
		  mw.visitVarInsn(Opcodes.ASTORE,2);  
		  
		  mw.visitVarInsn(Opcodes.ALOAD,2);  
		  mw.visitInsn(Opcodes.ICONST_0);  
		  
//		  mw.visitVarInsn(Opcodes.ALOAD,1);  
//		  mw.visitInsn(Opcodes.AASTORE);  
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
		  mw.visitVarInsn(Opcodes.ALOAD,1); 
		  mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/jhsf/asm/model/ParamDO", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V");
		  mw.visitInsn(Opcodes.AASTORE);
		  
		  mw.visitVarInsn(Opcodes.ALOAD,0);  
		  mw.visitVarInsn(Opcodes.ALOAD,2); 
		  
		  mw.visitLdcInsn("aa");
		  mw.visitLdcInsn("aa");
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
					"TestServiceImpl",
					    "execute",
					    "([Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V");
		  
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(30, 50);
		  
		  
		  byte[] code = cw.toByteArray();
		  
		  MyClassLoader loader = new MyClassLoader();
		  Class<?> exampleClass = loader.defineClass("TestServiceImpl", code);
		  Object o = exampleClass.newInstance();
		  TestService  s = (TestService)o;
		  s.add("aa");
//		  s.update("111");
//		  s.delete(11111, "aaa11111", new ArrayList<String>());
		  System.out.println();
	}
	
}
