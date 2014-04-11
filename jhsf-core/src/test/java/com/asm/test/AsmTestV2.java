package com.asm.test;

import java.util.ArrayList;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.jhsf.asm.classloader.MyClassLoader;
import com.jhsf.test.TestService;

public class AsmTestV2 {

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
		  mw.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
		  mw.visitVarInsn(Opcodes.ASTORE,2);  
		  
		  mw.visitVarInsn(Opcodes.ALOAD,2);  
		  mw.visitInsn(Opcodes.ICONST_0);  
		  mw.visitVarInsn(Opcodes.ALOAD,1);  
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
//		  s.add();
//		  s.update("111");
//		  s.delete(11111, "aaa11111", new ArrayList<String>());
		  System.out.println();
	}
	
}
