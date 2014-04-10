package com.asm.test;

import java.util.ArrayList;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.jhsf.asm.classloader.MyClassLoader;

public class AsmTestV2 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		 ClassWriter cw = new ClassWriter(0);
		 cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "HsfServiceImpl", null, "java/lang/Object", 
				 new String[] { "com/asm/test/HsfService" });
		 
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
		  
		  
		  //生成main方法
		  mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
		    "add",
		    "()V",
		    null,
		    null);

		  //生成main方法中的字节码指令
		  mw.visitFieldInsn(Opcodes.GETSTATIC,
		    "java/lang/System",
		    "out",
		    "Ljava/io/PrintStream;");

		  mw.visitLdcInsn("Hello world!");
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
		    "java/io/PrintStream",
		    "println",
		    "(Ljava/lang/String;)V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(2, 1);

		  //字节码生成完成
		  mw.visitEnd();
		  
		  
		  mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
				    "update",
				    "(Ljava/lang/String;)V",
				    null,
				    null);
		  
		  //生成main方法中的字节码指令
		  mw.visitFieldInsn(Opcodes.GETSTATIC,
		    "java/lang/System",
		    "out",
		    "Ljava/io/PrintStream;");

//		  mw.visitLdcInsn("Hello world!");
		  mw.visitVarInsn(Opcodes.ALOAD, 1);  
		  mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
		    "java/io/PrintStream",
		    "println",
		    "(Ljava/lang/String;)V");
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(2, 2);

		  //字节码生成完成
		  mw.visitEnd();
		  
		  
		  mw = cw.visitMethod(Opcodes.ACC_PUBLIC,
				    "delete",
				    "(Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)V",
				    null,
				    null);
		  mw.visitInsn(Opcodes.ICONST_3);  
		  mw.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
		  mw.visitVarInsn(Opcodes.ASTORE,4);  
		  
		  mw.visitVarInsn(Opcodes.ALOAD,4);  
		  mw.visitInsn(Opcodes.ICONST_1);  
		  mw.visitVarInsn(Opcodes.ALOAD,1);  
		  mw.visitInsn(Opcodes.AASTORE);  
		  
		  mw.visitInsn(Opcodes.RETURN);
		  mw.visitMaxs(3, 5);
		  
		  
		  byte[] code = cw.toByteArray();
		  
		  MyClassLoader loader = new MyClassLoader();
		  Class<?> exampleClass = loader.defineClass("HsfServiceImpl", code);
		  Object o = exampleClass.newInstance();
		  HsfService  s = (HsfService)o;
		  s.add();
		  s.update("111");
		  s.delete(11111, "aaa11111", new ArrayList<String>());
		  System.out.println();
	}
	
}
