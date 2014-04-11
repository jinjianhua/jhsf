package com.asm.test;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.jhsf.asm.classloader.MyClassLoader;

public class AsmTestV3 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		 ClassWriter cw = new ClassWriter(0);
		 cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "HsfServiceImpl", null, "java/lang/Object", 
				 new String[] { "com/asm/test/Hservice" });
		 
		 //生成默认的构造方法
		  MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
		    "<init>",
		    "()V",
		    null,
		    null);

		  //生成构造方法的字节码指令
		  mw.visitVarInsn(Opcodes.ALOAD, 0);
		  mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(1, 1);
		  mw.visitEnd();
		  
		  
		  //TODO
		  mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
				    "update",
				    "(Lcom/asm/test/Service;)V",
				    null,
				    null);
		  mw.visitVarInsn(Opcodes.ALOAD, 1);  
		  mw.visitLdcInsn("");
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
				    "com/asm/test/Service",
				    "add",
				    "(Ljava/lang/String;)V");
		 
		  mw.visitMaxs(2, 2);
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitEnd();
		  
		  byte[] code = cw.toByteArray();
		  
		  MyClassLoader loader = new MyClassLoader();
		  Class<?> exampleClass = loader.defineClass("HsfServiceImpl", code);
		  Object o = exampleClass.newInstance();
		  Hservice  s = (Hservice)o;
		  Service  service = new Service();
		  s.update(service);
	}
	
}
