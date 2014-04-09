package com.asm.test;

import java.lang.reflect.InvocationTargetException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmHello {

	public static void main(String[] args) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException {
		 //定义一个叫做Example的类
		  ClassWriter cw = new ClassWriter(0);
		  cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Example", null, "java/lang/Object", null);

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
		  mw = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
		    "main",
		    "([Ljava/lang/String;)V",
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
		  mw.visitMaxs(2, 2);

		  //字节码生成完成
		  mw.visitEnd();

		  // 获取生成的class文件对应的二进制流
		  byte[] code = cw.toByteArray();


		  //将二进制流写到本地磁盘上

		  //直接将二进制流加载到内存中
		  MyClassLoader loader = new MyClassLoader();
		  Class<?> exampleClass = loader.defineClass("Example", code);
		  Object o = exampleClass.newInstance();
		  System.out.println();
		  //通过反射调用main方法
		  exampleClass.getMethods()[0].invoke(null, new Object[] { null });

		  
		
		
	}
}
